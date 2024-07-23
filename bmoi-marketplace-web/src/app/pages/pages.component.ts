import { OverlayContainer } from '@angular/cdk/overlay';
import { Component, HostBinding, OnInit } from '@angular/core';

@Component({
  selector: 'app-pages',
  templateUrl: './pages.component.html',
  styleUrls: ['./pages.component.scss'],
})
export class PagesComponent implements OnInit {
  @HostBinding('class') componentCssClass: any;
  constructor(public overlayContainer: OverlayContainer) {}
  ngOnInit(): void {
    this.onSetTheme('theme-light');
  }

  onSetTheme(e: string) {
    this.overlayContainer.getContainerElement().classList.remove('theme-light');
    this.overlayContainer
      .getContainerElement()
      .classList.remove('theme-alternate');
    this.overlayContainer.getContainerElement().classList.add(e);
    this.componentCssClass = e;
  }
}
