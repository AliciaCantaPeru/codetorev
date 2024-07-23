/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.producto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import lombok.Data;
import marketplace.service.administracion.dto.general.MenuGrupoCategoriaDto;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Data
public class ProductoMantDto {

    private Integer idUsuarioLogeado;
    private Integer idSeller;
    private Integer id;
    private String sku;
    private String codigoMenu;
    private Integer idPrdpadre;
    private String nombrecorto;
    private String titulo;
    private int stock;
    private String moneda;
    private BigDecimal preciobasico;
    private BigDecimal preciolista;
    private BigDecimal precioenvio;
    private boolean enviogratis;
    private String multimedia2;
    private String dscMultimedia2;
    private String multimedia1;
    private String dscMultimedia1;
    private Integer f2;
    private String productonuevo;
    private boolean onlinestatus;
    private String unidadmedida;
    private BigInteger avgstar;
    private String enoferta;
    private int iniciodigital;
    private boolean personalizable;
    private int nroSegmentospersonalizable;
    private Integer destacado;
    private String impuesto;
    private BigInteger impuestoporcentaje;
    private Integer idSellerchat;
    private String f7;
    private String f8;
    private String f9;
    private String descripcion;
    private int estado;
    private int idBrand;

    private boolean descuentoProdNuevo;
    private int porcentajeProdNuevo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicioProdNuevo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaFinProdNuevo;

    private List<ProductoAssetsMultimediaDto> listaMultimedia;
    private List<ProductoVariantesDto> listaVariante;
    private ProductoDimensionesDto dimension;
    private List<DigProductoTipopersonalizacionDto> listaPersonalizacion;
    private List<ProductoImagenDto> listaImg;
    private List<ProductoImagenPropioDto> listaImgPropio;
    private List<MenuGrupoCategoriaDto> listaGrupoCategoria;
    private List<ProductoCategoriaDto> listaCategorias;

    @Data
    public static class ProductoAssetsMultimediaDto {

        private Integer id;
        private String grdMultimedia;
        private String descripcion;
    }

    @Data
    public static class ProductoVariantesDto {

        private Integer id;
        private int stock;
        private BigDecimal preciolista;
        private int idColor;
        private String codColor;
        private int idTalla;
        private String codTalla;

    }

    @Data
    public static class ProductoDimensionesDto {

        private Integer id;
        private double peso;
        private BigInteger altura;
        private BigInteger anchura;
        private BigInteger profundida;
    }

    @Data
    public static class DigProductoTipopersonalizacionDto {

        private Integer id;
        private int idDigtipopersonalizacion;
    }

    @Data
    public static class ProductoImagenDto {

        private Integer id;
        private int idDigImgSubImg;

    }

    @Data
    @JsonIgnoreProperties(value = {"idGrupo", "idCategoria"}, allowGetters = true)
    public static class ProductoImagenPropioDto {

        private Integer id;

        private int idDigImgSubImg;
        private String url;
        private boolean estado;
        private Integer idGrupo;
        private Integer idCategoria;

    }

    @Data
    public static class ProductoCategoriaDto {

        private Integer id;
        private int idCategoriaGrupomenu;
    }
}
