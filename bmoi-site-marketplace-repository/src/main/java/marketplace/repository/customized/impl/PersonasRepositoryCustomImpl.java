/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.customized.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import marketplace.repository.customized.PersonasRepositoryCustom;
import marketplace.repository.entity.Personas;
import marketplace.repository.entity.QCuentas;
import marketplace.repository.entity.QPersonas;
import marketplace.repository.entity.personas.BmoiPersonaOpcionEntity;
import marketplace.util.EstadoRegistro;
import marketplace.util.Rol;
import marketplace.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author Martin Pilar
 */
public class PersonasRepositoryCustomImpl implements PersonasRepositoryCustom {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager em;

    @Override
    public PageImpl<Personas> listarPersonasColaboradores(String sortCampo, String sortOrden, String seach, PageRequest pageable, boolean esCuenta) throws Exception {
        QPersonas qPersonas = QPersonas.personas;
        QCuentas qCuentas = QCuentas.cuentas;
        JPAQuery<Personas> jPAQuery = new JPAQuery<>(em);
        jPAQuery.from(qPersonas).select(qPersonas);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qCuentas.isNull().or(qCuentas.idRol.id.ne(Rol.MASTER.getId())));
        if (seach != null) {
            seach = seach.trim().toUpperCase();
            String fechaFilter = Util.getFechaFilter(seach);
            builder.and(qPersonas.nombres.upper().like("%" + seach + "%")
                    .or(qPersonas.priApellido.upper().like("%" + seach + "%"))
                    .or(qPersonas.segApellido.upper().like("%" + seach + "%"))
                    .or(qPersonas.sexo.upper().like("%" + seach + "%"))
                    .or(qPersonas.email.upper().like("%" + seach + "%"))
                    .or(qPersonas.tipDocumento.upper().like("%" + seach + "%"))
                    .or(qPersonas.numDocumento.upper().like("%" + seach + "%"))
                    .or(qPersonas.fecNacimiento.stringValue().like("%" + fechaFilter + "%"))
                    .or(qPersonas.email.upper().like("%" + seach + "%"))
                    .or(qPersonas.celular.upper().like("%" + seach + "%"))
            );
        }

        if (sortCampo != null && sortOrden != null) {
            Path<Object> fieldPath = Expressions.path(Object.class, qPersonas, sortCampo);
            OrderSpecifier orderSpecifier = new OrderSpecifier(Order.valueOf(sortOrden.toUpperCase()), fieldPath);
            jPAQuery.orderBy(orderSpecifier);
        }
        jPAQuery.leftJoin(qPersonas.cuentas, qCuentas);
        jPAQuery.where(builder);
        jPAQuery.offset(pageable.getOffset());
        jPAQuery.limit(pageable.getPageSize());
        return new PageImpl(jPAQuery.fetch(), pageable, jPAQuery.fetchCount());
    }

    @Override
    public PageImpl<Personas> listarPersonasCuentasBmoi(String sortCampo, String sortOrden, String seach, PageRequest pageable, boolean esCuenta) throws Exception {
        QPersonas qPersonas = QPersonas.personas;
        QCuentas qCuentas = QCuentas.cuentas;

        JPAQuery<Personas> jPAQuery = new JPAQuery<>(em);
        jPAQuery.from(qPersonas).select(qPersonas);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qCuentas.idRol.id.ne(Rol.MASTER.getId()));
        if (esCuenta) {
            builder.and(qPersonas.cuentas.estado.eq(EstadoRegistro.ACTIVO.getId()));
            builder.and(qPersonas.cuentas.id.isNotNull());
        }
        if (seach != null) {
            seach = seach.trim().toUpperCase();
            String fechaFilter = Util.getFechaFilter(seach);
            builder.and(qPersonas.nombres.upper().like("%" + seach + "%")
                    .or(qPersonas.priApellido.upper().like("%" + seach + "%"))
                    .or(qPersonas.segApellido.upper().like("%" + seach + "%"))
                    .or(qPersonas.sexo.upper().like("%" + seach + "%"))
                    .or(qPersonas.email.upper().like("%" + seach + "%"))
                    .or(qPersonas.tipDocumento.upper().like("%" + seach + "%"))
                    .or(qPersonas.numDocumento.upper().like("%" + seach + "%"))
                    .or(qPersonas.fecNacimiento.stringValue().like("%" + fechaFilter + "%"))
                    .or(qPersonas.celular.upper().like("%" + seach + "%"))
            );
        }
        if (sortCampo != null && sortOrden != null) {
            Path<Object> fieldPath = Expressions.path(Object.class, qPersonas, sortCampo);
            OrderSpecifier orderSpecifier = new OrderSpecifier(Order.valueOf(sortOrden.toUpperCase()), fieldPath);
            jPAQuery.orderBy(orderSpecifier);
        }
        jPAQuery.leftJoin(qPersonas.cuentas, qCuentas);
        jPAQuery.where(builder);
        jPAQuery.offset(pageable.getOffset());
        jPAQuery.limit(pageable.getPageSize());
        return new PageImpl(jPAQuery.fetch(), pageable, jPAQuery.fetchCount());
    }

    @Override
    public List<BmoiPersonaOpcionEntity> listarOpcionPersonaCuentaBmoi(Integer idPersona) throws Exception {
        QPersonas qPersonas = QPersonas.personas;
        QCuentas qCuentas = QCuentas.cuentas;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(
                qCuentas.id.isNull()
        );
        if (idPersona != null) {
            builder.or(qPersonas.id.eq(idPersona));
        }
        List<BmoiPersonaOpcionEntity> listaPersonas = jpaQueryFactory
                .from(qPersonas).select(
                Projections.bean(BmoiPersonaOpcionEntity.class,
                        qPersonas.nombres.concat(" ").concat(" ").concat(qPersonas.priApellido).concat(" ").concat(qPersonas.segApellido).as("nombre"),
                        qPersonas.email,
                        qPersonas.id))
                .leftJoin(qPersonas.cuentas, qCuentas)
                .where(builder).fetch();
        return listaPersonas;
    }

    @Override
    public String obtenerNombreCompleto(String correo) throws Exception {
        QPersonas qPersonas = QPersonas.personas;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qPersonas.email.eq(correo));
        String nombreCompleto = jpaQueryFactory
                .from(qPersonas)
                .select(qPersonas.nombres.concat(" ").concat(" ").concat(qPersonas.priApellido).concat(" ").concat(qPersonas.segApellido))
                .where(builder)
                .fetchOne();
        return nombreCompleto;
    }

}
