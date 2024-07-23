/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.empresa;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author mpilar
 */
@Data
public class EmpresaMantDto {

    private Integer idPersonaLogeada;
    private EmpresaDto empresa;
    private List<PersonasDto> listaPersonas;
    private List<RedesSocialesDto> listaRedesSociales;
    private DireccionDto direccion;
    private String foto;

    @Data
    public static class EmpresaDto {

        private Integer id;
        private String razSocial;
        private String rubro;
        private String nombre;
        private String ruc;
        private String telefono;
        private String email;
        private String tipEmpresa;
        private String otros;
        private String web;

    }

    @Data
    public static class PersonasDto {

        private Integer id;
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

    @Data
    public static class RedesSocialesDto {

        private Integer id;
        private String nombre;
        private String url;
    }

    @Data
    public static class DireccionDto {

        private Integer id;
        private Integer idPais;
        private Integer idDepartamento;
        private Integer idProvincia;
        private Integer idDistrito;
        private String direccion;
    }

}
