/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.controller.administracion;

import java.util.List;
import marketplace.service.administracion.ProductoService;
import marketplace.service.administracion.dto.producto.ProductoInDto;
import marketplace.service.administracion.dto.producto.ProductoMantDto;
import marketplace.service.administracion.dto.producto.ProductoOutDto;
import marketplace.support.MensajeSupport;
import marketplace.support.dto.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mpilar
 */
@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private MensajeSupport mensajeSupport;

    @Autowired
    private ProductoService productoService;

    @GetMapping("{ipPersonaLogeada}/{idProducto}")
    public Respuesta<ProductoMantDto> obtener(@PathVariable int ipPersonaLogeada, @PathVariable int idProducto) throws Exception {
        ProductoMantDto mantDto = productoService.obtener(ipPersonaLogeada, idProducto);
        return mensajeSupport.respuestaObtener(mantDto);
    }

    @PostMapping("listar")
    public Respuesta<Page<ProductoOutDto>> listar(@RequestBody ProductoInDto inDto) throws Exception {
        Page<ProductoOutDto> dato = productoService.listar(inDto);
        return mensajeSupport.respuestaListar(dato);
    }

    @PostMapping()
    public Respuesta<ProductoMantDto> guardar(@RequestBody ProductoMantDto productoIn) throws Exception {
        ProductoMantDto dato = productoService.guardar(productoIn);
        return mensajeSupport.respuestaAgregar(dato);
    }

    @PutMapping()
    public Respuesta<ProductoMantDto> actualizar(@RequestBody ProductoMantDto productoIn) throws Exception {
        ProductoMantDto dato = productoService.actualizar(productoIn);
        return mensajeSupport.respuestaActualizar(dato);
    }

    @PutMapping("{ipPersonaLogeada}")
    public Respuesta eliminar(@PathVariable int ipPersonaLogeada, @RequestBody List<Integer> listaIdProducto) throws Exception {
        productoService.eliminar(ipPersonaLogeada, listaIdProducto);
        return mensajeSupport.respuestaEliminar(null);
    }

    @PutMapping("ver/{ipPersonaLogeada}/{idProducto}")
    public Respuesta visibilidad(@PathVariable int ipPersonaLogeada, @PathVariable int idProducto) throws Exception {
        productoService.ver(ipPersonaLogeada, idProducto);
        return mensajeSupport.respuestaActualizar(null);
    }
     @PutMapping("destacar/{ipPersonaLogeada}/{idProducto}")
    public Respuesta destacar(@PathVariable int ipPersonaLogeada, @PathVariable int idProducto) throws Exception {
        productoService.destacar(ipPersonaLogeada, idProducto);
        return mensajeSupport.respuestaActualizar(null);
    }
}
