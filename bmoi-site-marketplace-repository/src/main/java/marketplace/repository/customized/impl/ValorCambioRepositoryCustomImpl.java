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
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import marketplace.repository.customized.ValorCambioRepositoryCustom;
import marketplace.repository.entity.QValorCambio;
import marketplace.repository.entity.ValorCambio;
import marketplace.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author Martin Pilar mpilarcastillejo@gmail.com
 */
public class ValorCambioRepositoryCustomImpl implements ValorCambioRepositoryCustom {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager em;

    @Override
    public PageImpl<ValorCambio> listaValorCambio(String sortCampo, String sortOrden, String seach, PageRequest pageable) throws Exception {
        QValorCambio qValorCambio = QValorCambio.valorCambio;
        JPAQuery<ValorCambio> jPAQuery = new JPAQuery<>(em);
        jPAQuery.select(qValorCambio)
                .from(qValorCambio);
        BooleanBuilder builder = new BooleanBuilder();
        if (seach != null) {
            seach = seach.trim().toUpperCase();
            String fechaFilter = Util.getFechaFilter(seach);
            builder.and(qValorCambio.moneda.upper().like("%" + seach + "%")
                    .or(qValorCambio.valCompra.like("%" + seach + "%"))
                    .or(qValorCambio.valVenta.like("%" + seach + "%"))
                    .or(qValorCambio.fecha.stringValue().like("%" + fechaFilter + "%"))
            );
        }
        if (sortCampo != null && sortOrden != null) {
            Path<Object> fieldPath = Expressions.path(Object.class, qValorCambio, sortCampo);
            OrderSpecifier orderSpecifier = new OrderSpecifier(Order.valueOf(sortOrden.toUpperCase()), fieldPath);
            jPAQuery.orderBy(orderSpecifier);
        }
        jPAQuery.where(builder);
        jPAQuery.offset(pageable.getOffset());
        jPAQuery.limit(pageable.getPageSize());
        return new PageImpl(jPAQuery.fetch(), pageable, jPAQuery.fetchCount());
    }

    @Override
    public BigDecimal obtenerValorCambio(LocalDate fecha, String moneda) throws Exception {
        QValorCambio qValorCambio = QValorCambio.valorCambio;
        QValorCambio qCambio = new QValorCambio("qCambio");
        Predicate p = qValorCambio.fecha.month().eq(fecha.getMonthValue())
                .and(qValorCambio.fecha.year().eq(fecha.getYear()))
                .and(qValorCambio.fecha.dayOfMonth().eq(fecha.getDayOfMonth()))
                .and(qValorCambio.moneda.eq(moneda))
                .and(qValorCambio.id
                        .eq(JPAExpressions.select(qCambio.id.max())
                                .from(qCambio))
                );
        BigDecimal valorCambio = jpaQueryFactory
                .select(qValorCambio.valVenta)
                .from(qValorCambio)
                .where(p)
                .fetchOne();
        return valorCambio;
    }

}
