/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.producto;

import java.math.BigDecimal;
import java.math.BigInteger;
import lombok.Data;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Data
public class ProductoOutDto {

    private Integer id;
    private String multimedia1;
    private String sku;
    private int stock;
    private String nombrecorto;
    private BigDecimal preciobasico;
    private int onlinestatus;
    private Integer destacado;
    private String skupadre;
    private String titulo;
    private String moneda;
    private BigDecimal preciolista;
    private BigDecimal precioenvio;
    private Integer enviogratis;
    private String multimedia2;
    private String dscMultimedia2;
    private String dscMultimedia1;
    private Integer f2;
    private String productonuevo;
    private String unidadmedida;
    private BigInteger avgstar;
    private String enoferta;
    private int iniciodigital;
    private int personalizable;
    private int nroSegmentospersonalizable;
    private String impuesto;
    private BigInteger impuestoporcentaje;
    private Integer idSellerchat;
    private String f7;
    private String f8;
    private String f9;
    private String descripcion;
    private int estado;
}
