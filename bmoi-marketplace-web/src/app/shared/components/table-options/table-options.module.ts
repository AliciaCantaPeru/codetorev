import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatMenuModule } from '@angular/material/menu';
import { MatInputModule } from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableOptionsComponent } from './table-options.component';
import { MatDividerModule } from '@angular/material/divider';
import { DirectivesModule } from '@shared/directives/directives.module';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { TableActionsModule } from '../table-actions/table-actions.module';
import { ButtonAddModule } from '../button-add/button-add.module';
@NgModule({
  declarations: [TableOptionsComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    DirectivesModule,
    MatInputModule,
    MatMenuModule,
    MatCheckboxModule,
    MatIconModule,
    MatButtonModule,
    MatDividerModule,
    MatSelectModule,
    MatAutocompleteModule,
    TableActionsModule,
    ButtonAddModule,
  ],
  exports: [TableOptionsComponent, TableActionsModule, ButtonAddModule],
})
export class TableOptionsModule {}
