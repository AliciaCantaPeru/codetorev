package marketplace.controller.administracion;

import java.nio.charset.StandardCharsets;
import java.util.List;
import marketplace.service.administracion.ColaboradorBmoiService;
import marketplace.service.administracion.dto.colaboradorseller.ColaboradorSellerInDto;
import marketplace.service.administracion.dto.colaboradorseller.ColaboradorSellerMantDto;
import marketplace.service.administracion.dto.colaboradorseller.ColaboradorSellerOutDto;
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
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
@RestController
@RequestMapping("/api/colaborador/bmoi")
public class ColaboradorBmoiController {

    @Autowired
    private MensajeSupport mensajeSupport;

    @Autowired
    private ColaboradorBmoiService colaboradorService;

    @GetMapping("{ipPersonaLogeada}/{idColaborador}")
    public Respuesta<ColaboradorSellerMantDto> obtener(@PathVariable int ipPersonaLogeada, @PathVariable int idColaborador) throws Exception {
        ColaboradorSellerMantDto mantDto = colaboradorService.obtener(ipPersonaLogeada, idColaborador);
        return mensajeSupport.respuestaObtener(mantDto);
    }

    @PostMapping("listar")
    public Respuesta<Page<ColaboradorSellerOutDto>> listar(@RequestBody ColaboradorSellerInDto inDto) throws Exception {
        Page<ColaboradorSellerOutDto> dato = colaboradorService.listar(inDto);
        return mensajeSupport.respuestaListar(dato);
    }

    @PostMapping()
    public Respuesta<ColaboradorSellerMantDto> guardar(@RequestParam(required = false) MultipartFile image, @RequestPart() String jsonDato) throws Exception {
        String json = new String(jsonDato.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        ColaboradorSellerMantDto mantenimientoDto = JsonConversor.fromStringToObject(json, ColaboradorSellerMantDto.class);
        ColaboradorSellerMantDto mantOut = colaboradorService.guardar(image, mantenimientoDto);
        return mensajeSupport.respuestaAgregar(mantOut);
    }

    @PutMapping()
    public Respuesta<ColaboradorSellerMantDto> actualizar(@RequestParam(required = false) MultipartFile image, @RequestPart() String jsonDato) throws Exception {
        String json = new String(jsonDato.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        ColaboradorSellerMantDto mantenimientoDto = JsonConversor.fromStringToObject(json, ColaboradorSellerMantDto.class);
        ColaboradorSellerMantDto dato = colaboradorService.actualizar(image, mantenimientoDto);
        return mensajeSupport.respuestaActualizar(dato);
    }

    @PutMapping("{ipPersonaLogeada}")
    public Respuesta actualizarEstado(@PathVariable int ipPersonaLogeada, @RequestBody List<Integer> listaIdColaborador) throws Exception {
        colaboradorService.eliminar(ipPersonaLogeada, listaIdColaborador);
        return mensajeSupport.respuestaEliminar(null);
    }
}
