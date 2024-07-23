import { ActivatedRoute, Router } from '@angular/router';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { AutenticacionService } from '@core/services/autenticacion.service';
import {
  CambiarContraseniaDto,
  KeyOutDto,
} from '@core/models/autenticacion.model';

@Component({
  selector: 'app-activar-cuenta',
  templateUrl: './activar-cuenta.component.html',
  styleUrls: ['./activar-cuenta.component.scss'],
})
export class ActivarCuentaComponent implements OnInit {
  forma: FormGroup;
  hide1 = false;
  hide2 = false;
  send = false;
  keyInvalid = false;
  messageError = '';
  constructor(
    private fb: FormBuilder,
    public router: Router,
    private activatedRoute: ActivatedRoute,
    private autenticacionService: AutenticacionService
  ) {
    this.crearFormulario();
  }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(
      (params: { key: string }) => {
        if (!params?.key) {
          this.keyInvalid = true;
          this.messageError = 'Lo sentimos no se puede realizar la operación';
        } else {
          const key = params?.key.replace(/ /g, '%2B');
          this.verificarKey(key);
        }
      },
      (error) => {
        this.keyInvalid = true;
        this.messageError = 'Lo sentimos no se puede realizar la operación';
      }
    );
  }

  verificarKey(key: string) {
    const keyIn: KeyOutDto = {
      valor: key,
    };
    this.autenticacionService.verificarKey(keyIn).subscribe(
      (res) => this.forma.patchValue({ key: key }),
      (error) => {
        this.keyInvalid = true;
        this.messageError = error?.error?.mensaje
          ? error?.error?.mensaje
          : 'Lo sentimos no se puede realizar la operación';
      }
    );
  }

  crearFormulario() {
    this.forma = this.fb.group({
      key: ['', Validators.required],
      contrasenia: [
        '',
        Validators.compose([
          Validators.required,
          Validators.pattern(
            '^(?=(?:.*[$@$!%*?&]){1})(?=(?:.*(\\d|[$@])){1})(?=(?:.*[A-Z]){1})(?=(?:.*[a-z]){1})\\S{8,}$'
          ),
        ]),
      ],
      contraseniaConfirmacion: ['', Validators.required],
    });
    this.forma.controls.contraseniaConfirmacion.valueChanges.subscribe(
      (value) => {
        const contrasenia = this.forma.controls.contrasenia?.value;
        if (value !== contrasenia) {
          this.forma.controls.contraseniaConfirmacion.setErrors({
            comparar: true,
          });
        } else {
          this.forma.controls.contraseniaConfirmacion.setErrors(null);
        }
      }
    );
    this.forma.controls.contrasenia.valueChanges.subscribe((value) => {
      const contrasenia = this.forma.controls.contraseniaConfirmacion?.value;
      if (value !== contrasenia) {
        this.forma.controls.contraseniaConfirmacion.setErrors({
          comparar: true,
        });
      } else {
        this.forma.controls.contraseniaConfirmacion.setErrors(null);
      }
    });
  }
  onSubmit() {
    if (this.forma.invalid) {
      this.forma.markAllAsTouched();
      return;
    }
    const { key, contrasenia } = this.forma.value;
    const data: CambiarContraseniaDto = { key, contrasenia };
    this.autenticacionService.activarCuenta(data).subscribe((res) => {
      this.send = true;
    });
  }
}
