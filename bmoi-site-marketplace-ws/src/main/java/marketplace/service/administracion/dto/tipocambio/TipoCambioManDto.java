/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.tipocambio;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import lombok.Data;
import marketplace.support.json.JsonDateDDMMYYYSerializer;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Data
public class TipoCambioManDto {

    private Integer id;
    private Integer idPersonaLogeada;
    private String moneda;
    private double valCompra;
    private double valVenta;
    @JsonSerialize(using = JsonDateDDMMYYYSerializer.class)
    private Date fecha;
}
