/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.general;

import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author Martin Pilar
 */
@Data
public class TipoCambioDto {

    private Integer id;
    private String moneda;
    private BigDecimal valCompra;
    private BigDecimal valVenta;
    private String simbolo;
//    private Date fecha;
}
