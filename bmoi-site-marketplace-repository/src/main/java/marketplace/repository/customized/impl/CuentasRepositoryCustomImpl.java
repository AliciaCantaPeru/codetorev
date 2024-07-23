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
import marketplace.repository.customized.CuentasRepositoryCustom;
import marketplace.repository.entity.QCuentas;
import marketplace.repository.entity.QPersonas;
import marketplace.repository.entity.QRoles;
import marketplace.repository.entity.cuentas.PersonaCuentaEntity;
import marketplace.repository.entity.cuentas.UsuarioEntity;
import marketplace.util.EstadoRegistro;
import org.springframework.beans.factory.annotation.Autowired;

public class CuentasRepositoryCustomImpl implements CuentasRepositoryCustom {

    @Autowired
    private JPAQueryFactory factory;

    @Override
    public UsuarioEntity obtenerUsuario(String email) throws Exception {
        QCuentas qCuentas = QCuentas.cuentas;
        QRoles qRoles = QRoles.roles;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qCuentas.estado.eq(EstadoRegistro.ACTIVO.getId()));
        builder.and(qCuentas.idPersona.email.eq(email));
        UsuarioEntity entity = factory.from(qCuentas)
                .select(Projections.bean(UsuarioEntity.class, qCuentas.idPersona.id.as("idPersona"), qCuentas.idPersona.email, qCuentas.contrasenia, qCuentas.idRol.nombre.as("rol")))
                .innerJoin(qCuentas.idRol, qRoles)
                .where(builder).fetchOne();
        return entity;
    }

    @Override
    public String validaObtenerCorreo(int idUsuarioLogeado) throws Exception {
        QCuentas qCuentas = QCuentas.cuentas;
        QRoles qRoles = QRoles.roles;
        QPersonas qPersonas = QPersonas.personas;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qCuentas.estado.eq(EstadoRegistro.ACTIVO.getId()));
        builder.and(qCuentas.idPersona.id.eq(idUsuarioLogeado));
        String correo = factory.from(qCuentas)
                .select(qPersonas.email)
                .innerJoin(qCuentas.idRol, qRoles)
                .leftJoin(qCuentas.idPersona, qPersonas)
                .where(builder).fetchOne();
        if (correo == null) {
            throw new ExceptionUser("El cuenta esta desabilitada o no se encuentra registrado", "no encontrado usuario id " + idUsuarioLogeado);
        }
        return correo;
    }

    @Override
    public PersonaCuentaEntity existeCuenta(String email) throws Exception {
        QCuentas qCuentas = QCuentas.cuentas;
        QRoles qRoles = QRoles.roles;
//        QClientes qClientes = QClientes.clientes;
        QPersonas qPersonas = QPersonas.personas;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qCuentas.estado.eq(EstadoRegistro.ACTIVO.getId()));
        builder.and(qCuentas.idPersona.email.eq(email));
//        builder.and(qClientes.estado.eq(EstadoRegistro.ACTIVO.getId()));
        PersonaCuentaEntity persona = factory.from(qCuentas)
                .select(
                        Projections.bean(PersonaCuentaEntity.class,
                                qCuentas.idPersona.id,
                                qCuentas.idPersona.nombres.concat(" ").concat(qCuentas.idPersona.priApellido).concat(" ").concat(qCuentas.idPersona.segApellido).as("nombres")))
                .innerJoin(qCuentas.idRol, qRoles)
                .leftJoin(qCuentas.idPersona, qPersonas)
//                .leftJoin(qPersonas.clientesSet, qClientes)
                .where(builder).fetchOne();
        return persona;
    }

    @Override
    public boolean existeKey(String email, String keyNewPassword) throws Exception {
        QCuentas qCuentas = QCuentas.cuentas;
        Predicate p = qCuentas.idPersona.email.eq(email)
                .and(qCuentas.keyNewContrasenia.eq(keyNewPassword));
        Long count = factory.from(qCuentas)
                .select(qCuentas.count())
                .where(p).fetchOne();
        return count == 1;
    }

    @Override
    public void updateKeyNewPassword(int idPersona, String keyNewPassword) throws Exception {
        QCuentas qCuentas = QCuentas.cuentas;
        Predicate p = qCuentas.idPersona.id.eq(idPersona);
        factory.update(qCuentas)
                .set(qCuentas.keyNewContrasenia, keyNewPassword)
                .where(p).execute();
    }

}
