import { Parametro } from '@core/soporte/enums';
import { GeneralService } from '@core/services/general.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UtilArchivoService {
  extensionVideo: string[] = null;
  extensionImage: string[] = null;
  constructor(private generalService: GeneralService) {
    this.extensionVideo = this.generalService
      .listarParamatros(Parametro.FORMATO_CARGA_VIDEO)[0]
      .valor.split(',');
    this.extensionImage = this.generalService
      .listarParamatros(Parametro.FORMATO_CARGA_IMAGEN)[0]
      .valor.split(',');
  }

  isVideo(path: string) {
    let isVideo = false;
    this.extensionVideo.forEach((extension) => {
      if (path.includes(extension)) {
        isVideo = true;
      }
    });
    return isVideo;
  }

  isYouTube(path: string) {
    let isYoutube = false;
    if (path.includes('www.youtube.com')) {
      isYoutube = true;
    }
    return isYoutube;
  }

  isImage(path: string) {
    let isImage = false;
    this.extensionImage.forEach((extension) => {
      if (path.includes(extension)) {
        isImage = true;
      }
    });
    return isImage;
  }
  getNombre(path: string) {
    let extension = '';
    if (path.includes('bmoi.site')) {
      extension = path.replace(/^.*[\\\-]/, '');
    } else {
      extension = path.replace(/^.*[\\\/\\]/, '');
    }
    return extension;
  }
  getExtension(path: string) {
    var extension = path.replace(/^.*[\\\.]/, '');
    //console.log(extension);
    return 'video/' + extension;
  }
}
