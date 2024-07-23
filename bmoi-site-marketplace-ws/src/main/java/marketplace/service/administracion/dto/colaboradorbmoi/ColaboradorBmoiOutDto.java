/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.colaboradorbmoi;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import lombok.Data;
import marketplace.support.json.JsonDateDDMMYYYSerializer;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Data
public class ColaboradorBmoiOutDto {

    private Integer id;
    private String rol;
    private String nombres;
    private String priApellido;
    private String segApellido;
    @JsonSerialize(using = JsonDateDDMMYYYSerializer.class)
    private Date fecNacimiento;
    private String sexo;
    private String tipDocumento;
    private String numDocumento;
    private String email;
    private String celular;
    private String foto;
    private Integer codigopais;
}
