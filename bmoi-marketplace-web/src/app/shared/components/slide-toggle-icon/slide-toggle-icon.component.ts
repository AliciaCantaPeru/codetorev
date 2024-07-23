import {
  Component,
  Input,
  ViewEncapsulation,
  ChangeDetectionStrategy,
  forwardRef,
} from '@angular/core';
import { NG_VALUE_ACCESSOR } from '@angular/forms';
import { MatSlideToggle } from '@angular/material/slide-toggle';

@Component({
  selector: 'mat-slide-toggle-icon',
  exportAs: 'matSlideToggleIcon',

  // copied from Material
  host: {
    class: 'mat-slide-toggle mat-slide-toggle-icon',
    '[id]': 'id',
    '[attr.tabindex]': 'disabled ? null : -1',
    '[attr.aria-label]': 'null',
    '[attr.aria-labelledby]': 'null',
    '[class.mat-checked]': 'checked',
    '[class.mat-disabled]': 'disabled',
    '[class.mat-slide-toggle-label-before]': 'labelPosition == "before"',
    '[class._mat-animation-noopable]': '_animationMode === "NoopAnimations"',
    '(focus)': '_inputElement.nativeElement.focus()',
  },
  templateUrl: './slide-toggle-icon.component.html',
  styleUrls: ['./slide-toggle-icon.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => SlideToggleIconComponent),
      multi: true,
    },
  ],
  inputs: ['disabled', 'disableRipple', 'color', 'tabIndex'],
  // encapsulation: ViewEncapsulation.None,
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SlideToggleIconComponent extends MatSlideToggle {
  @Input() enabledIcon: string = 'visibility';
  @Input() disabledIcon: string = 'visibility_off';
}
