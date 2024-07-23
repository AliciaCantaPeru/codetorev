import { DialogAlertService } from '@core/services/dialog-alert.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AutLoginOut } from '@core/models/autenticacion.model';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { AuthTokenService } from '@core/services/auth-token.service';
import { Alert } from '@core/soporte/alert';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-autenticacion',
  templateUrl: './autenticacion.component.html',
  styleUrls: ['./autenticacion.component.scss'],
})
export class AutenticacionComponent implements OnInit {
  forma: FormGroup;
  hide = true;
  constructor(
    private fb: FormBuilder,
    public router: Router,
    private autenticacionService: AutenticacionService,
    public dialogAlertService: DialogAlertService,
    private authTokenService: AuthTokenService
  ) {
    this.crearFormulario();
  }

  ngOnInit(): void {}

  crearFormulario() {
    this.forma = this.fb.group({
      correo: [
        null,
        Validators.compose([Validators.required, Validators.email]),
      ],
      contrasenia: [null, Validators.compose([Validators.required])],
      tipoUsuario: ['BMOI', Validators.compose([Validators.required])],
      recordar: [this.authTokenService.getRecordarCuenta()],
    });
    this.forma.controls.recordar.valueChanges.subscribe((value) => {
      this.authTokenService.changeRecordarCuenta(value);
    });
  }
  get getusername() {
    return this.forma.get('username');
  }
  get getpassword() {
    return this.forma.get('password');
  }

  onSubmit() {
    if (this.forma.invalid) {
      this.forma.markAllAsTouched();
      return;
    }
    const loginOut: AutLoginOut = this.forma.value;
    this.autenticacionService.login(loginOut).subscribe(
      (res) => {
        // this.dialogAlertService.loginMesage(res.dato.nombres);
        this.router.navigate(['/dashboard']);
      },
      (error) => {
        const messageError = error?.error?.mensaje
          ? 'Credenciales incorrectas'
          : 'Lo sentimos no se pudo realizar la operación';
        this.dialogAlertService.mostrarWarning(messageError);
      }
    );
  }

  recuperarClave() {
    this.router.navigate([
      '/recuperar-clave',
      this.forma.get('tipoUsuario').value,
    ]);
  }
}
