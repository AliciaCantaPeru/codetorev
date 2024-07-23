/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.seller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import marketplace.support.json.JsonDateDDMMYYYHHMMSSSerializer;
import marketplace.support.json.JsonDateDDMMYYYSerializer;

/**
 *
 * @author mpilar
 */
@Data
public class SellerOutDto {

    private int id;
    private String tipEmpresa;
    private String razSocial;
    private String rubro;
    private String nomComercial;
    private String ruc;
    private String telefono;
    private String descripcion;
    private String email1;
    private String email2;
    private String celular;
    private String website;
    @JsonSerialize(using = JsonDateDDMMYYYSerializer.class)
    private Date feciniActividades;
    private String usuRegistro;
    @JsonSerialize(using = JsonDateDDMMYYYHHMMSSSerializer.class)
    private Date fecRegistro;
    private String usuActualizacion;
    @JsonSerialize(using = JsonDateDDMMYYYHHMMSSSerializer.class)
    private Date fecActualizacion;
    private Integer totPedidos;
    private BigDecimal totPedMonto;
    @JsonSerialize(using = JsonDateDDMMYYYSerializer.class)
    private Date fecFinal;
//    private Date fecInicio;    
    private String contacto;
    private String monedaCambio="S/.";

}
