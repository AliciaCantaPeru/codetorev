/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.colaboradorcliente;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import lombok.Data;
import marketplace.support.json.JsonDateDDMMYYYHHMMSSSerializer;
import marketplace.support.json.JsonDateDDMMYYYSerializer;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Data
public class ColaboradorClienteOutDto {

    private Integer id;
    private String nombres;
    private String priApellido;
    private String segApellido;
    @JsonSerialize(using = JsonDateDDMMYYYSerializer.class)
    private Date fecNacimiento;
    private String genero;
    private String tipDocumento;
    private String numDocumento;
    private String email;
    private String celular;
    private String telFijo;
    private String cargo;
    private String usuRegistro;
    @JsonSerialize(using = JsonDateDDMMYYYHHMMSSSerializer.class)
    private Date fecRegistro;
    private String usuActualizacion;
    @JsonSerialize(using = JsonDateDDMMYYYHHMMSSSerializer.class)
    private Date fecActualizacion;
    private int cntPrincipal;
}
