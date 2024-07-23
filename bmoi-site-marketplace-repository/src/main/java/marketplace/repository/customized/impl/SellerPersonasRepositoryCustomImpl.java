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
import marketplace.repository.customized.SellerPersonasRepositoryCustom;
import marketplace.repository.entity.Personas;
import marketplace.repository.entity.QSellerCuentas;
import marketplace.repository.entity.QSellerPersonas;
import marketplace.repository.entity.QSellers;
import marketplace.repository.entity.SellerPersonas;
import marketplace.repository.entity.personas.SellerPersonaOpcionEntity;
import marketplace.util.EstadoRegistro;
import marketplace.util.Rol;
import marketplace.util.TipoUsuarioLogin;
import marketplace.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author Martin Pilar
 */
public class SellerPersonasRepositoryCustomImpl implements SellerPersonasRepositoryCustom {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager em;

    @Override
    public PageImpl<SellerPersonas> listarSellerPersonas(String tipoUsuario, String sortCampo, String sortOrden, String seach, Integer idSeller, PageRequest pageable, boolean esCuenta) throws Exception {
        QSellerPersonas qSellerPersonas = QSellerPersonas.sellerPersonas;
        QSellerCuentas qSellerCuentas = QSellerCuentas.sellerCuentas;
        QSellers qSellers = QSellers.sellers;
        JPAQuery<SellerPersonas> jPAQuery = new JPAQuery<>(em);
        jPAQuery.select(qSellerPersonas).from(qSellerPersonas);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qSellerPersonas.estado.eq(EstadoRegistro.ACTIVO.getId()));
        if (esCuenta) {
            builder.and(qSellerPersonas.sellerCuentas.estado.eq(EstadoRegistro.ACTIVO.getId()));
            builder.and(qSellerPersonas.sellerCuentas.id.isNotNull());
        }
        if (tipoUsuario == TipoUsuarioLogin.BMOI.getValor()) {
            builder.and(qSellers.id.eq(1));
        }
        if (tipoUsuario == TipoUsuarioLogin.SELLER.getValor()) {
            builder.and(qSellers.id.ne(1));
        }
        if (seach != null) {
            seach = seach.trim().toUpperCase();
            String fechaFilter = Util.getFechaFilter(seach);
            builder.and(qSellerPersonas.priNombre.upper().like("%" + seach + "%")
                    .or(qSellerPersonas.segNombre.upper().like("%" + seach + "%"))
                    .or(qSellerPersonas.priApellido.upper().like("%" + seach + "%"))
                    .or(qSellerPersonas.segApellido.upper().like("%" + seach + "%"))
                    .or(qSellerPersonas.sexo.upper().like("%" + seach + "%"))
                    .or(qSellerPersonas.email.upper().like("%" + seach + "%"))
                    .or(qSellerPersonas.tipDocumento.upper().like("%" + seach + "%"))
                    .or(qSellerPersonas.numDocumento.upper().like("%" + seach + "%"))
                    .or(qSellerPersonas.fecNacimiento.stringValue().like("%" + fechaFilter + "%"))
                    .or(qSellerPersonas.email.upper().like("%" + seach + "%"))
                    .or(qSellerPersonas.celular1.upper().like("%" + seach + "%"))
                    .or(qSellerPersonas.celular2.upper().like("%" + seach + "%"))
                    .or(qSellerPersonas.cargo.upper().like("%" + seach + "%"))
                    .or(qSellerPersonas.fecNacimiento.stringValue().like("%" + fechaFilter + "%"))
                    .or(qSellerPersonas.usuRegistro.upper().like("%" + seach + "%"))
                    .or(qSellerPersonas.usuActualizacion.upper().like("%" + seach + "%"))
                    .or(qSellerPersonas.fecRegistro.stringValue().like("%" + fechaFilter + "%"))
                    .or(qSellerPersonas.fecActualizacion.stringValue().like("%" + fechaFilter + "%"))
            );
        }
        if (idSeller != null) {
            builder.and(qSellers.id.eq(idSeller));
        }
        if (sortCampo != null && sortOrden != null) {
            Path<Object> fieldPath = Expressions.path(Object.class, qSellerPersonas, sortCampo);
            OrderSpecifier orderSpecifier = new OrderSpecifier(Order.valueOf(sortOrden.toUpperCase()), fieldPath);
            jPAQuery.orderBy(orderSpecifier);
        }
        jPAQuery.leftJoin(qSellerPersonas.sellerCuentas, qSellerCuentas);
        jPAQuery.leftJoin(qSellerPersonas.idseller, qSellers);
        jPAQuery.where(builder);
        jPAQuery.offset(pageable.getOffset());
        jPAQuery.limit(pageable.getPageSize());
        return new PageImpl(jPAQuery.fetch(), pageable, jPAQuery.fetchCount());
    }

    @Override
    public PageImpl<SellerPersonas> listarSellerPersonasColaboradores(String tipoUsuario, String sortCampo, String sortOrden, String seach, PageRequest pageable, boolean esCuenta) throws Exception {
        QSellerPersonas qSellerPersonas = QSellerPersonas.sellerPersonas;
        QSellerCuentas qSellerCuentas = QSellerCuentas.sellerCuentas;
        JPAQuery<Personas> jPAQuery = new JPAQuery<>(em);
        jPAQuery.select(qSellerPersonas).from(qSellerPersonas);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qSellerPersonas.estado.eq(EstadoRegistro.ACTIVO.getId()));
        builder.and(qSellerPersonas.idseller.isNull());
        builder.and(qSellerPersonas.cargo.ne("SYSTEM"));
        if (esCuenta) {
            builder.and(qSellerCuentas.id.isNotNull());
            builder.and(qSellerCuentas.idRol.id.ne(Rol.MASTER.getId()));
        }
        if (tipoUsuario == TipoUsuarioLogin.BMOI.getValor()) {
            builder.and(qSellerCuentas.idSellerpersona.idseller.id.eq(1));
        }
        if (tipoUsuario == TipoUsuarioLogin.SELLER.getValor()) {
            builder.and(qSellerCuentas.idSellerpersona.idseller.id.ne(1));
        }
        if (seach != null) {
            seach = seach.trim().toUpperCase();
            String fechaFilter = Util.getFechaFilter(seach);
            builder.and(qSellerPersonas.priNombre.upper().like("%" + seach + "%")
                    .or(qSellerPersonas.segNombre.upper().like("%" + seach + "%"))
                    .or(qSellerPersonas.priApellido.upper().like("%" + seach + "%"))
                    .or(qSellerPersonas.segApellido.upper().like("%" + seach + "%"))
                    .or(qSellerPersonas.sexo.upper().like("%" + seach + "%"))
                    .or(qSellerPersonas.email.upper().like("%" + seach + "%"))
                    .or(qSellerPersonas.tipDocumento.upper().like("%" + seach + "%"))
                    .or(qSellerPersonas.numDocumento.upper().like("%" + seach + "%"))
                    .or(qSellerPersonas.fecNacimiento.stringValue().like("%" + fechaFilter + "%"))
                    .or(qSellerPersonas.email.upper().like("%" + seach + "%"))
                    .or(qSellerPersonas.celular1.upper().like("%" + seach + "%"))
                    .or(qSellerPersonas.celular2.upper().like("%" + seach + "%"))
                    .or(qSellerPersonas.cargo.upper().like("%" + seach + "%"))
            );
        }
        if (sortCampo != null && sortOrden != null) {
            Path<Object> fieldPath = Expressions.path(Object.class, qSellerPersonas, sortCampo);
            OrderSpecifier orderSpecifier = new OrderSpecifier(Order.valueOf(sortOrden.toUpperCase()), fieldPath);
            jPAQuery.orderBy(orderSpecifier);
        }
        jPAQuery.leftJoin(qSellerPersonas.sellerCuentas, qSellerCuentas);
        jPAQuery.where(builder);
        jPAQuery.offset(pageable.getOffset());
        jPAQuery.limit(pageable.getPageSize());
        return new PageImpl(jPAQuery.fetch(), pageable, jPAQuery.fetchCount());
    }

    @Override
    public List<SellerPersonaOpcionEntity> listarOpcionPersonaSellerCuenta(String tipoUsuario, int idSeller, Integer idSellerPersona) throws Exception {
        QSellerPersonas qSellerPersonas = QSellerPersonas.sellerPersonas;
        QSellerCuentas qSellerCuentas = QSellerCuentas.sellerCuentas;
        BooleanBuilder builder = new BooleanBuilder();
        builder
                .and(
                        qSellerPersonas.estado.eq(EstadoRegistro.ACTIVO.getId())
                                .and(qSellerPersonas.idseller.id.eq(idSeller))
                                .and(qSellerCuentas.id.isNull()
                                ));
        if (idSellerPersona != null) {
            builder.or(qSellerPersonas.id.eq(idSellerPersona));
        }
//        if (tipoUsuario.equals(TipoUsuarioLogin.BMOI.getValor())) {
//            builder.and(qSellerCuentas.idSellerpersona.idseller.id.eq(1));
//        }
//        if (tipoUsuario.equals(TipoUsuarioLogin.SELLER.getValor())) {
//            builder.and(qSellerCuentas.idSellerpersona.idseller.id.ne(1));
//        }
        List<SellerPersonaOpcionEntity> listaPersonas = jpaQueryFactory.from(qSellerPersonas).select(Projections.bean(SellerPersonaOpcionEntity.class,
                qSellerPersonas.priNombre.concat(" ").concat(qSellerPersonas.segNombre).concat(" ").concat(qSellerPersonas.priApellido).concat(" ").concat(qSellerPersonas.segApellido).as("nombre"),
                qSellerPersonas.email,
                qSellerPersonas.id))
                .leftJoin(qSellerPersonas.sellerCuentas, qSellerCuentas)
                .where(builder).fetch();
        return listaPersonas;

    }

    @Override
    public String obtenerNombreCompleto(String tipoUsuario, String correo) throws Exception {
        QSellerPersonas qSellerPersonas = QSellerPersonas.sellerPersonas;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qSellerPersonas.email.eq(correo));
        if (tipoUsuario == TipoUsuarioLogin.BMOI.getValor()) {
            builder.and(qSellerPersonas.idseller.id.eq(1));
        }
        if (tipoUsuario == TipoUsuarioLogin.SELLER.getValor()) {
            builder.and(qSellerPersonas.idseller.id.ne(1));
        }
        String nombreCompleto = jpaQueryFactory
                .from(qSellerPersonas)
                .select(qSellerPersonas.priNombre.concat(" ").concat(qSellerPersonas.segNombre).concat(" ").concat(qSellerPersonas.priApellido).concat(" ").concat(qSellerPersonas.segApellido))
                .where(builder)
                .fetchOne();
        return nombreCompleto;
    }

    @Override
    public String obtenerNombreCompletoPersonaPrincipal(String tipoUsuario, int idSeller) throws Exception {
        QSellerPersonas qSellerPersonas = QSellerPersonas.sellerPersonas;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qSellerPersonas.idseller.id.eq(idSeller));
        builder.and(qSellerPersonas.cntPrincipal.eq(1));
        if (tipoUsuario == TipoUsuarioLogin.BMOI.getValor()) {
            builder.and(qSellerPersonas.idseller.id.eq(1));
        }
        if (tipoUsuario == TipoUsuarioLogin.SELLER.getValor()) {
            builder.and(qSellerPersonas.idseller.id.ne(1));
        }
        String nombreCompleto = jpaQueryFactory
                .from(qSellerPersonas)
                .select(qSellerPersonas.priNombre.concat(" ").concat(qSellerPersonas.segNombre).concat(" ").concat(qSellerPersonas.priApellido).concat(" ").concat(qSellerPersonas.segApellido))
                .where(builder)
                .fetchFirst();
        return nombreCompleto;
    }

}
