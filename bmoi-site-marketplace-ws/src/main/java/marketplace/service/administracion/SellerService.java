/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion;

import marketplace.service.administracion.dto.seller.SellerInDto;
import marketplace.service.administracion.dto.seller.SellerMantDto;
import marketplace.service.administracion.dto.seller.SellerOutDto;

/**
 *
 * @author Martin Pilar
 */
public interface SellerService extends GenericSecondaryService<SellerInDto, SellerOutDto, SellerMantDto> {

    void activarcuenta(int ipPersonaLogeada, int idSeller) throws Exception;
}
