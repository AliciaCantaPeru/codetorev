import { DialogAlertService } from '@core/services/dialog-alert.service';
import { Alert } from '@core/soporte/alert';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import {
  ChangeDetectionStrategy,
  Component,
  Input,
  OnInit,
} from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormGroup,
  Validators,
  FormControl,
} from '@angular/forms';
import { RedSocial } from '@core/models/seller.model';
import { OpcioIn, ParametroIn } from '@core/models/general.model';
import { AutenticacionService } from '@core/services/autenticacion.service';
import { GeneralService } from '@core/services/general.service';
import { OpcionEnum, Parametro } from '@core/soporte/enums';
import { FormGroupConfig } from '@core/soporte/form-group-config';
import { AuxProService } from '../../pro-crear-editar.service';
import { UtilArchivoService } from '@core/services/util-archivo.service';
import { MatCheckboxChange } from '@angular/material/checkbox';
@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.scss'],
})
export class UploadFileComponent implements OnInit {
  @Input() esPerfil: boolean;
  formGroup: FormGroup;
  listaRedesSociales: RedSocial[] = [];
  listaRedes: OpcioIn[] = [];
  permitirModificar = false;
  extensionVideo: string[] = null;
  extensionImage: string[] = null;
  constructor(
    private fb: FormBuilder,
    public auxProService: AuxProService,
    public dialogAlertService: DialogAlertService,
    private generalService: GeneralService,
    private autenticacionService: AutenticacionService,
    public utilArchivoService: UtilArchivoService
  ) {}

  ngOnInit(): void {
    this.formGroup = this.auxProService.formGroup;
    this.extensionVideo = this.generalService
      .listarParamatros(Parametro.FORMATO_CARGA_VIDEO)[0]
      .valor.split(',');
    this.extensionImage = this.generalService
      .listarParamatros(Parametro.FORMATO_CARGA_IMAGEN)[0]
      .valor.split(',');
    this.listaRedes = this.generalService.listarDatoOpciones(
      OpcionEnum.REDES_SOCIALES
    );
  }

  remove(index: number) {
    this.auxProService.removeAtListaMultimediaForm(index);
  }
  changeCheckBox(event: MatCheckboxChange, item: FormGroup) {
    if (event.checked) {
      const countChecked = this.auxProService
        .getFormArray('listaMultimedia')
        .controls.filter((cont) => cont.get('miniatura').value == true).length;
      if (countChecked > 2) {
        this.dialogAlertService.mostrarInfo(
          'Solo se puede seleccionar dos miniaturas'
        );
        item.get('miniatura').setValue(false);
        return;
      }
    }
  }
}
