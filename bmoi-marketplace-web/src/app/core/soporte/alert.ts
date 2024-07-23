import Swal from 'sweetalert2';

export class Alert {
  public static updateSuccess() {
    this.success('El registro se actualizó con éxito');
  }

  public static saveSuccess() {
    this.success('El registro se guardo con éxito');
  }

  public static deleteSuccess() {
    this.success('El registro fue eliminado con éxito');
  }

  public static errorHttp(error) {
    const messageError = error?.error?.mensaje
      ? error?.error?.mensaje
      : 'Lo sentimos no se pudo realizar la operación';
    this.warning(messageError);
  }

  public static success(message: string) {
    Swal.fire({
      icon: 'success',
      title: 'Operación completada',
      text: message,
      heightAuto: false,
      customClass: this.getStyleButton(),
      buttonsStyling: false,
    });
  }

  public static loginMesage(message: string) {
    Swal.fire({
      icon: 'success',
      title: 'Bienvenido',
      text: message,
      heightAuto: false,
      customClass: this.getStyleButton(),
      buttonsStyling: false,
    });
  }

  public static error(message: string) {
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: message,
      heightAuto: false,
      customClass: this.getStyleButton(),
      buttonsStyling: false,
    });
  }

  public static info(message: string) {
    Swal.fire({
      icon: 'info',
      title: 'Aviso',
      text: message,
      heightAuto: false,
      customClass: this.getStyleButton(),
      buttonsStyling: false,
    });
  }

  public static warning(message: string) {
    Swal.fire({
      icon: 'warning',
      title: 'Advertencia',
      text: message,
      heightAuto: false,
      customClass: this.getStyleButton(),
      buttonsStyling: false,
    });
  }

  public static loading() {
    Swal.fire({
      allowOutsideClick: false,
      icon: 'info',
      text: 'Cargando',
      heightAuto: false,
    });
    Swal.showLoading();
  }

  public static close() {
    Swal.close();
  }

  public static getStyleButton() {
    return {
      confirmButton:
        'mat-primary-alert mat-raised-button mat-button-base mat-primary',
    };
  }
}
