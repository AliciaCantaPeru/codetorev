import { PathApp } from './../app-menu/app.menu';
import { environment } from './../../../../environments/environment';
import { MatDrawer } from '@angular/material/sidenav';
import {
  Component,
  Input,
  OnInit,
  HostBinding,
  ViewEncapsulation,
  Output,
  EventEmitter,
} from '@angular/core';
import { OverlayContainer } from '@angular/cdk/overlay';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { MatSlideToggleChange } from '@angular/material/slide-toggle';
import { GeneralService } from '@core/services/general.service';

@Component({
  selector: 'app-toolbar-principal',
  templateUrl: './toolbar-principal.component.html',
  styleUrls: ['./toolbar-principal.component.scss'],
})
export class ToolbarPrincipalComponent implements OnInit {
  @Input() drawer: MatDrawer;
  @Input() diasRestante: string;
  @HostBinding('class') componentCssClass: any;
  @Output() onSetTheme = new EventEmitter<string>();
  version = environment.version;
  themeToggle = false;
  usuario;
  isToggleChecked = false;
  toggleOptions = {
    barColor: 'lightgreen',
    spotColor: 'rgb(255,0,0)',
    iconColor: 'white',
    width: 60,
  };
  constructor(
    public overlayContainer: OverlayContainer,
    private autenticacionService: AutenticacionService
  ) {}

  ngOnInit(): void {
    this.themeToggle =
      this.autenticacionService.getCurrentUser().temaApp == 'theme-light';
    this.usuario = this.autenticacionService.getCurrentUser();
  }
  changeTheme(event: MatSlideToggleChange) {
    //console.log(event.checked);
    let tema = '';
    if (event.checked) {
      tema = 'theme-light';
    } else {
      tema = 'theme-alternate';
    }
    this.autenticacionService
      .guardarTema(
        this.autenticacionService.getCurrentUser().idSellerPersona,
        tema
      )
      .subscribe((res) => {
        this.onSetThemes(tema);
      });
  }
  onSetThemes(e: string) {
    this.onSetTheme.emit(e);
  }
  logout() {
    PathApp.appBmoi.forEach((path) => (path.close = false));
    PathApp.appSeller.forEach((path) => (path.close = false));
    this.autenticacionService.logout();
  }
}
