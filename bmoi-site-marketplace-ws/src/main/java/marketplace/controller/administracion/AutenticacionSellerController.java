/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.controller.administracion;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import marketplace.security.util.JwtTokenUtil;
import marketplace.security.util.JwtUser;
import marketplace.service.administracion.dto.autenticacion.AutLoginInDto;
import marketplace.service.administracion.dto.autenticacion.AutUsuarioOutDto;
import marketplace.service.administracion.dto.autenticacion.CambiarContraseniaInDto;
import marketplace.service.administracion.dto.autenticacion.KeyInDto;
import marketplace.service.administracion.dto.autenticacion.TokenDto;
import marketplace.service.autenticacion.AutenticacionBmoiService;
import marketplace.service.autenticacion.AutenticacionSellerService;
import static marketplace.service.autenticacion.impl.AutenticacionSellerServiceImpl.SEPARATOR_USERNAME;
import marketplace.support.MensajeSupport;
import marketplace.support.dto.Respuesta;
import marketplace.util.Encriptador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import static org.springframework.util.StringUtils.isEmpty;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mpilar
 */
@RestController
@RequestMapping(value = "api/autenticacion")
@Slf4j
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST})
public class AutenticacionSellerController {

    @Autowired
    private AutenticacionSellerService autenticacionSellerService;

    @Autowired
    private AutenticacionBmoiService autenticacionBmoiService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private Encriptador encriptador;

    @Autowired
    private MensajeSupport mensajeSupport;

    @PostMapping(value = "login")
    public ResponseEntity<Respuesta> iniciarSesion(@RequestBody AutLoginInDto usuarioIn) throws Exception {
        String userName = usuarioIn.getTipoUsuario() + SEPARATOR_USERNAME + usuarioIn.getCorreo();
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(userName, usuarioIn.getContrasenia());
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final JwtUser userDetails = (JwtUser) authentication.getPrincipal();
        AutUsuarioOutDto autUsuarioOutDto = autenticacionSellerService.obtenerUsuario(userDetails.getIdUsuario());
        Map<String, Object> claims = new HashMap<>();
        claims.put("user", autUsuarioOutDto);
        final String token = jwtTokenUtil.generateToken(claims, userDetails);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        httpHeaders.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);
        return ResponseEntity.ok().headers(httpHeaders).body(mensajeSupport.respuestaObtener(autUsuarioOutDto));
    }

    @PostMapping(value = "refresh")
    public ResponseEntity<Respuesta<TokenDto>> refreshAndGetAuthenticationToken(@RequestBody TokenDto request) throws Exception {
        try {
            if (isEmpty(request.getToken()) || !request.getToken().startsWith("Bearer ")) {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
            final String authToken = request.getToken().replace("Bearer ", "");
            String username = jwtTokenUtil.getUsernameFromTokenExpire(authToken);
            JwtUser user = (JwtUser) autenticacionSellerService.loadUserByUsername(username);
            if (!jwtTokenUtil.canTokenBeRefreshed(authToken, user.getLastPasswordResetDate())) {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
            String refreshedToken = jwtTokenUtil.refreshToken(authToken);
            TokenDto res = new TokenDto();
            res.setToken(refreshedToken);
            return ResponseEntity.ok().body(mensajeSupport.respuestaObtener(res));
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(value = "cambiarContrasenia/enviarLink/{tipoUsuario}/{corrreoUsuario}")
    public Respuesta cambiarContraseniaEnviarLink(@PathVariable String corrreoUsuario, @PathVariable String tipoUsuario) throws Exception {
        autenticacionSellerService.cambiarContraseniaEnviarLink(corrreoUsuario, tipoUsuario);
        return mensajeSupport.respuestaObtener(null);
    }

    @PostMapping(value = "cambiarContrasenia/verificarKey")
    public Respuesta<String> cambiarContraseniaVerficarKey(@RequestBody KeyInDto inDto) throws Exception {
        String correo = autenticacionSellerService.cambiarContraseniaVerficarKey(inDto);
        return mensajeSupport.respuestaObtener(correo);
    }

    @PostMapping(value = "cambiarContrasenia")
    public Respuesta cambiarContrasenia(@RequestBody CambiarContraseniaInDto inDto) throws Exception {
        autenticacionSellerService.cambiarContrasenia(inDto);
        return mensajeSupport.respuestaObtener(null);
    }

    @PostMapping(value = "activarCuenta")
    public Respuesta activarCuenta(@RequestBody CambiarContraseniaInDto inDto) throws Exception {
        autenticacionSellerService.activarCuenta(inDto);
        return mensajeSupport.respuestaObtener(null);
    }

//    @GetMapping()
//    public Respuesta<String> getUTf() throws Exception {
//        return mensajeSupport.respuestaObtener(Charset.defaultCharset().toString());
//    }
    @PostMapping("/tema/{idSellerPersona}/{tema}")
    public ResponseEntity<Respuesta> guardarTema(@PathVariable int idSellerPersona, @PathVariable String tema) throws Exception {
        String token = autenticacionSellerService.guardarTema(idSellerPersona, tema);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        httpHeaders.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);
        return ResponseEntity.ok().headers(httpHeaders).body(mensajeSupport.respuestaActualizar(null));
    }
}
