import { AuxProService } from './../pro-crear-editar.service';
import { FormGroup } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dimension-peso',
  templateUrl: './dimension-peso.component.html',
  styleUrls: ['./dimension-peso.component.scss'],
})
export class DimensionPesoComponent implements OnInit {
  formGroup: FormGroup;
  constructor(public auxProService: AuxProService) {}

  ngOnInit(): void {
    this.formGroup = this.auxProService.formGroup;
  }
}
