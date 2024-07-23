import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { Router } from '@angular/router';
import { PathApp } from 'src/app/layout/container-page/app-menu/app.menu';

@Component({
  selector: 'app-toolbar-index',
  templateUrl: './toolbar-index.component.html',
  styleUrls: ['./toolbar-index.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ToolbarIndexComponent implements OnInit {
  constructor(private router: Router) {}

  ngOnInit(): void {}
  irLogin() {
    this.router.navigate([PathApp.routeLogin]);
  }
  irIndex() {
    this.router.navigate([PathApp.routeIndex]);
  }
  idDescargas() {
    this.router.navigate([PathApp.routeDescarga]);
  }
}
