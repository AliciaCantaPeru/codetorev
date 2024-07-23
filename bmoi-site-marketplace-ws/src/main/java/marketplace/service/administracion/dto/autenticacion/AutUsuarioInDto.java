/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion.dto.autenticacion;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import marketplace.support.json.JsonId;

/**
 *
 * @author PCADMIN
 */
@Data
public class AutUsuarioInDto {

    @JsonDeserialize(using = JsonId.JsonIdIntegerDeserializer.class)
    private Integer idPersona;
}
