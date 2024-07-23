import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { PathApp } from 'src/app/layout/container-page/app-menu/app.menu';

@Component({
  selector: 'app-cli-crear-editar',
  templateUrl: './cli-crear-editar.component.html',
  styleUrls: ['./cli-crear-editar.component.scss'],
})
export class CliCrearEditarComponent {
  constructor(private router: Router) {}
  cancelar($event) {
    this.router.navigate([PathApp.routeSeller]);
  }
}
