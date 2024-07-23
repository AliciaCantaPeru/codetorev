/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity.sellers;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author Martin Pilar
 */
@Data
public class SellerEntity {

    private Integer id;
    private String tipEmpresa;
    private String nomComercial;
    private String razSocial;
    private String rubro;
    private String ruc;
    private String telefono;
    private String celular;
    private String img;
    private String website;
    private String email1;
    private String email2;
    private String descripcion;
    private String usuRegistro;
    private Date fecRegistro;
    private String usuActualizacion;
    private Date fecActualizacion;
    private int estado;
    private Date feciniActividades;
    private String direccion;
    private Integer totPedidos;
    private BigDecimal totPedMonto;
    private Date fecFinal;
    private String contacto; 

}
