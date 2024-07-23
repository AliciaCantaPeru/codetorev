///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package marketplace.repository.customized.impl;
//
//import com.querydsl.core.BooleanBuilder;
//import com.querydsl.core.types.Order;
//import com.querydsl.core.types.OrderSpecifier;
//import com.querydsl.core.types.Path;
//import com.querydsl.core.types.Projections;
//import com.querydsl.core.types.dsl.Expressions;
//import com.querydsl.jpa.impl.JPAQuery;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import marketplace.repository.customized.UnidadesNegocioRepositoryCustom;
//import marketplace.repository.entity.QDetalleSolicitud;
//import marketplace.repository.entity.QProductos;
//import marketplace.repository.entity.QSolicitudes;
//import marketplace.repository.entity.QUnidadesNegocio;
//import marketplace.repository.entity.UnidadesNegocio;
//import marketplace.repository.entity.unidadnegocio.UnidadNegocioOpcionEntity;
//import marketplace.repository.entity.unidadnegocio.UnidadNegocioProductoEntity;
//import marketplace.support.dto.Opcion;
//import marketplace.util.EstadoRegistro;
//import marketplace.util.EstadoSolicitud;
//import marketplace.util.Util;
//
///**
// *
// * @author Martin Pilar mpilarcastillejo@gmail.com
// */
//public class UnidadesNegocioRepositoryCustomImpl implements UnidadesNegocioRepositoryCustom {
//
//    @Autowired
//    private JPAQueryFactory jpaQueryFactory;
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Override
//    public PageImpl<UnidadesNegocio> listarNegocios(String sortCampo, String sortOrden, String seach, PageRequest pageable) throws Exception {
//        QUnidadesNegocio qUnidadesNegocio = QUnidadesNegocio.unidadesNegocio;
//        JPAQuery<UnidadesNegocio> jPAQuery = new JPAQuery<>(em);
//        jPAQuery.select(qUnidadesNegocio)
//                .from(qUnidadesNegocio);
//        BooleanBuilder builder = new BooleanBuilder();
//        builder.and(qUnidadesNegocio.estado.eq(EstadoRegistro.ACTIVO.getId()));
//        if (seach != null) {
//            seach = seach.trim().toUpperCase();
//            String fechaFilter = Util.getFechaFilter(seach);
//            builder.and(qUnidadesNegocio.razSocial.upper().like("%" + seach + "%")
//                    .or(qUnidadesNegocio.rubro.upper().like("%" + seach + "%"))
//                    .or(qUnidadesNegocio.ruc.upper().like("%" + seach + "%"))
//                    .or(qUnidadesNegocio.tipEmpresa.upper().like("%" + seach + "%"))
//                    .or(qUnidadesNegocio.nombre.upper().like("%" + seach + "%"))
//                    .or(qUnidadesNegocio.email.upper().like("%" + seach + "%"))
//                    .or(qUnidadesNegocio.telefono.upper().like("%" + seach + "%"))
//                    .or(qUnidadesNegocio.web.upper().like("%" + seach + "%"))
//                    .or(qUnidadesNegocio.otros.upper().like("%" + seach + "%"))
//                    .or(qUnidadesNegocio.usuRegistro.upper().like("%" + seach + "%"))
//                    .or(qUnidadesNegocio.usuActualizacion.upper().like("%" + seach + "%"))
//                    .or(qUnidadesNegocio.fecRegistro.stringValue().like("%" + fechaFilter + "%"))
//                    .or(qUnidadesNegocio.fecActualizacion.stringValue().like("%" + fechaFilter + "%"))
//            );
//        }
//        if (sortCampo != null && sortOrden != null) {
//            Path<Object> fieldPath = Expressions.path(Object.class, qUnidadesNegocio, sortCampo);
//            OrderSpecifier orderSpecifier = new OrderSpecifier(Order.valueOf(sortOrden.toUpperCase()), fieldPath);
//            jPAQuery.orderBy(orderSpecifier);
//        }
//        jPAQuery.where(builder);
//        jPAQuery.offset(pageable.getOffset());
//        jPAQuery.limit(pageable.getPageSize());
//        return new PageImpl(jPAQuery.fetch(), pageable, jPAQuery.fetchCount());
//    }
//
//    @Override
//    public List<Opcion> listarUndNegocio(Integer idUndNegocio) {
//        QUnidadesNegocio qUnidadesNegocio = QUnidadesNegocio.unidadesNegocio;
//        BooleanBuilder booleanBuilder = new BooleanBuilder();
//        booleanBuilder.and(qUnidadesNegocio.estado.eq(EstadoRegistro.ACTIVO.getId()));
//        if (idUndNegocio != null) {
//            booleanBuilder.or(qUnidadesNegocio.id.eq(idUndNegocio));
//        }
//        List<Opcion> lista = jpaQueryFactory
//                .from(qUnidadesNegocio)
//                .select(Projections.bean(Opcion.class, qUnidadesNegocio.id, qUnidadesNegocio.razSocial.as("nombre")))
//                .where(booleanBuilder).fetch();
//        return lista;
//    }
//
//    @Override
//    public List<UnidadNegocioOpcionEntity> listarUndNegocio() throws Exception {
//        QUnidadesNegocio qUnidadesNegocio = QUnidadesNegocio.unidadesNegocio;
//        BooleanBuilder booleanBuilder = new BooleanBuilder();
//        booleanBuilder.and(qUnidadesNegocio.estado.eq(EstadoRegistro.ACTIVO.getId()));
//        List<UnidadNegocioOpcionEntity> lista = jpaQueryFactory
//                .from(qUnidadesNegocio)
//                .select(Projections.bean(UnidadNegocioOpcionEntity.class,
//                        qUnidadesNegocio.id,
//                        qUnidadesNegocio.razSocial.as("nombre"),
//                        qUnidadesNegocio.foto,
//                        qUnidadesNegocio.ruc)
//                ).where(booleanBuilder).fetch();
//        return lista;
//    }
//
//    @Override
//    public List<UnidadNegocioProductoEntity> listarUnidadNegocioProductoEntity() throws Exception {
//        QDetalleSolicitud qDetalleSolicitud = QDetalleSolicitud.detalleSolicitud;
//        QSolicitudes qSolicitudes = QSolicitudes.solicitudes;
//        QProductos qProductos = QProductos.productos;
//        QUnidadesNegocio qUnidadesNegocio = QUnidadesNegocio.unidadesNegocio;
//        BooleanBuilder builder = new BooleanBuilder();
//        builder.and(qSolicitudes.estado.eq(EstadoRegistro.ACTIVO.getId()));
//        builder.and(qSolicitudes.estSolicitud.in(EstadoSolicitud.VENTA.getValor(), EstadoSolicitud.PRUEBA.getValor(), EstadoSolicitud.ALQUILER.getValor()));
//        List<UnidadNegocioProductoEntity> lista = jpaQueryFactory
//                .from(qDetalleSolicitud)
//                .select(Projections.bean(UnidadNegocioProductoEntity.class,
//                        qUnidadesNegocio.id.as("idUnidadNegocio"),
//                        qUnidadesNegocio.razSocial.as("nombreUnidadNegocio"),
//                        qProductos.id.as("idProducto"),
//                        qProductos.nombre.as("nombreProducto")
//                ))
//                .leftJoin(qDetalleSolicitud.idsolicitud, qSolicitudes)
//                .leftJoin(qDetalleSolicitud.idproducto, qProductos)
//                .leftJoin(qSolicitudes.idunidadnegocio, qUnidadesNegocio)
//                .groupBy(qProductos.nombre)
//                .orderBy(qUnidadesNegocio.razSocial.asc())
//                .where(builder)
//                .fetch();
//        return lista;
//    }
//
//}
