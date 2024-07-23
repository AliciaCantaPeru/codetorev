import { Directive, TemplateRef, Input, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

@Directive({
    selector: '[vistaTemplate]'
})
export class TemplateDirective {

    @Input() type: string;
    @Input() vistaTemplate: string;

    constructor(public template: TemplateRef<any>) { }

    getType(): string {
        return this.vistaTemplate;
    }

}
@NgModule({
    imports: [CommonModule],
    exports: [TemplateDirective],
    declarations: [TemplateDirective]
})
export class TemplateDirectiveModule { }
