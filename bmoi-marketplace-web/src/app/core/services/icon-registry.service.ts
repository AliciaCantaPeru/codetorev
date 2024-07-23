import { DomSanitizer } from '@angular/platform-browser';
import { MatIconRegistry } from '@angular/material/icon';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class IconRegistryService {

  constructor(
    private matIconRegistry: MatIconRegistry,
    private domSanitizer: DomSanitizer
  ) {
    this.matIconRegistry
      .addSvgIcon(
        `i-productos`,
        this.domSanitizer.bypassSecurityTrustResourceUrl("../../../assets/ico/ico-productos.svg"))
      .addSvgIcon(
        `i-cuentas`,
        this.domSanitizer.bypassSecurityTrustResourceUrl("../../../assets/ico/ico-cuentas.svg"))
      .addSvgIcon(
        `i-proveedores`,
        this.domSanitizer.bypassSecurityTrustResourceUrl("../../../assets/ico/ico-proveedores.svg"))
      .addSvgIcon(
        `i-clientes`,
        this.domSanitizer.bypassSecurityTrustResourceUrl("../../../assets/ico/ico-clientes.svg"))
      .addSvgIcon(
        `i-dashboard`,
        this.domSanitizer.bypassSecurityTrustResourceUrl("../../../assets/ico/ico-dashboard.svg"))
      .addSvgIcon(
        `i-ventas`,
        this.domSanitizer.bypassSecurityTrustResourceUrl("../../../assets/ico/ico-ventas.svg"));
  }
}
