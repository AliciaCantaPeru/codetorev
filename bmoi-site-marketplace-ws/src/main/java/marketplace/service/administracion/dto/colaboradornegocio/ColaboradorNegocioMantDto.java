/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.colaboradornegocio;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Data
public class ColaboradorNegocioMantDto {

    private Integer id;
    private Integer idPersonaLogeada;
    private String nombres;
    private String priApellido;
    private String segApellido;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecNacimiento;
    private String genero;
    private String tipDocumento;
    private String numDocumento;
    private String email;
    private String telFijo;
    private String celular;
    private String cargo;
    private int cntPrincipal;
}
