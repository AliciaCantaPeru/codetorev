export class Validacion {
  static validarSoloNumeros(event) {
    return event.charCode === 0 || (event.charCode >= 48 && event.charCode <= 57);
  }

}
