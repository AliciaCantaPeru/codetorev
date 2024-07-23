/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.customized.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import marketplace.repository.customized.RolesRepositoryCustom;
import marketplace.repository.entity.QRoles;
import marketplace.support.dto.Opcion;
import marketplace.util.EstadoRegistro;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
public class RolesRepositoryCustomImpl implements RolesRepositoryCustom {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Opcion> listarRolesPersona(Predicate p) throws Exception {
        QRoles qRoles = QRoles.roles;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(p);
        builder.and(qRoles.estado.eq(EstadoRegistro.ACTIVO.getId()));
        List<Opcion> listaRol = jpaQueryFactory.from(qRoles).select(Projections.bean(Opcion.class, qRoles.nombre, qRoles.id)).where(builder).fetch();
        return listaRol;
    }

}
