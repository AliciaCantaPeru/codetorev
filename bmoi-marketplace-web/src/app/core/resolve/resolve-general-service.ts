import { Injectable } from '@angular/core';
import { Resolve } from '@angular/router';
import { GeneralService } from '@core/services/general.service';

@Injectable({
  providedIn: 'root',
})
export class ResolveGeneralService implements Resolve<any> {
  constructor(private generalService: GeneralService) {}
  async resolve() {
    return await this.generalService.obtener();
  }
}
