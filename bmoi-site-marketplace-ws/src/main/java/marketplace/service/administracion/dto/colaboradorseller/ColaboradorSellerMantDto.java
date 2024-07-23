/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.colaboradorseller;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Data
public class ColaboradorSellerMantDto {

    private Integer id;
    private int idSeller;
    private Integer idPersonaLogeada;
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
    private String foto;
    private String cargo;
    private int cntPrincipal;
}
