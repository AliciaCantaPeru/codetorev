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
import marketplace.logger.ExceptionUser;
import marketplace.repository.customized.SellerCuentasRepositoryCustom;
import marketplace.repository.entity.QRoles;
import marketplace.repository.entity.QSellerCuentas;
import marketplace.repository.entity.QSellerPersonas;
import marketplace.repository.entity.QSellers;
import marketplace.repository.entity.cuentas.PersonaCuentaEntity;
import marketplace.repository.entity.cuentas.UsuarioEntity;
import marketplace.util.EstadoRegistro;
import marketplace.util.TipoUsuarioLogin;
import org.springframework.beans.factory.annotation.Autowired;

public class SellerCuentasRepositoryCustomImpl implements SellerCuentasRepositoryCustom {

    @Autowired
    private JPAQueryFactory factory;

    @Override
    public UsuarioEntity obtenerUsuario(String email, String tipoUsuario) throws Exception {
        QSellerCuentas qCuentas = QSellerCuentas.sellerCuentas;
        QRoles qRoles = QRoles.roles;
        QSellers qSellers = QSellers.sellers;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qCuentas.estado.eq(EstadoRegistro.ACTIVO.getId()));
        builder.and(qCuentas.idSellerpersona.email.eq(email));
        builder.and(qCuentas.idSellerpersona.estado.eq(EstadoRegistro.ACTIVO.getId()));
        builder.and(
                qCuentas.idSellerpersona.idseller.estado.eq(EstadoRegistro.ACTIVO.getId())
        );
        if (tipoUsuario.equals(TipoUsuarioLogin.SELLER.getValor())) {
            builder.and(qSellers.id.ne(1));
        }
        if (tipoUsuario.equals(TipoUsuarioLogin.BMOI.getValor())) {
            builder.and(qSellers.id.eq(1));
        }
        UsuarioEntity entity = factory.from(qCuentas)
                .select(Projections.bean(UsuarioEntity.class, qCuentas.idSellerpersona.id.as("idPersona"), qCuentas.idSellerpersona.email, qCuentas.contrasenia, qCuentas.idRol.nombre.as("rol")))
                .innerJoin(qCuentas.idRol, qRoles)
                .leftJoin(qCuentas.idSellerpersona.idseller, qSellers)
                .where(builder).fetchOne();
        return entity;
    }

    @Override
    public String validaObtenerCorreo(int idUsuarioLogeado, String tipoUsuario) throws Exception {
        QSellerCuentas qCuentas = QSellerCuentas.sellerCuentas;
        QRoles qRoles = QRoles.roles;
        QSellers qSeller = QSellers.sellers;
        QSellerPersonas qSellerPersonas = QSellerPersonas.sellerPersonas;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qCuentas.estado.eq(EstadoRegistro.ACTIVO.getId()));
        builder.and(qSellerPersonas.id.eq(idUsuarioLogeado));
        builder.and(qSeller.estado.eq(EstadoRegistro.ACTIVO.getId()));
        if (tipoUsuario.equals(TipoUsuarioLogin.SELLER.getValor())) {
            builder.and(qSeller.id.ne(1));
        }
        if (tipoUsuario.equals(TipoUsuarioLogin.BMOI.getValor())) {
            builder.and(qSeller.id.eq(1));
        }
        String correo = factory.from(qCuentas)
                .select(qSellerPersonas.email)
                .innerJoin(qCuentas.idRol, qRoles)
                .leftJoin(qCuentas.idSellerpersona, qSellerPersonas)
                .leftJoin(qSellerPersonas.idseller, qSeller)
                .where(builder).fetchOne();
        if (correo == null) {
            throw new ExceptionUser("El cuenta esta desabilitada o no se encuentra registrado", "no encontrado usuario id " + idUsuarioLogeado);
        }
        return correo;
    }
    
 @Override
    public String validaObtenerCorreo(int idUsuarioLogeado) throws Exception {
        QSellerCuentas qCuentas = QSellerCuentas.sellerCuentas;
        QRoles qRoles = QRoles.roles;
        QSellers qSeller = QSellers.sellers;
        QSellerPersonas qSellerPersonas = QSellerPersonas.sellerPersonas;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qCuentas.estado.eq(EstadoRegistro.ACTIVO.getId()));
        builder.and(qSellerPersonas.id.eq(idUsuarioLogeado));
        builder.and(qSeller.estado.eq(EstadoRegistro.ACTIVO.getId()));
        String correo = factory.from(qCuentas)
                .select(qSellerPersonas.email)
                .innerJoin(qCuentas.idRol, qRoles)
                .leftJoin(qCuentas.idSellerpersona, qSellerPersonas)
                .leftJoin(qSellerPersonas.idseller, qSeller)
                .where(builder).fetchOne();
        if (correo == null) {
            throw new ExceptionUser("El cuenta esta desabilitada o no se encuentra registrado", "no encontrado usuario id " + idUsuarioLogeado);
        }
        return correo;
    }
    
    @Override
    public PersonaCuentaEntity existeCuenta(String email, String tipoUsuario) throws Exception {
        QSellerCuentas qCuentas = QSellerCuentas.sellerCuentas;
        QRoles qRoles = QRoles.roles;
        QSellers qSellers = QSellers.sellers;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qCuentas.estado.eq(EstadoRegistro.ACTIVO.getId()));
        builder.and(qCuentas.idSellerpersona.email.eq(email));
        builder.and(qCuentas.idSellerpersona.estado.eq(EstadoRegistro.ACTIVO.getId()));
        builder.and(
                qSellers.estado.eq(EstadoRegistro.ACTIVO.getId())
        );
        if (tipoUsuario.equals(TipoUsuarioLogin.SELLER.getValor())) {
            builder.and(qSellers.id.ne(1));
        }
        if (tipoUsuario.equals(TipoUsuarioLogin.BMOI.getValor())) {
            builder.and(qSellers.id.eq(1));
        }
        PersonaCuentaEntity persona = factory.from(qCuentas)
                .select(
                        Projections.bean(PersonaCuentaEntity.class,
                                qCuentas.idSellerpersona.id,
                                qCuentas.idSellerpersona.priNombre.concat(" ")
                                        .concat(qCuentas.idSellerpersona.segNombre).concat(" ")
                                        .concat(qCuentas.idSellerpersona.priApellido).concat(" ")
                                        .concat(qCuentas.idSellerpersona.segApellido).as("nombres")))
                .innerJoin(qCuentas.idRol, qRoles)
                .leftJoin(qCuentas.idSellerpersona.idseller, qSellers)
                .where(builder).fetchOne();
        return persona;
    }

    @Override
    public boolean existeKey(String email, String keyNewPassword) throws Exception {
        QSellerCuentas qCuentas = QSellerCuentas.sellerCuentas;
        Predicate p = qCuentas.idSellerpersona.email.eq(email)
                .and(qCuentas.keyNewContrasenia.eq(keyNewPassword));
        Long count = factory.from(qCuentas)
                .select(qCuentas.count())
                .where(p).fetchOne();
        return count == 1;
    }

    @Override
    public void updateKeyNewPassword(int idPersona, String keyNewPassword) throws Exception {
        QSellerCuentas qCuentas = QSellerCuentas.sellerCuentas;
        Predicate p = qCuentas.idSellerpersona.id.eq(idPersona);
        factory.update(qCuentas)
                .set(qCuentas.keyNewContrasenia, keyNewPassword)
                .where(p).execute();
    }

}
