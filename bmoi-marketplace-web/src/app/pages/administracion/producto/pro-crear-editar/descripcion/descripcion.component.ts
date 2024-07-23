import { AuxProService } from './../pro-crear-editar.service';
import { QuillConfiguration } from './quill-configuration';
import { Component, EventEmitter, OnInit, Output, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-descripcion',
  templateUrl: './descripcion.component.html',
  styleUrls: ['./descripcion.component.scss'],
})
export class DescripcionComponent implements OnInit {
  formGroup: FormGroup;
  name = 'Angular 6';
  htmlContent = '';
  editorStyle = {
    height: '200px',
  };
  quillConfiguration = QuillConfiguration;

  constructor(private auxProService: AuxProService) {}

  ngOnInit(): void {
    this.formGroup = this.auxProService.formGroup;
  }
}
