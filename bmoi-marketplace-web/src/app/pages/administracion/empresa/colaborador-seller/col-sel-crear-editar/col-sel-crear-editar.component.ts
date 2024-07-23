import { PathApp } from './../../../../../layout/container-page/app-menu/app.menu';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Persona } from '@core/models/seller.model';
import { ColaboradorClienteMant } from '@core/models/colaborador-cliente.model';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { Alert } from '@core/soporte/alert';
import { FormContactoComponent } from '@shared/components/form-contacto/form-contacto.component';
import { ColaboradorSellerService } from '@core/services/colaborador-seller.service';
import { DialogAlertService } from '@core/services/dialog-alert.service';

@Component({
  selector: 'app-col-sel-crear-editar',
  templateUrl: './col-sel-crear-editar.component.html',
  styleUrls: ['./col-sel-crear-editar.component.scss'],
})
export class ColSelCrearEditarComponent implements OnInit {
  @ViewChild(FormContactoComponent, { static: true })
  datoContacto: FormContactoComponent;
  foto: string = null;
  fotoTemporal: File = null;
  permitirModificar = false;
  id;
  constructor(
    private colaboradorService: ColaboradorSellerService,
    public dialogAlertService: DialogAlertService,
    private autenticacionService: AutenticacionService,
    private route: ActivatedRoute,
    private router: Router
  ) {}
  ngOnInit(): void {
    this.route.params.subscribe(
      (param) => (this.id = Number(param.id) == -1 ? null : Number(param.id))
    );
    this.permitirModificar = this.autenticacionService.permitirModificar();
    this.datoContacto.crearForm();
    this.datoContacto.agregarPersona();
    this.obtenerDato();
  }

  obtenerDato() {
    if (this.id !== null) {
      this.colaboradorService
        .obtener(
          this.autenticacionService?.getCurrentUser()?.idSellerPersona,
          this.id
        )
        .subscribe((res) => {
          const {
            id,
            idSeller,
            priNombre,
            segNombre,
            priApellido,
            segApellido,
            fecNacimiento,
            sexo,
            tipDocumento,
            numDocumento,
            email,
            celular1,
            celular2,
            cargo,
            foto,
          } = res.dato;
          this.datoContacto.listaPersonas.push({
            id,
            idSeller,
            priNombre,
            segNombre,
            priApellido,
            segApellido,
            fecNacimiento,
            sexo,
            tipDocumento,
            numDocumento,
            email,
            celular1,
            celular2,
            cargo,
          });
          this.foto = foto;
          this.datoContacto.setOnePersona();
        });
    }
  }
  imagenChange(file: File) {
    this.fotoTemporal = file;
  }
  guardar() {
    if (this.datoContacto.formGroup.invalid) {
      this.datoContacto.formGroup.markAllAsTouched();
      return;
    }
    const personas = this.datoContacto.formGroup.value.personas.map(
      (per: Persona) => {
        per.cntPrincipal = 1;
        return per;
      }
    );
    const manDto: ColaboradorClienteMant = personas[0];
    manDto.idPersonaLogeada =
      this.autenticacionService?.getCurrentUser()?.idSellerPersona;
    manDto.idSeller = this.autenticacionService?.getCurrentUser()?.idSeller;
    const mant = new FormData();
    mant.append('jsonDato', JSON.stringify(manDto));
    if (this.fotoTemporal != null) {
      mant.append('image', this.fotoTemporal);
    }
    if (this.id === null) {
      this.colaboradorService.guardar(mant).subscribe(
        (res) => {
          this.router.navigate([PathApp.routeColSeller]);
          this.dialogAlertService.saveSuccess();
        },
        (error) => this.dialogAlertService.errorHttp(error)
      );
    } else {
      this.colaboradorService.actualizar(mant).subscribe(
        (res) => {
          this.router.navigate([PathApp.routeColSeller]);
          this.dialogAlertService.updateSuccess();
        },
        (error) => this.dialogAlertService.errorHttp(error)
      );
    }
  }
  cancelar() {
    this.router.navigate([PathApp.routeColSeller]);
  }
}
