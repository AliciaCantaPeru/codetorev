import { IColumnMat } from '@core/models/util.model';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterColumn'
})
export class FilterColumnPipe implements PipeTransform {

  transform(values: IColumnMat[], ...args: string[]): unknown {
    if (!values) return [];
    if (!values || values.length == 0) return values;
    return values.filter(value => args.indexOf(value.column) < 0);
  }

}
