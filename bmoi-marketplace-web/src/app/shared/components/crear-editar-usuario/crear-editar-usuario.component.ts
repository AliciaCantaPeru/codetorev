import { DatePipe } from '@angular/common';
import {
  Component,
  EventEmitter,
  OnInit,
  Output,
  ViewChild,
} from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { EmpresaMantDto, Persona } from '@core/models/seller.model';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { DialogAlertService } from '@core/services/dialog-alert.service';
import { SellerService } from '@core/services/seller.service';
import { Alert } from '@core/soporte/alert';
import { FormContactoComponent } from '@shared/components/form-contacto/form-contacto.component';
import { FormDatoEmpresaComponent } from '@shared/components/form-dato-empresa/form-dato-empresa.component';
import { FormDireccionComponent } from '@shared/components/form-direccion/form-direccion.component';
import { FormRedSocialComponent } from '@shared/components/form-red-social/form-red-social.component';

@Component({
  selector: 'app-crear-editar-usuario',
  templateUrl: './crear-editar-usuario.component.html',
  styleUrls: ['./crear-editar-usuario.component.scss'],
})
export class CrearEditarUsuarioComponent implements OnInit {
  tipo = 2;
  @Output() cancelar: EventEmitter<boolean> = new EventEmitter();
  @ViewChild(FormDatoEmpresaComponent, { static: true })
  datoEmpresa: FormDatoEmpresaComponent;
  @ViewChild(FormContactoComponent, { static: true })
  datoContacto: FormContactoComponent;
  @ViewChild(FormRedSocialComponent, { static: true })
  redSocial: FormRedSocialComponent;
  @ViewChild(FormDireccionComponent, { static: true })
  direccion: FormDireccionComponent;
  invalidDatoEmpresa = false;
  invalidDatoPersona = false;
  invalidDataRedSocial = false;
  invalidDireccion = false;
  foto: string = null;
  fotoTemporal: File = null;
  id: number;
  indexTab = 0;
  permitirModificar = false;
  constructor(
    private datePipe: DatePipe,
    private clienteService: SellerService,
    private autenticacionService: AutenticacionService,
    public dialogAlertService: DialogAlertService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.permitirModificar = this.autenticacionService.permitirModificar();
    this.route.params.subscribe((param) => {
      console.log(param);
      this.id = Number(param.id);
    });
    this.datoEmpresa.crearForm();
    this.redSocial.crearForm();
    this.direccion.crearForm();
    this.datoContacto.crearForm();
    this.obtenerDato();
  }

  obtenerDato() {
    if (this.id !== -1) {
      this.clienteService
        .obtener(
          this.autenticacionService?.getCurrentUser()?.idSellerPersona,
          this.id
        )
        .subscribe((res) => {
          console.log(res.dato);
          this.datoEmpresa.dato = res.dato.empresa;
          this.datoContacto.listaPersonas = res.dato.listaPersonas;
          this.redSocial.listaRedesSociales = res.dato.listaRedesSociales;
          this.direccion.direccion = res.dato.direccion;
          this.foto = res.dato.foto;
          this.datoEmpresa.setDataEmpresa();
          this.datoContacto.setListaPersonas();
          this.redSocial.setRedSocial();
          this.direccion.setDireccion();
        });
    } else {
      this.datoContacto.agregarPersona();
      this.redSocial.agregarRedSocial();
    }
  }
  imagenChange(file: File) {
    this.fotoTemporal = file;
  }
  guardar() {
    this.invalidDatoEmpresa = false;
    this.invalidDatoPersona = false;
    this.invalidDataRedSocial = false;
    this.invalidDireccion = false;
    if (this.datoEmpresa.formGroup.invalid) {
      this.datoEmpresa.formGroup.markAllAsTouched();
      this.invalidDatoEmpresa = true;
    }
    if (this.datoContacto.formGroup.invalid) {
      this.datoContacto.formGroup.markAllAsTouched();
      this.invalidDatoPersona = true;
    }
    if (this.redSocial.formGroup.invalid) {
      this.redSocial.formGroup.markAllAsTouched();
      this.invalidDataRedSocial = true;
    }
    if (this.direccion.formGroup.invalid) {
      this.direccion.formGroup.markAllAsTouched();
      this.invalidDireccion = true;
    }
    if (
      this.invalidDatoEmpresa ||
      this.invalidDatoPersona ||
      this.invalidDataRedSocial ||
      this.invalidDireccion
    ) {
      return;
    }
    const personas = this.datoContacto.formGroup.value.personas.map(
      (per: Persona) => {
        per.cntPrincipal = per.cntPrincipal ? 1 : 0;
        return per;
      }
    );

    const clieMantDto: EmpresaMantDto = {
      empresa: this.datoEmpresa.formGroup.value,
      direccion: this.direccion.formGroup.value,
      listaRedesSociales: this.redSocial.formGroup.value.redes,
      listaPersonas: personas,
      idPersonaLogeada:
        this.autenticacionService?.getCurrentUser()?.idSellerPersona,
    };
    const mant = new FormData();
    mant.append('jsonDato', JSON.stringify(clieMantDto));
    if (this.fotoTemporal != null) {
      mant.append('image', this.fotoTemporal);
    }
    if (Number(this.id) === -1) {
      this.clienteService.guardar(mant).subscribe(
        (res) => {
          this.cancelar.emit(true);
          this.dialogAlertService.saveSuccess();
        },
        (error) => this.dialogAlertService.errorHttp(error)
      );
    } else {
      this.clienteService.actualizar(mant).subscribe(
        (res) => {
          this.cancelar.emit(true);
          this.dialogAlertService.updateSuccess();
        },
        (error) => this.dialogAlertService.errorHttp(error)
      );
    }
  }
  cerrar() {
    this.cancelar.emit(false);
  }
  deplazarTab(movimiento: string) {
    const factor = movimiento === 'S' ? 1 : -1;
    this.invalidDatoEmpresa = false;
    this.invalidDatoPersona = false;
    this.invalidDataRedSocial = false;
    if (
      this.indexTab === 0 &&
      this.datoEmpresa.formGroup.invalid &&
      factor > 0
    ) {
      this.datoEmpresa.formGroup.markAllAsTouched();
      this.invalidDatoEmpresa = true;
      return;
    }
    if (
      this.indexTab === 1 &&
      this.datoContacto.formGroup.invalid &&
      factor > 0
    ) {
      this.datoContacto.formGroup.markAllAsTouched();
      this.invalidDatoPersona = true;
      return;
    }
    if (this.indexTab === 2 && this.redSocial.formGroup.invalid && factor > 0) {
      this.redSocial.formGroup.markAllAsTouched();
      this.invalidDataRedSocial = true;
      return;
    }
    const indiceTAbActive = this.indexTab + factor;
    this.indexTab = indiceTAbActive;
  }
}
