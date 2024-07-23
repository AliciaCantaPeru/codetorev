import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { Alert } from '@core/soporte/alert';
import { DialogAlertService } from '@core/services/dialog-alert.service';

@Component({
  selector: 'app-recuperar-clave',
  templateUrl: './recuperar-clave.component.html',
  styleUrls: ['./recuperar-clave.component.scss'],
})
export class RecuperarClaveComponent implements OnInit {
  forma: FormGroup;
  public loginInvalid = false;
  secretKey: string;
  send = false;
  tipoUsuario: string;
  constructor(
    private fb: FormBuilder,
    public router: Router,
    public route: ActivatedRoute,
    public dialogAlertService: DialogAlertService,
    private autenticacionService: AutenticacionService
  ) {
    this.crearFormulario();
  }

  ngOnInit(): void {
    this.route.params.subscribe(
      (param) => (this.tipoUsuario = param.tipoUsuario)
    );
  }
  crearFormulario() {
    this.forma = this.fb.group({
      correo: ['', Validators.required],
    });
  }
  get getCorreo() {
    return this.forma.get('correo');
  }
  onSubmit() {
    if (this.forma.invalid) {
      this.forma.markAllAsTouched();
      return;
    }
    this.autenticacionService
      .generarLinkRecuperarContrasenia(
        this.tipoUsuario,
        this.forma.controls.correo.value
      )
      .subscribe(
        (res) => {
          this.send = true;
        },
        (error) => this.dialogAlertService.errorHttp(error)
      );
  }
}
