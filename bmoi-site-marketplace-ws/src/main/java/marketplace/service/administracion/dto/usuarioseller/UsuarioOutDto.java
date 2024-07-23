/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.usuarioseller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import lombok.Data;
import marketplace.support.json.JsonDateDDMMYYYHHMMSSSerializer;
import marketplace.support.json.JsonDateDDMMYYYSerializer;

/**
 *
 * @author mpilar
 */
@Data
public class UsuarioOutDto {

    private String cliente;
    private String rol;
    private Integer id;
    private String priNombre;
    private String segNombre;
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
    private String sexo;
    private String celular1;
    private String celular2;
}
