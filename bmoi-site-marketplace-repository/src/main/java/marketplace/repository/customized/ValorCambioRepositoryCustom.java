/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.customized;

import java.math.BigDecimal;
import java.time.LocalDate;
import marketplace.repository.entity.ValorCambio;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
public interface ValorCambioRepositoryCustom {

    PageImpl<ValorCambio> listaValorCambio(String sortCampo, String sortOrden, String seach, PageRequest pageable) throws Exception;

    BigDecimal obtenerValorCambio(LocalDate fecha, String moneda) throws Exception;

}
