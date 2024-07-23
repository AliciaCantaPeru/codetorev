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
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import marketplace.repository.customized.SellerRepositoryCustom;
import marketplace.repository.entity.QSellerPersonas;
import marketplace.repository.entity.QSellerPlan;
import marketplace.repository.entity.QSellers;
import marketplace.repository.entity.sellers.SellerEntity;
import marketplace.support.dto.Opcion;
import marketplace.util.EstadoRegistro;
import marketplace.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author Martin Pilar
 */
public class SellerRepositoryCustomImpl implements SellerRepositoryCustom {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager em;

    @Override
    public PageImpl<SellerEntity> listarSeller(String sortCampo, String sortOrden, String seach, PageRequest pageable) throws Exception {
        QSellers qSellers = QSellers.sellers;
        QSellerPlan qSellerPlan = QSellerPlan.sellerPlan;
        QSellerPersonas qSellerPersonas = QSellerPersonas.sellerPersonas;
        JPAQuery<SellerEntity> jPAQuery = new JPAQuery<>(em);
        jPAQuery.select(
                Projections.bean(SellerEntity.class,
                        qSellers.id,
                        qSellers.celular,
                        qSellers.descripcion,
                        qSellers.email1,
                        qSellers.email2,
                        qSellers.estado,
                        qSellers.fecActualizacion,
                        qSellers.fecRegistro,
                        qSellers.feciniActividades,
                        qSellers.img,
                        qSellers.nomComercial,
                        qSellers.razSocial,
                        qSellers.rubro,
                        qSellers.ruc,
                        qSellers.telefono,
                        qSellers.tipEmpresa,
                        qSellers.usuActualizacion,
                        qSellers.usuRegistro,
                        qSellers.website,
                        qSellers.totPedidos,
                        qSellers.totPedMonto,
                        qSellerPersonas.priNombre.concat(" ").concat(qSellerPersonas.segNombre).concat(" ").concat(qSellerPersonas.priApellido).concat(" ").concat(qSellerPersonas.segApellido).as("contacto"),
                        qSellerPlan.fecFinal
                )
        ).from(qSellers);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qSellers.estado.eq(EstadoRegistro.ACTIVO.getId()));
        builder.and(qSellers.id.ne(1));
        builder.and(qSellerPersonas.cntPrincipal.eq(1));

        if (seach != null) {
            seach = seach.trim().toUpperCase();
            String fechaFilter = Util.getFechaFilter(seach);
            builder.and(qSellers.razSocial.upper().like("%" + seach + "%")
                    .or(qSellers.tipEmpresa.upper().like("%" + seach + "%"))
                    .or(qSellers.nomComercial.upper().like("%" + seach + "%"))
                    .or(qSellers.rubro.upper().like("%" + seach + "%"))
                    .or(qSellers.ruc.upper().like("%" + seach + "%"))
                    .or(qSellers.telefono.upper().like("%" + seach + "%"))
                    .or(qSellers.celular.upper().like("%" + seach + "%"))
                    .or(qSellers.website.upper().like("%" + seach + "%"))
                    .or(qSellers.email1.upper().like("%" + seach + "%"))
                    .or(qSellers.email2.upper().like("%" + seach + "%"))
                    .or(qSellers.descripcion.upper().like("%" + seach + "%"))
                    .or(qSellers.iddireccion.direccion.upper().like("%" + seach + "%"))
                    .or(qSellers.iddireccion.direccion.upper().like("%" + seach + "%"))
                    .or(qSellers.totPedMonto.like("%" + seach + "%"))
                    .or(qSellers.totPedidos.like("%" + seach + "%"))
                    .or(qSellers.usuRegistro.upper().like("%" + seach + "%"))
                    .or(qSellers.usuActualizacion.upper().like("%" + seach + "%"))
                    .or(qSellers.feciniActividades.stringValue().like("%" + fechaFilter + "%"))
                    .or(qSellers.fecRegistro.stringValue().like("%" + fechaFilter + "%"))
                    .or(qSellers.fecActualizacion.stringValue().like("%" + fechaFilter + "%"))
                    .or(qSellerPlan.fecFinal.stringValue().like("%" + fechaFilter + "%"))
                    .or(qSellerPersonas.priNombre.concat(" ").concat(qSellerPersonas.segNombre).concat(" ").concat(qSellerPersonas.priApellido).concat(" ").concat(qSellerPersonas.segApellido).upper().like("%" + seach + "%"))
            );
        }
        if (sortCampo != null && sortOrden != null) {
            OrderSpecifier<?>[] sortOrder = null;
            switch (sortCampo) {
                case "contacto":
                    String sortCampoNombre = "priNombre";
                    String sortCampoSegNombre = "segNombre";
                    String sortCampoPriApellido = "priApellido";
                    String sortCampoSegApellido = "segApellido";
                    Path<Object> fieldPathNombre = Expressions.path(Object.class, qSellerPersonas, sortCampoNombre);
                    Path<Object> fieldPathSegNombre = Expressions.path(Object.class, qSellerPersonas, sortCampoSegNombre);
                    Path<Object> fieldPathPriApellido = Expressions.path(Object.class, qSellerPersonas, sortCampoPriApellido);
                    Path<Object> fieldPathSegApellido = Expressions.path(Object.class, qSellerPersonas, sortCampoSegApellido);
                    OrderSpecifier orderSpecifier1 = new OrderSpecifier(Order.valueOf(sortOrden.toUpperCase()), fieldPathNombre);
                    OrderSpecifier orderSpecifier2 = new OrderSpecifier(Order.valueOf(sortOrden.toUpperCase()), fieldPathSegNombre);
                    OrderSpecifier orderSpecifier3 = new OrderSpecifier(Order.valueOf(sortOrden.toUpperCase()), fieldPathPriApellido);
                    OrderSpecifier orderSpecifier4 = new OrderSpecifier(Order.valueOf(sortOrden.toUpperCase()), fieldPathSegApellido);
                    sortOrder = new OrderSpecifier[]{
                        orderSpecifier1,
                        orderSpecifier2,
                        orderSpecifier3,
                        orderSpecifier4
                    };
                    break;
                case "fecFinal":
                    Path<Object> fieldFecFinal = Expressions.path(Object.class, qSellerPlan, sortCampo);
                    OrderSpecifier orderSpecifier = new OrderSpecifier(Order.valueOf(sortOrden.toUpperCase()), fieldFecFinal);
                    sortOrder = new OrderSpecifier[]{
                        orderSpecifier
                    };
                    break;
                default:
                    Path<Object> fieldPath = Expressions.path(Object.class, qSellers, sortCampo);
                    sortOrder = new OrderSpecifier[]{
                        new OrderSpecifier(Order.valueOf(sortOrden.toUpperCase()), fieldPath),};
                    break;
            }
            jPAQuery.orderBy(sortOrder);
        }
        jPAQuery.leftJoin(qSellers.sellerPlanSet, qSellerPlan);
        jPAQuery.leftJoin(qSellers.sellerPersonasSet, qSellerPersonas);
        jPAQuery.where(builder);
        jPAQuery.offset(pageable.getOffset());
        jPAQuery.limit(pageable.getPageSize());
        return new PageImpl(jPAQuery.fetch(), pageable, jPAQuery.fetchCount());
    }

    @Override
    public Integer obtenerIdSeller(int idSellerPersona) throws Exception {
        QSellerPersonas qSellerPersonas = QSellerPersonas.sellerPersonas;
        Predicate p = qSellerPersonas.id.eq(idSellerPersona);
        Integer idCliente = jpaQueryFactory
                .from(qSellerPersonas)
                .select(qSellerPersonas.id)
                .where(p).fetchOne();
        return idCliente;
    }

    @Override
    public List<Opcion> listarOpcionSeller(int idSeller) throws Exception {
        QSellers qSellers = QSellers.sellers;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qSellers.estado.eq(EstadoRegistro.ACTIVO.getId()));
        if (idSeller != -1) {
            booleanBuilder.or(qSellers.id.eq(idSeller));
        }
        List<Opcion> lista = jpaQueryFactory
                .from(qSellers)
                .select(Projections.bean(Opcion.class, qSellers.id, qSellers.razSocial.as("nombre")))
                .where(booleanBuilder).fetch();
        return lista;
    }

}
