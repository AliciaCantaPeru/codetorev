import { Component, OnInit } from '@angular/core';
import { LoadingService } from '@core/services/loading.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-seguridad',
  templateUrl: './seguridad.component.html',
  styleUrls: ['./seguridad.component.scss'],
})
export class SeguridadComponent implements OnInit {
  loading: Observable<boolean>;
  constructor(private loadingService: LoadingService) {}

  ngOnInit(): void {
    this.loading = this.loadingService.change().pipe(map((loading) => loading));
  }
}
