import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AutocompleteObjectComponent } from './autocomplete-object.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '@shared/material/material.module';
@NgModule({
  declarations: [AutocompleteObjectComponent],
  imports: [CommonModule, MaterialModule, FormsModule, ReactiveFormsModule],
  exports: [AutocompleteObjectComponent],
})
export class AutocompleteObjectModule {}
