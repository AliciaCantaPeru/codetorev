/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.customized.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import marketplace.repository.customized.DigImgdisenioSubcategoriasRepositoryCustom;
import marketplace.repository.entity.QDigImgdisenioSubcategorias;
import marketplace.repository.entity.QProductoImagenPropio;
import marketplace.repository.entity.figimgdiseniosubcategorias.GrupoCategoriaDisenioEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Martin Pilar
 */
public class DigImgdisenioSubcategoriasRepositoryImpl implements DigImgdisenioSubcategoriasRepositoryCustom {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<GrupoCategoriaDisenioEntity> listarGrupoCategoriaDisenio(int idGrupo) throws Exception {
        QDigImgdisenioSubcategorias qSubcategorias = QDigImgdisenioSubcategorias.digImgdisenioSubcategorias;
        JPAQuery<GrupoCategoriaDisenioEntity> jPAQuery = new JPAQuery<>(em);
        jPAQuery.select(Projections.bean(GrupoCategoriaDisenioEntity.class,
                qSubcategorias.id.as("idSubcategoria"),
                qSubcategorias.nombre.as("subcategoria"),
                qSubcategorias.idCategoria.id.as("idCategoria"),
                qSubcategorias.idCategoria.nombre.as("categoria"),
                qSubcategorias.idCategoria.idMenu.id.as("idGrupo"),
                qSubcategorias.idCategoria.idMenu.nombre.as("grupo")
        )).from(qSubcategorias).distinct();
        BooleanBuilder builder = new BooleanBuilder();
        if (idGrupo != -1) {
            builder.and(qSubcategorias.idCategoria.idMenu.id.eq(idGrupo));
        }
        jPAQuery.where(builder);
        return jPAQuery.fetch();
    }

    @Override
    public List<GrupoCategoriaDisenioEntity> listarGrupoCategoriaDisenioProducto(int idProducto) throws Exception {
        QDigImgdisenioSubcategorias qSubcategorias = QDigImgdisenioSubcategorias.digImgdisenioSubcategorias;
        QProductoImagenPropio qProductoImagenPropio = QProductoImagenPropio.productoImagenPropio;
        JPAQuery<GrupoCategoriaDisenioEntity> jPAQuery = new JPAQuery<>(em);
        jPAQuery.select(Projections.bean(GrupoCategoriaDisenioEntity.class,
                qSubcategorias.id.as("idSubcategoria"),
                qSubcategorias.nombre.as("subcategoria"),
                qSubcategorias.idCategoria.id.as("idCategoria"),
                qSubcategorias.idCategoria.nombre.as("categoria"),
                qSubcategorias.idCategoria.idMenu.id.as("idGrupo"),
                qSubcategorias.idCategoria.idMenu.nombre.as("grupo")
        )).from(qSubcategorias).distinct();
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qProductoImagenPropio.idProducto.id.eq(idProducto));
        jPAQuery.innerJoin(qSubcategorias.productoImagenPropioSet, qProductoImagenPropio);
        jPAQuery.where(builder);
        return jPAQuery.fetch();
    }

}
