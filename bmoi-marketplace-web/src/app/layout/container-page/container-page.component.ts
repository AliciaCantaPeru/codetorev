import { PerfectScrollbarConfigInterface } from 'ngx-perfect-scrollbar';
import {
  Component,
  OnInit,
  ChangeDetectionStrategy,
  OnDestroy,
  AfterViewInit,
  HostBinding,
  ViewEncapsulation,
} from '@angular/core';
import { environment } from 'src/environments/environment';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { AutUsuarioIn } from '@core/models/autenticacion.model';
import { LoadingService } from '@core/services/loading.service';
import { Observable, Subscription } from 'rxjs';
import { map } from 'rxjs/operators';
import { GeneralService } from '@core/services/general.service';
import { MatDrawer } from '@angular/material/sidenav';
import { OverlayContainer } from '@angular/cdk/overlay';
import { IconRegistryService } from '@core/services/icon-registry.service';
import { DashboardService } from '@core/services/dashboard.service';

@Component({
  selector: 'app-container-page',
  templateUrl: './container-page.component.html',
  styleUrls: ['./container-page.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ContainerPageComponent implements OnInit, OnDestroy {
  title;
  version = environment.version;
  usuario: AutUsuarioIn;
  loading: Observable<boolean>;
  configData: PerfectScrollbarConfigInterface = { suppressScrollX: true };
  @HostBinding('class') componentCssClass: any;
  diasRestante = '--';
  constructor(
    public overlayContainer: OverlayContainer,
    private autenticacionService: AutenticacionService,
    private loadingService: LoadingService,
    private dashboardService: DashboardService
  ) {
    this.usuario = autenticacionService.getCurrentUser();
  }
  ngOnDestroy(): void {}

  ngOnInit(): void {
    this.getDiasRestantes();
    this.onSetTheme(this.autenticacionService.getCurrentUser().temaApp);
    this.loading = this.loadingService.change().pipe(map((loading) => loading));
  }
  getDiasRestantes() {
    this.dashboardService
      .obtenerDato(1)
      .subscribe((res) => (this.diasRestante = res.dato));
  }
  onSetTheme(e: string) {
    console.warn(
      'onSetTheme',
      this.autenticacionService.getCurrentUser().temaApp
    );
    this.overlayContainer.getContainerElement().classList.remove('theme-light');
    this.overlayContainer
      .getContainerElement()
      .classList.remove('theme-alternate');
    this.overlayContainer.getContainerElement().classList.add(e);
    this.componentCssClass = e;
  }
  changeTitle($title, drawer: MatDrawer) {
    this.title = $title;
  }

  logout() {
    this.autenticacionService.logout();
  }
}
