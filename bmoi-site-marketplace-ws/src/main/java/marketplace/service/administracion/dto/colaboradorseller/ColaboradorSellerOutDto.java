/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.colaboradorseller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import lombok.Data;
import marketplace.support.json.JsonDateDDMMYYYHHMMSSSerializer;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Data
public class ColaboradorSellerOutDto {

    private Integer id;
    private String priNombre;
    private String segNombre;
    private String priApellido;
    private String segApellido;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecNacimiento;
    private String sexo;
    private String tipDocumento;
    private String numDocumento;
    private String email;
    private String celular1;
    private String celular2;
    private String cargo;
    private String usuRegistro;
    @JsonSerialize(using = JsonDateDDMMYYYHHMMSSSerializer.class)
    private Date fecRegistro;
    private String usuActualizacion;
    @JsonSerialize(using = JsonDateDDMMYYYHHMMSSSerializer.class)
    private Date fecActualizacion;
    private int cntPrincipal;
}
