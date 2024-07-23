/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.controller.administracion;

import java.util.List;
import marketplace.service.administracion.UsuarioBmoiService;
import marketplace.service.administracion.dto.usuarioseller.OpcionPersona;
import marketplace.service.administracion.dto.usuarioseller.UsuarioInDto;
import marketplace.service.administracion.dto.usuarioseller.UsuarioMantDto;
import marketplace.service.administracion.dto.usuarioseller.UsuarioOutDto;
import marketplace.support.MensajeSupport;
import marketplace.support.dto.Opcion;
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
 * Controller para el modulo cuentas
 *
 * @author mpilar
 */
@RestController
@RequestMapping("api/usuario/bmoi")
public class UsuarioBmoiController {

    @Autowired
    private MensajeSupport mensajeSupport;

    @Autowired
    private UsuarioBmoiService usuarioService;

    @GetMapping("{ipPersonaLogeada}/{idPersona}")
    public Respuesta<UsuarioMantDto> obtener(@PathVariable int ipPersonaLogeada, @PathVariable int idPersona) throws Exception {
        UsuarioMantDto mantDto = usuarioService.obtener(ipPersonaLogeada, idPersona);
        return mensajeSupport.respuestaObtener(mantDto);
    }

    @PostMapping("listar")
    public Respuesta<Page<UsuarioOutDto>> listar(@RequestBody UsuarioInDto usuarioIn) throws Exception {
        Page<UsuarioOutDto> dato = usuarioService.listar(usuarioIn);
        return mensajeSupport.respuestaListar(dato);
    }

    @PostMapping()
    public Respuesta guardar(@RequestBody UsuarioMantDto mantenimientoDto) throws Exception {
        usuarioService.guardar(mantenimientoDto);
        return mensajeSupport.respuestaAgregar(null);
    }

    @PutMapping()
    public Respuesta actualizar(@RequestBody UsuarioMantDto mantenimientoDto) throws Exception {
        usuarioService.actualizar(mantenimientoDto);
        return mensajeSupport.respuestaActualizar(null);
    }

    @PutMapping("{idPersonaLogeada}")
    public Respuesta eliminar(@PathVariable int ipPersonaLogeada, @RequestBody List<Integer> listaIdPersona) throws Exception {
        usuarioService.eliminar(ipPersonaLogeada, listaIdPersona);
        return mensajeSupport.respuestaEliminar(null);
    }

    @GetMapping("opcion/{idPersonaLogeada}/{idCliente}")
    public Respuesta<List<Opcion>> listarOpcionCliente(@PathVariable int idPersonaLogeada, @PathVariable int idCliente) throws Exception {
        List<Opcion> listaOpcionesOut = usuarioService.listarOpcionSeller(idPersonaLogeada, idCliente);
        return mensajeSupport.respuestaListar(listaOpcionesOut);
    }

    @GetMapping("personas/{idPersonaLogeada}/{idCliente}/{idPersona}")
    public Respuesta<List<OpcionPersona>> listarOpcionPersonas(@PathVariable int idPersonaLogeada, @PathVariable int idCliente, @PathVariable Integer idPersona) throws Exception {
        List<OpcionPersona> listaOpcionesOut = usuarioService.listarOpcionPersonas(idPersonaLogeada, idCliente, idPersona);
        return mensajeSupport.respuestaListar(listaOpcionesOut);
    }

    @GetMapping("personas/roles/{idPersonaLogeada}")
    public Respuesta<List<Opcion>> listarOpcionRoles(@PathVariable int idPersonaLogeada) throws Exception {
        List<Opcion> listaOpcionesOut = usuarioService.listarOpcionRoles(idPersonaLogeada);
        return mensajeSupport.respuestaListar(listaOpcionesOut);
    }
}
