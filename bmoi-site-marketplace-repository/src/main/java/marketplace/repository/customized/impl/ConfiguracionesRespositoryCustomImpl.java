/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.customized.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import marketplace.repository.customized.ConfiguracionesRespositoryCustom;
import marketplace.repository.entity.QTblmasterConfiguraciones;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
public class ConfiguracionesRespositoryCustomImpl implements ConfiguracionesRespositoryCustom {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Override
    public String obtenerValor(String clave) throws Exception {
        QTblmasterConfiguraciones qConfiguraciones = QTblmasterConfiguraciones.tblmasterConfiguraciones;
        Predicate p = qConfiguraciones.clave.eq(clave);
        String valor = jpaQueryFactory
                .select(qConfiguraciones.valor)
                .from(qConfiguraciones)
                .where(p)
                .fetchOne();
        return valor;
    }

}
