/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.controller.administracion;

import marketplace.service.administracion.DashboardService;
import marketplace.support.MensajeSupport;
import marketplace.support.dto.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Martin Pilar
 */
@RestController
@RequestMapping("api/dashboard")
public class DashboardController {

    @Autowired
    private MensajeSupport mensajeSupport;

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dia-restante/{idSeller}")
    public Respuesta<String> listarParametros(@PathVariable int idSeller) throws Exception {
        String listaOpcion = dashboardService.obtenerDiasRestante(idSeller);
        return mensajeSupport.respuestaListar(listaOpcion);
    }
}
