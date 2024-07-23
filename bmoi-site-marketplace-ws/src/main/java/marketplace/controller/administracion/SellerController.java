/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.controller.administracion;

import java.nio.charset.StandardCharsets;
import java.util.List;
import marketplace.service.administracion.SellerService;
import marketplace.service.administracion.dto.seller.SellerInDto;
import marketplace.service.administracion.dto.seller.SellerMantDto;
import marketplace.service.administracion.dto.seller.SellerOutDto;
import marketplace.support.MensajeSupport;
import marketplace.support.dto.Respuesta;
import marketplace.util.JsonConversor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Martin Pilar
 */
@RestController
@RequestMapping("api/seller")
public class SellerController {

    @Autowired
    private MensajeSupport mensajeSupport;

    @Autowired
    private SellerService sellerService;

    @GetMapping("{ipPersonaLogeada}/{idCliente}")
    public Respuesta<SellerMantDto> obtener(@PathVariable int ipPersonaLogeada, @PathVariable int idCliente) throws Exception {
        SellerMantDto clienteOutDto = sellerService.obtener(ipPersonaLogeada, idCliente);
        return mensajeSupport.respuestaObtener(clienteOutDto);
    }

    @PostMapping("listar")
    public Respuesta<Page<SellerOutDto>> listar(@RequestBody SellerInDto clienteIn) throws Exception {
        Page<SellerOutDto> dato = sellerService.listar(clienteIn);
        return mensajeSupport.respuestaListar(dato);
    }

    @PostMapping()
    public Respuesta<SellerMantDto> guardar(@RequestParam(required = false) MultipartFile image, @RequestPart() String jsonDato) throws Exception {
        String json = new String(jsonDato.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        SellerMantDto mantenimientoDto = JsonConversor.fromStringToObject(json, SellerMantDto.class);
        SellerMantDto mantOut = sellerService.guardar(image, mantenimientoDto);
        return mensajeSupport.respuestaAgregar(mantOut);
    }

    @PutMapping()
    public Respuesta<SellerMantDto> actualizar(@RequestParam(required = false) MultipartFile image, @RequestPart() String jsonDato) throws Exception {
        String json = new String(jsonDato.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        SellerMantDto mantenimientoDto = JsonConversor.fromStringToObject(json, SellerMantDto.class);
        SellerMantDto mantOut = sellerService.actualizar(image, mantenimientoDto);
        return mensajeSupport.respuestaActualizar(mantOut);
    }

    @PutMapping("{ipPersonaLogeada}")
    public Respuesta actualizarEstado(@PathVariable int ipPersonaLogeada, @RequestBody List<Integer> listaSeller) throws Exception {
        sellerService.eliminar(ipPersonaLogeada, listaSeller);
        return mensajeSupport.respuestaEliminar(null);
    }

    @PutMapping("resetear-cuenta/{ipPersonaLogeada}/{idSeller}")
    public Respuesta<Page<SellerOutDto>> resetearCuenta(@PathVariable int ipPersonaLogeada, @PathVariable int idSeller) throws Exception {
        sellerService.activarcuenta(ipPersonaLogeada, idSeller);
        return mensajeSupport.respuestaListar(null);
    }
}
