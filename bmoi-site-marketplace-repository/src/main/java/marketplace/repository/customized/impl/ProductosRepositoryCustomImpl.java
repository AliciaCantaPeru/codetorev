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
import marketplace.repository.customized.ProductosRepositoryCustom;
import marketplace.repository.entity.Clientes;
import marketplace.repository.entity.Productos;
import marketplace.repository.entity.QProductos;
import marketplace.repository.entity.producto.ProductoSolicitudOptionEntity;
import marketplace.repository.entity.productos.ProductoOptionEntity;
import marketplace.support.dto.Opcion;
import marketplace.util.EstadoRegistro;
import marketplace.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
public class ProductosRepositoryCustomImpl implements ProductosRepositoryCustom {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager em;

    @Override
    public PageImpl<Productos> listarProducto(String sortCampo, String sortOrden, String seach, PageRequest pageable, Integer idSeller, int seccion) throws Exception {
        QProductos qProductos = QProductos.productos;
        JPAQuery<Clientes> jPAQuery = new JPAQuery<>(em);
        jPAQuery.select(qProductos)
                .from(qProductos);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qProductos.estado.eq(EstadoRegistro.ACTIVO.getId()));
        if (idSeller != null) {
            builder.and(qProductos.idSeller.id.eq(idSeller));
        }
        if (seccion == 0) {
            builder.and(qProductos.estado.eq(EstadoRegistro.ACTIVO.getId()).or(qProductos.estado.eq(EstadoRegistro.INACTIVO.getId())));
        }
        if (seccion == 1) {
            builder.and(qProductos.enoferta.startsWith("1\\|"));
        }
        if (seccion == 2) {
            builder.and(qProductos.enviogratis.eq(1));
        }
        if (seccion == 3) {
            builder.and(qProductos.stock.eq(0));
        }
        if (seccion == 4) {
            builder.and(qProductos.onlinestatus.eq(0));
        }
        if (seccion == 5) {
            builder.and(qProductos.onlinestatus.eq(1));
        }
        if (seccion == 6) {
            builder.and(qProductos.personalizable.eq(1));
        }
        if (seach != null) {
            seach = seach.trim().toUpperCase();
            String fechaFilter = Util.getFechaFilter(seach);
            builder.and(
                    qProductos.sku.upper().like("%" + seach + "%")
                            .or(qProductos.stock.like("%" + seach + "%"))
                            .or(qProductos.nombrecorto.upper().like("%" + seach + "%"))
                            .or(qProductos.preciolista.like("%" + seach + "%"))
                            .or(qProductos.onlinestatus.like("%" + seach + "%"))
                            .or(qProductos.destacado.like("%" + seach + "%"))
                            .or(qProductos.descripcion.upper().like("%" + seach + "%"))
                            //                            .or(qProductos.enoferta.upper().like("%" + seach + "%"))
                            .or(qProductos.impuesto.upper().like("%" + seach + "%"))
                            .or(qProductos.moneda.upper().like("%" + seach + "%"))
                            .or(qProductos.productonuevo.upper().like("%" + seach + "%"))
//                            .or(qProductos.skupadre.upper().like("%" + seach + "%"))
                            .or(qProductos.titulo.upper().like("%" + seach + "%"))
                            .or(qProductos.unidadmedida.upper().like("%" + seach + "%"))
                            .or(qProductos.preciobasico.like("%" + seach + "%"))
                            .or(qProductos.preciolista.like("%" + seach + "%"))
                            //                            .or(qProductos.enoferta.upper().like("%" + seach + "%"))
                            .or(qProductos.f2.like("%" + seach + "%"))
                            .or(qProductos.f7.upper().like("%" + seach + "%"))
                            .or(qProductos.f8.upper().like("%" + seach + "%"))
                            .or(qProductos.f9.upper().like("%" + seach + "%"))
                            .or(qProductos.avgstar.like("%" + seach + "%"))
                            .or(qProductos.usuRegistro.upper().like("%" + seach + "%"))
                            .or(qProductos.usuActualizacion.upper().like("%" + seach + "%"))
                            .or(qProductos.fecRegistro.stringValue().like("%" + fechaFilter + "%"))
                            .or(qProductos.fecActualizacion.stringValue().like("%" + fechaFilter + "%"))
            );
        }
        if (sortCampo != null && sortOrden != null) {
            Path<Object> fieldPath = Expressions.path(Object.class, qProductos, sortCampo);
            OrderSpecifier orderSpecifier = new OrderSpecifier(Order.valueOf(sortOrden.toUpperCase()), fieldPath);
            jPAQuery.orderBy(orderSpecifier);
        }
        jPAQuery.where(builder);
        jPAQuery.offset(pageable.getOffset());
        jPAQuery.limit(pageable.getPageSize());
        return new PageImpl(jPAQuery.fetch(), pageable, jPAQuery.fetchCount());
    }

    @Override
    public List<ProductoOptionEntity> listarProductoEntity() throws Exception {
        QProductos qProductos = QProductos.productos;
        Predicate p = qProductos.estado.eq(EstadoRegistro.ACTIVO.getId());
        return jpaQueryFactory
                .select(
                        Projections.bean(ProductoOptionEntity.class,
                                qProductos.id, qProductos.nombrecorto
                        //                                qProductos.nroSensoresmedida,
                        //                                qProductos.foto
                        )
                )
                .from(qProductos)
                .where(p)
                .fetch();
    }

    @Override
    public List<ProductoSolicitudOptionEntity> listarProductoOptionSolicitud(Integer idProducto) throws Exception {
        QProductos qProductos = QProductos.productos;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qProductos.estado.eq(EstadoRegistro.ACTIVO.getId()));
        booleanBuilder.and(qProductos.stock.gt(0));
        if (idProducto != null) {
            booleanBuilder.or(qProductos.id.eq(idProducto));
        }
        List<ProductoSolicitudOptionEntity> lista = jpaQueryFactory
                .from(qProductos)
                .select(Projections.bean(ProductoSolicitudOptionEntity.class,
                        qProductos.id,
                        qProductos.nombrecorto,
                        //                        qProductos.prcVenta,
                        qProductos.stock)
                )
                .where(booleanBuilder)
                .fetch();
        return lista;
    }

    @Override
    public List<Opcion> listarProductoOpcion(Integer idSeller) throws Exception {
        QProductos qProductos = QProductos.productos;
//        QDispositivosSerializados qDispositivosSerializados = QDispositivosSerializados.dispositivosSerializados;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qProductos.estado.eq(EstadoRegistro.ACTIVO.getId()));
//        if (idCliente != null) {
//            booleanBuilder.and(qDispositivosSerializados.idcliente.id.eq(idCliente));
//        }
        return jpaQueryFactory
                .select(Projections.bean(Opcion.class, qProductos.id, qProductos.nombrecorto))
                .distinct()
                .from(qProductos)
                //                .leftJoin(qProductos.dispositivosSerializadosSet, qDispositivosSerializados)
                .where(booleanBuilder)
                .fetch();
    }

    @Override
    public String ObtenerUltimoSku(int idSeller) throws Exception {
        QProductos qProductos = QProductos.productos;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qProductos.idSeller.id.eq(idSeller));
        String sku = jpaQueryFactory
                .select(qProductos.sku)
                .from(qProductos)
                .where(booleanBuilder)
                .orderBy(qProductos.id.desc())
                .fetchOne();
        return sku;
    }

}
