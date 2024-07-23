import { AuxProService } from './../pro-crear-editar.service';
import { FormGroup } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-envio',
  templateUrl: './envio.component.html',
  styleUrls: ['./envio.component.scss'],
})
export class EnvioComponent implements OnInit {
  formGroup: FormGroup;
  constructor(public auxProService: AuxProService) {}

  ngOnInit(): void {
    this.formGroup = this.auxProService.formGroup;
  }
}
