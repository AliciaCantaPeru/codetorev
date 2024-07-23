import { Component, OnInit } from '@angular/core';
import { ContenidWebDto } from '@core/models/content-web.model';
import { ContenidoWebService } from '@core/services/contenido-web.service';
import { ContentWeb } from '@core/soporte/enums';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.scss'],
})
export class IndexComponent implements OnInit {
  contenidoWeb: ContenidWebDto;
  constructor(private contenidoWebService: ContenidoWebService) {}

  ngOnInit(): void {
    this.contenidoWebService.listarContenidoWeb().subscribe((res) => {
      this.contenidoWeb = res.dato;
    });
  }

  getBody() {
    const html = this.contenidoWeb?.listaContent?.find((con) => {
      console.log(con.seccion);
      console.log(ContentWeb.body.toString());

      return con.seccion == ContentWeb.body.toString();
    })?.html;
    return html ? html : '';
  }
  getRedes() {
    const html = this.contenidoWeb?.listaContent?.find((con) => {
      return con.seccion == ContentWeb.prefooter.toString();
    })?.html;
    return html ? html : '';
  }
  getFooter() {
    const html = this.contenidoWeb?.listaContent?.find((con) => {
      return con.seccion == ContentWeb.footer.toString();
    })?.html;
    return html ? html : '';
  }
}
