/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.controller.administracion;

import java.util.List;
import marketplace.service.administracion.GeneralService;
import marketplace.service.administracion.dto.general.GeneralDto;
import marketplace.service.administracion.dto.general.GeneralProductoDto;
import marketplace.service.administracion.dto.general.ImagenDto;
import marketplace.service.administracion.dto.general.MenuGrupoCategoriaDto;
import marketplace.service.administracion.dto.general.OpcionDto;
import marketplace.service.administracion.dto.general.ParametroDto;
import marketplace.service.administracion.dto.general.TipoCambioDto;
import marketplace.support.MensajeSupport;
import marketplace.support.dto.Opcion;
import marketplace.support.dto.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 *
 * @author PCADMIN
 */
@RestController
@RequestMapping("api/general")
public class GeneralController {

    @Autowired
    private MensajeSupport mensajeSupport;

    @Autowired
    private GeneralService generalService;

    @GetMapping("/configuracion")
    public Respuesta<GeneralDto> listarParametros() throws Exception {
        List<ParametroDto> listaParametro = generalService.listarParametro();
        List<OpcionDto> listaOpcion = generalService.listarOpciones();
        GeneralDto dto = new GeneralDto();
        dto.setListaOpciones(listaOpcion);
        dto.setListaParametro(listaParametro);
        return mensajeSupport.respuestaListar(dto);
    }

    @GetMapping("/valor-cambio")
    public Mono<Respuesta<List<TipoCambioDto>>> obtenerValorCambio() throws Exception {
        List<TipoCambioDto> parametroDtos = generalService.listarTipoCambio();
        return Mono.just(mensajeSupport.respuestaListar(parametroDtos));
    }

    @GetMapping("/paises")
    public Respuesta<List<Opcion>> listarDepartamentos() throws Exception {
        List<Opcion> dato = generalService.listarPais();
        return mensajeSupport.respuestaListar(dato);
    }

    @GetMapping("/departamentos/{idPais}")
    public Respuesta<List<Opcion>> listarDepartamentos(@PathVariable int idPais) throws Exception {
        List<Opcion> dato = generalService.listarDepartamento(idPais);
        return mensajeSupport.respuestaListar(dato);
    }

    @GetMapping("/provincias/{idDepartamento}")
    public Respuesta<List<Opcion>> listarProvincias(@PathVariable int idDepartamento) throws Exception {
        List<Opcion> dato = generalService.listarProvincia(idDepartamento);
        return mensajeSupport.respuestaListar(dato);
    }

    @GetMapping("/distritos/{idProvincia}")
    public Respuesta<List<Opcion>> listarDistritos(@PathVariable int idProvincia) throws Exception {
        List<Opcion> dato = generalService.listarDistrito(idProvincia);
        return mensajeSupport.respuestaListar(dato);
    }

    @GetMapping("/correo-unico/{correo}/{idPersona}/{tipoUsuario}")
    public Respuesta<Boolean> buscarRepeticionCorreo(@PathVariable String correo, @PathVariable int idPersona, @PathVariable String tipoUsuario) throws Exception {
        boolean dato = generalService.buscarRepeticionCorreo(correo, idPersona, tipoUsuario);
        return mensajeSupport.respuestaObtener(dato);
    }

    @GetMapping("/menu-grupo-categoria-opcion/{codigo}")
    public Respuesta<List<MenuGrupoCategoriaDto>> listarCategoriaProducto(@PathVariable String codigo) throws Exception {
        List<MenuGrupoCategoriaDto> dato = generalService.listarMenuGrupoCategoria(codigo);
        return mensajeSupport.respuestaObtener(dato);
    }

    @GetMapping("/categoria-disenio/{idGrupo}")
    public Respuesta<List<MenuGrupoCategoriaDto>> listarCategoriaDisenio(@PathVariable int idGrupo) throws Exception {
        List<MenuGrupoCategoriaDto> dato = generalService.listarCategoriaDisenio(idGrupo);
        return mensajeSupport.respuestaObtener(dato);
    }

    @GetMapping("/general-producto")
    public Respuesta<GeneralProductoDto> listarGeneralProducto() throws Exception {
        List<Opcion> listaMenuOpcion = generalService.listarMenuOpcion();
        List<Opcion> listaMarca = generalService.listarMarca();
        List<Opcion> listaColor = generalService.listarColores();
        List<Opcion> listaTalla = generalService.listarTallas();
        List<Opcion> listaTipoPersonalizacion = generalService.listarTipoPersonalizacion();
        List<Opcion> listaMenuDisenio = generalService.listarMenuDisenio();
        List<Opcion> listaProductoPadre = generalService.listarProductoPadre();
        List<ImagenDto> listaImagen = generalService.listarImagen();
        GeneralProductoDto dto = new GeneralProductoDto();
        dto.setListaColor(listaColor);
        dto.setListaMarca(listaMarca);
        dto.setListaMenuDisenio(listaMenuDisenio);
        dto.setListaMenuOpcion(listaMenuOpcion);
        dto.setListaProductoPadre(listaProductoPadre);
        dto.setListaTalla(listaTalla);
        dto.setListaTipoPersonalizacion(listaTipoPersonalizacion);
        dto.setListaImagen(listaImagen);
        return mensajeSupport.respuestaObtener(dto);
    }

    @GetMapping("/postulante")
    public Respuesta<List<Opcion>> listarPostulante() throws Exception {
        List<Opcion> dato = generalService.listarPostulante();
        return mensajeSupport.respuestaListar(dato);
    }

  
}
