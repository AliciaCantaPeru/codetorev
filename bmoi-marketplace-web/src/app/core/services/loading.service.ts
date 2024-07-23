import { Injectable } from '@angular/core';
import { pipe, Subject } from 'rxjs';
import { distinctUntilChanged } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class LoadingService {
  private loading: boolean;
  private subject = new Subject<boolean>();
  constructor() {}
  loadingStatus(mostrar: boolean) {
    this.loading = mostrar;
    this.subject.next(this.loading);
  }
  change() {
    return this.subject.asObservable().pipe(distinctUntilChanged());
  }
}
