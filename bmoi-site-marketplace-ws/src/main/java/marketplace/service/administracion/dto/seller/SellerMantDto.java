/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.seller;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author mpilar
 */
@Data
public class SellerMantDto {

    private Integer idPersonaLogeada;
    private SellerEmpresaDto empresa;
    private List<PersonasDto> listaPersonas;
    private List<RedesSocialesDto> listaRedesSociales;
    private DireccionDto direccion;
    private String foto;

    @Data
    public static class SellerEmpresaDto {

        private DireccionDto direccion;
        private Integer id;
        private Integer idpostulante;
        private String tipEmpresa;
        private String nomComercial;
        private String razSocial;
        private String rubro;
        private String ruc;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date feciniActividades;
        private String telefono;
        private String celular;
        private String img;
        private String website;
        private String email1;
        private String email2;
        private String descripcion;
    }

    @Data
    public static class PersonasDto {

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
        private int cntPrincipal;

    }

    @Data
    public static class RedesSocialesDto {

        private Integer id;
        private String nombre;
        private String url;
    }

    @Data
    public static class DireccionDto {

        private Integer id;
        private String direccion;
        private String calle;
        private String referencia;
        private Integer idDepartamento;
        private Integer idDistrito;
        private Integer idPais;
        private Integer idProvincia;

    }

}
