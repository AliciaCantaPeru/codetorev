import { FormGroupConfig } from '@core/soporte/form-group-config';
import { FormArray, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Injectable } from '@angular/core';
import {
  DimensionMantDto,
  ProductoImagenDto,
  ListaMultimediaMantDto,
  ListaPersonalizacionMantDto,
  ListaVarianteMantDto,
  ProductoImagenPropioDto,
  ProductoCategoriaDto,
  ProductoMantDto,
} from '@core/models/producto.model';
import { BehaviorSubject } from 'rxjs';
import { id } from 'date-fns/locale';
import { AutenticacionService } from '@core/services/autenticacion.service';

@Injectable({
  providedIn: 'root',
})
export class AuxProService {
  formGroupSubject: BehaviorSubject<FormGroup>;
  constructor(
    private fb: FormBuilder,
    private autenticacionService: AutenticacionService
  ) {}

  private getFormGroup() {
    return {
      id: [null],
      avgstar: [null],
      descripcion: [null, Validators.required],
      destacado: [null],
      enoferta: [null],
      enviogratis: [null],
      f2: [null],
      f7: [null],
      f8: [null],
      f9: [null],
      idBrand: [null, Validators.required],
      estado: [null, Validators.required],
      codigoMenu: [null, Validators.required],
      codigo: [null],
      idSellerchat: [null],
      impuesto: [null, Validators.required],
      iniciodigital: [null],
      dimension: this.fb.group({
        id: [null],
        altura: [null],
        anchura: [null],
        peso: [null, Validators.required],
        profundida: [null],
      }),
      listaCategorias: this.fb.array([], Validators.required),
      listaImg: this.fb.array([], Validators.required),
      listaImgPropio: this.fb.array([], Validators.required),
      listaGrupoCategoria: [],
      listaMultimedia: this.fb.array([], Validators.required),
      listaPersonalizacion: this.fb.array([], Validators.required),
      listaVariante: this.fb.array([], Validators.required),
      moneda: [null],
      multimedia1: [null],
      multimedia2: [null],
      dscMultimedia1: [null],
      dscMultimedia2: [null],
      nombrecorto: [null, Validators.required],
      nroSegmentospersonalizable: [null],
      onlinestatus: [null],
      personalizable: [null],
      preciobasico: [null, Validators.required],
      precioenvio: [null, Validators.required],
      preciolista: [null, Validators.required],
      productonuevo: [null],
      sku: [{ value: '-', disabled: true }, Validators.required],
      idPrdpadre: [null],
      stock: [null],
      titulo: [null, Validators.required],
      unidadmedida: [null],
      descuentoProdNuevo: [null],
      porcentajeProdNuevo: [null, Validators.required],
      fechaInicioProdNuevo: [null, Validators.required],
      fechaFinProdNuevo: [null, Validators.required],
      idSeller: [this.autenticacionService.getCurrentUser()?.idSeller],
      idUsuarioLogeado: [
        this.autenticacionService.getCurrentUser()?.idSellerPersona,
      ],
    };
  }
  get formGroup(): FormGroup {
    return this.formGroupSubject ? this.formGroupSubject.value : null;
  }
  setFormGroup(dato: ProductoMantDto) {
    this.formGroupSubject = new BehaviorSubject(
      this.fb.group(this.getFormGroup())
    );
    console.log(this.formGroupSubject);

    if (dato != null) {
      dato.fechaFinProdNuevo = new Date(dato.fechaFinProdNuevo + 'T00:00:00');
      dato.fechaInicioProdNuevo = new Date(
        dato.fechaInicioProdNuevo + 'T00:00:00'
      );
      this.formGroup.patchValue(dato);
      const mini1: ListaMultimediaMantDto =
        dato.multimedia1 != null
          ? {
              grdMultimedia: dato.multimedia1,
              descripcion: dato.dscMultimedia1,
              miniatura: true,
            }
          : null;
      if (mini1 != null) {
        this.agregarListaMultimediaForm(mini1);
      }
      const mini2: ListaMultimediaMantDto =
        dato.multimedia2 != null
          ? {
              grdMultimedia: dato.multimedia2,
              descripcion: dato.dscMultimedia2,
              miniatura: true,
            }
          : null;
      if (mini2 != null) {
        this.agregarListaMultimediaForm(mini2);
      }
      dato.listaMultimedia.forEach((cont) => {
        this.agregarListaMultimediaForm(cont);
      });

      dato.listaCategorias.forEach((cont) => {
        this.agregarListCategoriaForm(cont);
      });

      dato.listaImg.forEach((cont) => {
        this.agregarListImgForm(cont);
      });
      dato.listaImgPropio.forEach((cont) => {
        this.agregarListImgPropioForm(cont);
      });
      dato.listaPersonalizacion.forEach((cont) => {
        this.agregarListaPersonalizacionForm(cont);
      });
      dato.listaVariante.forEach((cont) => {
        this.agregarListaVarianteForm(cont);
      });
    } else {
      this.agregarListaVarianteForm(null);
    }
  }

  obtenerFormGroupGuardar() {
    const listaMiniatura = this.getFormArray('listaMultimedia').controls.filter(
      (con) => con.get('miniatura').value == true
    );
    const mini1 = listaMiniatura.length > 0 ? listaMiniatura[0] : null;
    const mini2 = listaMiniatura.length > 1 ? listaMiniatura[1] : null;
    if (mini1) {
      this.getControl('multimedia1').setValue(mini1.get('grdMultimedia').value);
      this.getControl('dscMultimedia1').setValue(
        mini1.get('descripcion').value
      );
    }
    if (mini2) {
      this.getControl('multimedia2').setValue(mini2.get('grdMultimedia').value);
      this.getControl('dscMultimedia2').setValue(
        mini2.get('descripcion').value
      );
    }
    const formControl = this.fb.group(this.getFormGroup());
    formControl.patchValue(this.formGroup.value);
    this.getFormArray('listaMultimedia')
      .controls.filter(
        (con) =>
          !con.get('miniatura').value || con.get('miniatura').value == false
      )
      .forEach((cont) => {
        (formControl.get('listaMultimedia') as FormArray).push(cont);
      });
    this.getFormArray('listaCategorias').controls.forEach((cont) => {
      (formControl.get('listaCategorias') as FormArray).push(cont);
    });
    this.getFormArray('listaImg').controls.forEach((cont) => {
      (formControl.get('listaImg') as FormArray).push(cont);
    });
    this.getFormArray('listaImgPropio').controls.forEach((cont) => {
      (formControl.get('listaImgPropio') as FormArray).push(cont);
    });
    this.getFormArray('listaPersonalizacion').controls.forEach((cont) => {
      (formControl.get('listaPersonalizacion') as FormArray).push(cont);
    });
    this.getFormArray('listaVariante').controls.forEach((cont) => {
      (formControl.get('listaVariante') as FormArray).push(cont);
    });
    console.log(formControl);

    return formControl;
  }
  getControl(campo: string) {
    return this.formGroup.get(campo);
  }
  getControlChild(formGroup: string, campo: string) {
    return this.getControl(formGroup).get(campo);
  }
  getFormArray(campo: string) {
    return this.formGroup.get(campo) as FormArray;
  }
  agregarListCategoriaForm(dato: ProductoCategoriaDto) {
    if (!this.isValidForm('listaCategorias')) {
      return;
    }
    const config: FormGroupConfig<ProductoCategoriaDto> = {
      idCategoriaGrupomenu: [null, [Validators.required]],
    };
    let formGroup = this.fb.group(config);
    if (dato) {
      const { idCategoriaGrupomenu } = dato;
      formGroup.patchValue({ idCategoriaGrupomenu });
    }
    this.getFormArray('listaCategorias').push(formGroup);
  }

  agregarListImgForm(dato: ProductoImagenDto) {
    if (!this.isValidForm('listaImg')) {
      return;
    }
    const config: FormGroupConfig<ProductoImagenDto> = {
      id: [null],
      idDigImgSubImg: [null, [Validators.required]],
    };
    let formGroup = this.fb.group(config);
    if (dato) {
      const { idDigImgSubImg } = dato;
      formGroup.patchValue({ idDigImgSubImg });
    }
    this.getFormArray('listaImg').push(formGroup);
  }
  agregarListImgPropioForm(dato: ProductoImagenPropioDto) {
    if (!this.isValidForm('listaImgPropio')) {
      return;
    }
    const config: FormGroupConfig<ProductoImagenPropioDto> = {
      id: [null],
      idGrupo: [null, [Validators.required]],
      idCategoria: [null, [Validators.required]],
      idDigImgSubImg: [null, [Validators.required]],
      estado: [true],
      url: [null, [Validators.required]],
    };
    let formGroup = this.fb.group(config);
    if (dato) {
      const { id, idGrupo, idCategoria, idDigImgSubImg, estado, url } = dato;
      formGroup.patchValue({
        id,
        idGrupo,
        idCategoria,
        idDigImgSubImg,
        estado,
        url,
      });
    }
    this.getFormArray('listaImgPropio').push(formGroup);
  }
  agregarListaMultimediaForm(dato: ListaMultimediaMantDto) {
    if (!this.isValidForm('listaMultimedia')) {
      return;
    }
    const config: FormGroupConfig<ListaMultimediaMantDto> = {
      id: [null],
      descripcion: [null, [Validators.required]],
      grdMultimedia: [null, [Validators.required]],
      miniatura: [null],
    };
    let formGroup = this.fb.group(config);
    if (dato) {
      const { id, descripcion, grdMultimedia, miniatura } = dato;
      formGroup.patchValue({ id, descripcion, grdMultimedia, miniatura });
    }
    this.getFormArray('listaMultimedia').push(formGroup);
  }
  agregarListaPersonalizacionForm(dato: ListaPersonalizacionMantDto) {
    if (!this.isValidForm('listaPersonalizacion')) {
      return;
    }
    const config: FormGroupConfig<ListaPersonalizacionMantDto> = {
      id: [null],
      idDigtipopersonalizacion: [null, [Validators.required]],
      descripcion: [{ value: null, disabled: true }],
    };
    let formGroup = this.fb.group(config);
    if (dato) {
      const { id, idDigtipopersonalizacion, descripcion } = dato;
      formGroup.patchValue({ id, idDigtipopersonalizacion, descripcion });
    }
    this.getFormArray('listaPersonalizacion').push(formGroup);
  }
  agregarListaVarianteForm(dato: ListaVarianteMantDto) {
    if (!this.isValidForm('listaVariante')) {
      return;
    }
    const config: FormGroupConfig<ListaVarianteMantDto> = {
      id: [null],
      idColor: [null, [Validators.required]],
      codColor: [null],
      idTalla: [null, [Validators.required]],
      codTalla: [null],
      preciolista: [null, [Validators.required]],
      stock: [null, [Validators.required]],
    };
    let formGroup = this.fb.group(config);
    if (dato) {
      const { id, idColor, idTalla, preciolista, stock, codColor, codTalla } =
        dato;
      formGroup.patchValue({
        id,
        idColor,
        idTalla,
        preciolista,
        stock,
        codColor,
        codTalla,
      });
    }
    this.getFormArray('listaVariante').push(formGroup);
  }
  isValidForm(campo: string) {
    const forma = this.getFormArray(campo);
    //console.log('forma array ' + campo, forma);
    if (forma.length !== 0 && forma.invalid) {
      forma.markAllAsTouched();
      return false;
    }
    return true;
  }
  removeAtDimensionForm(index: number) {
    this.removeAtForm('listaDimension', index);
  }
  removeAtImgForm(index: number) {
    this.removeAtForm('listaImg', index);
  }
  removeAtImgPropioForm(index: number) {
    this.removeAtForm('listaImgPropio', index);
  }
  removeAtListaMultimediaForm(index: number) {
    this.removeAtForm('listaMultimedia', index);
  }
  removeAtListaPersonalizacionForm(index: number) {
    this.removeAtForm('listaPersonalizacion', index);
  }
  removeAtListaVarianteForm(index: number) {
    this.removeAtForm('listaVariante', index);
  }
  removeAtForm(campo, index: number) {
    this.getFormArray(campo).removeAt(index);
  }
}
