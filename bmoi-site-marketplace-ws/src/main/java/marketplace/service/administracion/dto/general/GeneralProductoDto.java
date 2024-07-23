/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.general;

import java.util.List;
import lombok.Data;
import marketplace.support.dto.Opcion;

/**
 *
 * @author Martin Pilar
 */
@Data
public class GeneralProductoDto {

    private List<Opcion> listaMenuOpcion;
    private List<Opcion> listaMarca;
    private List<Opcion> listaColor;
    private List<Opcion> listaTalla;
    private List<Opcion> listaTipoPersonalizacion;
    private List<Opcion> listaMenuDisenio;
    private List<Opcion> listaProductoPadre;
    private List<ImagenDto> listaImagen;
}
