/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.empresa;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import lombok.Data;
import marketplace.support.json.JsonDateDDMMYYYHHMMSSSerializer;

/**
 *
 * @author mpilar
 */
@Data
public class EmpresaOutDto {

    private int id;
    private String tipEmpresa;
    private String razSocial;
    private String rubro;
    private String nombre;
    private String ruc;
    private String telefono;
    private String descripcion;
    private String email1;
    private String email2;
    private String celular;
    private String web;
    private String otros;
    @JsonSerialize(using = JsonDateDDMMYYYHHMMSSSerializer.class)
    private Date feciniActividades;
    private String usuRegistro;
    @JsonSerialize(using = JsonDateDDMMYYYHHMMSSSerializer.class)
    private Date fecRegistro;
    private String usuActualizacion;
    @JsonSerialize(using = JsonDateDDMMYYYHHMMSSSerializer.class)
    private Date fecActualizacion;

}
