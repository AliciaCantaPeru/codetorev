import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ParametroIn } from '@core/models/general.model';
import { TipoCambioMant, TipoCambioOut } from '@core/models/tipo-cambio.model';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { DialogAlertService } from '@core/services/dialog-alert.service';
import { GeneralService } from '@core/services/general.service';
import { TipoCambioService } from '@core/services/tipo-cambio.service';
import { Alert } from '@core/soporte/alert';
import { Parametro } from '@core/soporte/enums';
import { FormGroupConfig } from '@core/soporte/form-group-config';

@Component({
  selector: 'app-crear-editar-tipo-cambio',
  templateUrl: './crear-editar-tipo-cambio.component.html',
  styleUrls: ['./crear-editar-tipo-cambio.component.scss'],
})
export class CrearEditarTipoCambioComponent implements OnInit {
  formGroup: FormGroup;
  hide = true;
  listaTipoMoneda: ParametroIn[] = [];
  permitirModificar = false;
  constructor(
    public dialogRef: MatDialogRef<CrearEditarTipoCambioComponent>,
    private tipoCambioService: TipoCambioService,
    public dialogAlertService: DialogAlertService,
    private generalService: GeneralService,
    private autenticacionService: AutenticacionService,
    private fb: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public data: { id; moneda }
  ) {}

  ngOnInit(): void {
    this.permitirModificar = this.autenticacionService.permitirModificar();
    this.crearForm();
    this.obtenerTipoCambio();
    this.listaTipos();
  }

  crearForm() {
    const moneda = this.data?.moneda ? this.data.moneda : null;
    const disabled = moneda !== null;
    const config: FormGroupConfig<TipoCambioMant> = {
      id: [this.data?.id],
      idPersonaLogeada: [
        this.autenticacionService?.getCurrentUser()?.idPersona,
      ],
      moneda: [{ value: moneda, disabled: disabled }, Validators.required],
      valCompra: [null, Validators.required],
      valVenta: [null, Validators.required],
    };
    this.formGroup = this.fb.group(config);
    if (!this.permitirModificar) {
      this.formGroup.disable();
    }
  }
  obtenerTipoCambio() {
    if (this.data?.id == null) {
      return;
    }
    this.tipoCambioService
      .obtener(
        this.autenticacionService?.getCurrentUser()?.idPersona,
        this.data.id
      )
      .subscribe((res) => {
        const { id, moneda, valCompra, valVenta } = res.dato;
        this.formGroup.patchValue({ id, moneda, valCompra, valVenta });
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
    const mant: TipoCambioMant = this.formGroup.getRawValue();
    if (this.data?.id == null) {
      this.tipoCambioService.guardar(mant).subscribe(
        (res) => {
          this.dialogRef.close(mant);
          this.dialogAlertService.saveSuccess();
        },
        (error) => this.dialogAlertService.errorHttp(error)
      );
    } else {
      this.tipoCambioService.actualizar(mant).subscribe(
        (res) => {
          this.dialogRef.close(mant);
          this.dialogAlertService.updateSuccess();
        },
        (error) => this.dialogAlertService.errorHttp(error)
      );
    }
  }
  listaTipos() {
    // this.listaTipoMoneda = this.generalService
    //   .listarParamatros(Parametro.MONEDA)
    //   .filter((cam) => cam.valor !== 'Soles');
  }
}
