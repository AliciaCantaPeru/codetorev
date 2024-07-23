/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion;

import java.util.List;
import marketplace.service.administracion.dto.general.ImagenDto;
import marketplace.service.administracion.dto.general.MenuGrupoCategoriaDto;
import marketplace.service.administracion.dto.general.OpcionDto;
import marketplace.service.administracion.dto.general.ParametroDto;
import marketplace.service.administracion.dto.general.TipoCambioDto;
import marketplace.support.dto.Opcion;

/**
 *
 * @author PCADMIN
 */
public interface GeneralService {

    List<ParametroDto> listarParametro() throws Exception;

    List<OpcionDto> listarOpciones() throws Exception;

    List<TipoCambioDto> listarTipoCambio() throws Exception;

    List<Opcion> listarPais() throws Exception;

    List<Opcion> listarDepartamento(int idPais) throws Exception;

    List<Opcion> listarProvincia(int idDepartamento) throws Exception;

    List<Opcion> listarDistrito(int idProvincia) throws Exception;

    List<Opcion> listarPostulante() throws Exception;

    boolean buscarRepeticionCorreo(String correo, int idPersona, String tipoUsuario) throws Exception;

    List<MenuGrupoCategoriaDto> listarMenuGrupoCategoria(String codigoMenu) throws Exception;

    List<MenuGrupoCategoriaDto> listarCategoriaDisenio(int idGrupoDisneio) throws Exception;

    List<Opcion> listarMenuOpcion() throws Exception;

    List<Opcion> listarMarca() throws Exception;

    List<Opcion> listarColores() throws Exception;

    List<Opcion> listarTallas() throws Exception;

    List<Opcion> listarTipoPersonalizacion() throws Exception;

    List<Opcion> listarMenuDisenio() throws Exception;

    List<Opcion> listarProductoPadre() throws Exception;

    List<Opcion> listarParametros() throws Exception;

    List<ImagenDto> listarImagen() throws Exception;


}
