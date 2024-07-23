/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity.dynamo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataCalculationEntity {

    private String pulsos;

    @JsonProperty("pulsos_medida0")
    private String pulsosMedida0;
    @JsonProperty("pulsos_valor0")
    private BigDecimal pulsosValor0;

    @JsonProperty("pulsos_medida1")
    private String pulsosMedida1;
    @JsonProperty("pulsos_valor1")
    private BigDecimal pulsosValor1;

    @JsonProperty("pulsos_medida2")
    private String pulsosMedida2;
    @JsonProperty("pulsos_valor2")
    private BigDecimal pulsosValor2;

    @JsonProperty("pulsos_medida3")
    private String pulsosMedida3;
    @JsonProperty("pulsos_valor3")
    private BigDecimal pulsosValor3;

    @JsonProperty("pulsos_medida4")
    private String pulsosMedida4;
    @JsonProperty("pulsos_valor4")
    private BigDecimal pulsosValor4;

    @JsonProperty("bateria_estado")
    private String bateriaEstado;

    @JsonProperty("rsv_estado")
    private String rsvEstado;

    @JsonProperty("seq_number")
    private String seqNumber;

    @JsonProperty("evento")
    private String evento;

    @JsonProperty("evento_estado")
    private String eventoEstado;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("estado_estado")
    private String estadoEstado;
    
    @JsonProperty("lat")
    private String lat;

    @JsonProperty("lon")
    private String lon;

    private String rsv;
    private String bateria;
    private int anio;
    private int mes;
    private int dia;
}
