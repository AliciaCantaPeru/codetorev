/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.service.administracion;

import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author mpilar
 * @param <IN>
 * @param <OUT>
 * @param <MANT>
 */
public interface GenericSecondaryService<IN, OUT, MANT> {

    PageImpl<OUT> listar(IN in) throws Exception;

    MANT obtener(int ipPersonaLogeada, int id) throws Exception;

    MANT guardar(MultipartFile image, MANT mantenimientoDto) throws Exception;

    MANT actualizar(MultipartFile image, MANT mantenimientoDto) throws Exception;

    void eliminar(int ipPersonaLogeada, List<Integer> listaId) throws Exception;

}
