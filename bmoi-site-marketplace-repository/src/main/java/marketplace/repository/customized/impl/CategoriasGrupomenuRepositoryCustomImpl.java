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
import marketplace.repository.customized.CategoriasGrupomenuRepositoryCustom;
import marketplace.repository.entity.QCategoriasGrupomenu;
import marketplace.repository.entity.categoriasgrupomenu.CategoriasGrupomenuEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Martin Pilar
 */
public class CategoriasGrupomenuRepositoryCustomImpl implements CategoriasGrupomenuRepositoryCustom {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<CategoriasGrupomenuEntity> listarGrupoCategoria(String codigoMenu) throws Exception {
        QCategoriasGrupomenu qCategoriasGrupomenu = QCategoriasGrupomenu.categoriasGrupomenu;
        JPAQuery<CategoriasGrupomenuEntity> jPAQuery = new JPAQuery<>(em);
        jPAQuery.select(Projections.bean(CategoriasGrupomenuEntity.class,
                qCategoriasGrupomenu.id.as("idCategoriaGrupoMenu"),
                qCategoriasGrupomenu.idCategoria.id.as("idCategoria"),
                qCategoriasGrupomenu.idCategoria.codigo.as("codigoCategoria"),
                qCategoriasGrupomenu.idCategoria.nombre.as("categoria"),
                qCategoriasGrupomenu.idGrupomenu.idGrupo.id.as("idGrupo"),
                qCategoriasGrupomenu.idGrupomenu.idGrupo.nombre.as("grupo"),
                qCategoriasGrupomenu.idGrupomenu.codMenu.codigo.as("codigoMenu")
        )).from(qCategoriasGrupomenu);
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qCategoriasGrupomenu.idGrupomenu.codMenu.codigo.eq(codigoMenu));
        jPAQuery.where(builder);
        return jPAQuery.fetch();

    }

}
