import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RolMantDto } from '@core/models/rol.model';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { DialogAlertService } from '@core/services/dialog-alert.service';
import { RolService } from '@core/services/rol.service';
import { Alert } from '@core/soporte/alert';
import { FormGroupConfig } from '@core/soporte/form-group-config';

@Component({
  selector: 'app-rol-crear-editar',
  templateUrl: './rol-crear-editar.component.html',
  styleUrls: ['./rol-crear-editar.component.scss'],
})
export class RolCrearEditarComponent implements OnInit {
  formGroup: FormGroup;
  permitirModificar = false;
  constructor(
    public dialogRef: MatDialogRef<RolCrearEditarComponent>,
    public dialogAlertService: DialogAlertService,
    private rolService: RolService,
    private autenticacionService: AutenticacionService,
    private fb: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public data: RolMantDto
  ) {}

  ngOnInit(): void {
    this.permitirModificar = this.autenticacionService.permitirModificar();
    this.crearForm();
  }
  crearForm() {
    const config: FormGroupConfig<RolMantDto> = {
      id: [{ value: this.data.id, disabled: true }, [Validators.required]],
      nombre: [
        { value: this.data.nombre, disabled: true },
        [Validators.required],
      ],
      descripcion: [this.data.descripcion, [Validators.required]],
      estado: [
        { value: this.data.estado, disabled: true },
        [Validators.required],
      ],
    };
    this.formGroup = this.fb.group(config);
    if (!this.permitirModificar) {
      this.formGroup.disable();
    }
  }

  actualizarRol() {
    if (this.formGroup.invalid) {
      this.formGroup.markAllAsTouched();
      return;
    }
    const mant: RolMantDto = {
      id: this.formGroup.controls.id.value,
      descripcion: this.formGroup.controls.descripcion.value,
    };
    this.rolService
      .actualizar(
        this.autenticacionService?.getCurrentUser()?.idSellerPersona,
        mant
      )
      .subscribe(
        (res) => {
          this.dialogRef.close(mant);
          this.dialogAlertService.updateSuccess();
        },
        (error) => this.dialogAlertService.errorHttp(error)
      );
  }
  getControl(campo: string) {
    return this.formGroup.get(campo);
  }
}
