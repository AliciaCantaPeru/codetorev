import { Injectable } from '@angular/core';
import { Respuesta } from '@core/models/respuesta.model';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CalendarService {

  closeCalendarSource = new BehaviorSubject<boolean>(false);
  isCloseCalander = this.closeCalendarSource.asObservable();

  constructor() { }

  closeCalander(message: boolean) {
    this.closeCalendarSource.next(message)
  }

}
