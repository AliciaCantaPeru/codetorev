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
import marketplace.repository.customized.ClientesRepositoryCustom;
import marketplace.repository.entity.Clientes;
import marketplace.repository.entity.QClientes;
import marketplace.repository.entity.QPersonas;
import marketplace.repository.entity.cliente.ClienteOpcionSolicitudEntity;
import marketplace.support.dto.Opcion;
import marketplace.util.EstadoRegistro;
import marketplace.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public class ClientesRepositoryCustomImpl implements ClientesRepositoryCustom {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager em;

    @Override
    public PageImpl<Clientes> listarCliente(String sortCampo, String sortOrden, String seach, PageRequest pageable) throws Exception {
        QClientes qClientes = QClientes.clientes;
        JPAQuery<Clientes> jPAQuery = new JPAQuery<>(em);
        jPAQuery.select(qClientes)
                .from(qClientes);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qClientes.estado.eq(EstadoRegistro.ACTIVO.getId()));
        if (seach != null) {
            seach = seach.trim().toUpperCase();
            String fechaFilter = Util.getFechaFilter(seach);
            builder.and(qClientes.razSocial.upper().like("%" + seach + "%")
                    .or(qClientes.rubro.upper().like("%" + seach + "%"))
                    .or(qClientes.ruc.upper().like("%" + seach + "%"))
                    .or(qClientes.nombre.upper().like("%" + seach + "%"))
                    .or(qClientes.tipEmpresa.upper().like("%" + seach + "%"))
                    .or(qClientes.email.upper().like("%" + seach + "%"))
                    .or(qClientes.telefono.upper().like("%" + seach + "%"))
                    .or(qClientes.web.upper().like("%" + seach + "%"))
                    .or(qClientes.usuRegistro.upper().like("%" + seach + "%"))
                    .or(qClientes.usuActualizacion.upper().like("%" + seach + "%"))
                    .or(qClientes.fecRegistro.stringValue().like("%" + fechaFilter + "%"))
                    .or(qClientes.fecActualizacion.stringValue().like("%" + fechaFilter + "%"))
            );
        }
        if (sortCampo != null && sortOrden != null) {
            Path<Object> fieldPath = Expressions.path(Object.class, qClientes, sortCampo);
            OrderSpecifier orderSpecifier = new OrderSpecifier(Order.valueOf(sortOrden.toUpperCase()), fieldPath);
            jPAQuery.orderBy(orderSpecifier);
        }
        jPAQuery.where(builder);
        jPAQuery.offset(pageable.getOffset());
        jPAQuery.limit(pageable.getPageSize());
        return new PageImpl(jPAQuery.fetch(), pageable, jPAQuery.fetchCount());
    }

    @Override
    public Integer obtenerIdCliente(int idPersona) throws Exception {
        QPersonas qPersonas = QPersonas.personas;
        Predicate p = qPersonas.id.eq(idPersona);
        Integer idCliente = jpaQueryFactory
                .from(qPersonas)
                .select(qPersonas.id)
                .where(p).fetchOne();
        return idCliente;
    }

    @Override
    public List<Opcion> listarOpcionCliente(int idCliente) throws Exception {
        QClientes qClientes = QClientes.clientes;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qClientes.estado.eq(EstadoRegistro.ACTIVO.getId()));
        if (idCliente != -1) {
            booleanBuilder.or(qClientes.id.eq(idCliente));
        }
        List<Opcion> lista = jpaQueryFactory
                .from(qClientes)
                .select(Projections.bean(Opcion.class, qClientes.id, qClientes.razSocial.as("nombre")))
                .where(booleanBuilder).fetch();
        return lista;
    }

    @Override
    public List<ClienteOpcionSolicitudEntity> listarOpcionClienteSolicitud() throws Exception {
        QClientes qClientes = QClientes.clientes;
        QPersonas qPersonas = QPersonas.personas;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qClientes.estado.eq(EstadoRegistro.ACTIVO.getId()));
//        booleanBuilder.and(qPersonas.cntPrincipal.eq(1));
        List<ClienteOpcionSolicitudEntity> lista = jpaQueryFactory
                .from(qClientes)
                .select(Projections.bean(ClienteOpcionSolicitudEntity.class,
                        qClientes.id,
                        qClientes.razSocial.as("nombre"),
                        qPersonas.nombres.concat(" ").concat(qPersonas.priApellido).concat(" ").concat(qPersonas.segApellido).as("nombrePersonaPrincipal")
                ))
//                .leftJoin(qClientes.personasSet, qPersonas)
                .distinct()
                .where(booleanBuilder).fetch();
        return lista;
    }
}
