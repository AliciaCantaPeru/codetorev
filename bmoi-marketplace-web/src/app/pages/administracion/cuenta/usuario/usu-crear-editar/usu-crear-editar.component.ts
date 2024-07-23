import { PathApp } from 'src/app/layout/container-page/app-menu/app.menu';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { OpcionPersona, UsuarioMantDto } from '@core/models/usuario.model';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { UsuarioSellerService } from '@core/services/usuario-seller.service';
import { Alert } from '@core/soporte/alert';
import { FormGroupConfig } from '@core/soporte/form-group-config';
import { Opcion } from '@core/soporte/generic.model';
import { ROL_ADMIN } from '@core/soporte/rol';
import { DialogAlertService } from '@core/services/dialog-alert.service';

@Component({
  selector: 'app-usu-crear-editar',
  templateUrl: './usu-crear-editar.component.html',
  styleUrls: ['./usu-crear-editar.component.scss'],
})
export class UsuCrearEditarComponent implements OnInit {
  formGroup: FormGroup;
  listacliente: Opcion[] = [];
  listaPersona: OpcionPersona[] = [];
  listaRol: Opcion[] = [];
  hide = true;
  verCliente = true;
  permitirModificar = false;
  id: number;
  constructor(
    private usuarioService: UsuarioSellerService,
    private autenticacionService: AutenticacionService,
    private fb: FormBuilder,
    public dialogAlertService: DialogAlertService,
    private route: ActivatedRoute,
    private router: Router
  ) {}
  ngOnInit(): void {
    this.route.params.subscribe((param) => (this.id = Number(param.id)));
    this.permitirModificar = this.autenticacionService.permitirModificar();
    this.crearForm();
    this.obtenerUsuarioRol();
    this.listarRol();
  }
  crearForm() {
    const config: FormGroupConfig<UsuarioMantDto> = {
      id: [
        {
          value: this.id != -1 ? this.id : null,
          disabled: this.id != -1,
        },
      ],
      idPersonaLogeada: [
        this.autenticacionService?.getCurrentUser()?.idSellerPersona,
      ],
      idSeller: [
        { value: null, disabled: this.id != -1 },
        [Validators.required],
      ],
      idSellerPersona: [
        { value: null, disabled: this.id != -1 },
        Validators.required,
      ],
      email: [{ value: null, disabled: true }, Validators.required],
      estado: [1, Validators.required],
      idRol: [{ value: null, disabled: false }, [Validators.required]],
    };
    this.formGroup = this.fb.group(config);
    if (!this.permitirModificar) {
      this.formGroup.disable();
    }
  }
  obtenerUsuarioRol() {
    if (this.autenticacionService?.getCurrentUser()?.idRol === ROL_ADMIN) {
      const { idSeller } = this.autenticacionService?.getCurrentUser();
      if (this.id == -1) {
        this.listarPersona(idSeller);
      }
      this.formGroup.patchValue({ idSeller });
      this.verCliente = false;
    }
    if (this.id == -1) {
      this.listarClientes(-1);
      return;
    }
    this.usuarioService
      .obtener(
        this.autenticacionService?.getCurrentUser()?.idSellerPersona,
        this.id
      )
      .subscribe((res) => {
        const { id, email, estado, idSellerPersona, idRol, idSeller } =
          res.dato;
        this.formGroup.patchValue({
          id,
          email,
          estado,
          idSellerPersona,
          idRol,
          idSeller,
        });
        this.listarPersona(idSeller);
        this.selectPersona(idSellerPersona);
        this.listarClientes(idSeller);
      });
  }
  getControl(campo: string) {
    return this.formGroup.get(campo);
  }
  guardar() {
    if (this.formGroup.invalid) {
      this.formGroup.markAllAsTouched();
      return;
    }
    const mant: UsuarioMantDto = this.formGroup.getRawValue();
    if (this.id == -1) {
      this.usuarioService.guardar(mant).subscribe(
        (res) => {
          this.router.navigate([PathApp.routeCuentaSeller]);
          this.dialogAlertService.saveSuccess();
        },
        (error) => this.dialogAlertService.errorHttp(error)
      );
    } else {
      this.usuarioService.actualizar(mant).subscribe(
        (res) => {
          this.router.navigate([PathApp.routeCuentaSeller]);
          this.dialogAlertService.updateSuccess();
        },
        (error) => this.dialogAlertService.errorHttp(error)
      );
    }
  }
  listarClientes(idCliente: number) {
    this.usuarioService
      .listarClientesOptions(
        this.autenticacionService?.getCurrentUser()?.idSellerPersona,
        idCliente
      )
      .subscribe((res) => {
        this.listacliente = res.dato;
      });
  }
  listarPersona(idSeller: number) {
    this.usuarioService
      .listarPersonasOptions(
        this.autenticacionService?.getCurrentUser()?.idSellerPersona,
        idSeller,
        this.id
      )
      .subscribe((res) => (this.listaPersona = res.dato));
  }
  selectPersona(idPersona: number) {
    if (this.listaPersona.length != 0) {
      const persona = this.listaPersona.find((per) => per.id === idPersona);
      const { email } = persona;
      this.formGroup.patchValue({
        email,
      });
    }
  }
  listarRol() {
    this.usuarioService
      .listarRolesOptions(
        this.autenticacionService?.getCurrentUser()?.idSellerPersona
      )
      .subscribe((res) => (this.listaRol = res.dato));
  }
  cancelar() {
    this.router.navigate([PathApp.routeCuentaSeller]);
  }
}
