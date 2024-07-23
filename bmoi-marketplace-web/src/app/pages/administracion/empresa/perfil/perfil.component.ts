import { DatePipe } from '@angular/common';
import {
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output,
  ViewChild,
} from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { EmpresaMantDto, Persona } from '@core/models/seller.model';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { SellerService } from '@core/services/seller.service';
import { NegocioService } from '@core/services/negocio.service';
import { ProveedorService } from '@core/services/proveedor.service';
import { PathApp } from 'src/app/layout/container-page/app-menu/app.menu';
import { FormContactoComponent } from '@shared/components/form-contacto/form-contacto.component';
import { FormDatoEmpresaComponent } from '@shared/components/form-dato-empresa/form-dato-empresa.component';
import { FormDireccionComponent } from '@shared/components/form-direccion/form-direccion.component';
import { FormRedSocialComponent } from '@shared/components/form-red-social/form-red-social.component';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.scss'],
})
export class PerfilComponent implements OnInit {
  tipo = 1;
  @Output() cancelar: EventEmitter<boolean> = new EventEmitter();
  @ViewChild(FormDatoEmpresaComponent, { static: true })
  datoEmpresa: FormDatoEmpresaComponent;
  @ViewChild(FormContactoComponent, { static: true })
  datoContacto: FormContactoComponent;
  @ViewChild(FormRedSocialComponent, { static: true })
  redSocial: FormRedSocialComponent;
  @ViewChild(FormDireccionComponent, { static: true })
  direccion: FormDireccionComponent;
  foto = null;
  constructor(
    private clienteService: SellerService,
    private autenticacionService: AutenticacionService
  ) {}

  ngOnInit(): void {
    this.datoEmpresa.crearForm();
    this.redSocial.crearForm();
    this.direccion.crearForm();
    this.datoContacto.crearForm();
    this.obtenerDato();
    this.datoEmpresa.formGroup.disable();
    this.direccion.formGroup.disable();
  }

  obtenerDato() {
    this.clienteService
      .obtener(
        this.autenticacionService?.getCurrentUser()?.idSellerPersona,
        this.autenticacionService?.getCurrentUser()?.idSeller
      )
      .subscribe((res) => {
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
  }
}
