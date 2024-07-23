export interface IMenu {
  title: string;
  route?: string;
  icon?: string;
  svgIcon?: string;
  close?: boolean;
  ocultar?: boolean;
  children?: IMenu[];
}
