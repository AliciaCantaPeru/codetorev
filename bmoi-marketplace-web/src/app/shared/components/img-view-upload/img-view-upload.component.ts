import {
  Component,
  OnInit,
  ChangeDetectionStrategy,
  ViewChild,
  ElementRef,
  Input,
  Output,
  EventEmitter,
} from '@angular/core';
import { LoadingService } from '@core/services/loading.service';

@Component({
  selector: 'app-img-view-upload',
  templateUrl: './img-view-upload.component.html',
  styleUrls: ['./img-view-upload.component.scss'],
})
export class ImgViewUploadComponent implements OnInit {
  @Input() image = null;
  @Input() editable = true;
  @Output() imageChange = new EventEmitter<File>();
  @ViewChild('inputFile') inputFile: ElementRef<HTMLInputElement>;
  imageTemporal: string | ArrayBuffer = null;

  constructor(private loadingService: LoadingService) {}

  ngOnInit(): void {
  }

  seleccionarImagen(event) {
    this.loadingService.loadingStatus(true);
    const fileList: FileList = event.target.files;
    if (fileList.length > 0) {
      const file: File = fileList[0];
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.imageTemporal = e.target.result;
        this.loadingService.loadingStatus(false);
      };
      reader.readAsDataURL(file);
      this.imageChange.emit(file);
    }
  }
  cancelar() {
    this.imageTemporal = null;
    this.imageChange.emit(null);
  }
}
