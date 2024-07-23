/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.tipocambio;

import java.util.List;
import org.mapstruct.Mapper;
import marketplace.repository.entity.ValorCambio;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Mapper(componentModel = "spring")
public interface TipoCambioMapper {

    List<TipoCambioManDto> toTipoCambioManDtos(List<ValorCambio> listaValorCambio);

    TipoCambioManDto toTipoCambioManDto(ValorCambio valorCambio);

    ValorCambio toValorCambio(TipoCambioManDto manteminientoDto);
}
