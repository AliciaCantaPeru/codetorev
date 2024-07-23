/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Martin Pilar
 */
@Entity
@Table(name = "cliente_direcciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteDirecciones.findAll", query = "SELECT c FROM ClienteDirecciones c"),
    @NamedQuery(name = "ClienteDirecciones.findById", query = "SELECT c FROM ClienteDirecciones c WHERE c.id = :id"),
    @NamedQuery(name = "ClienteDirecciones.findByPrincipal", query = "SELECT c FROM ClienteDirecciones c WHERE c.principal = :principal"),
    @NamedQuery(name = "ClienteDirecciones.findByEstado", query = "SELECT c FROM ClienteDirecciones c WHERE c.estado = :estado")})
public class ClienteDirecciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "principal")
    private Integer principal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Clientes idCliente;
    @JoinColumn(name = "id_direccion", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Direcciones idDireccion;

    public ClienteDirecciones() {
    }

    public ClienteDirecciones(Integer id) {
        this.id = id;
    }

    public ClienteDirecciones(Integer id, int estado) {
        this.id = id;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrincipal() {
        return principal;
    }

    public void setPrincipal(Integer principal) {
        this.principal = principal;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }

    public Direcciones getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Direcciones idDireccion) {
        this.idDireccion = idDireccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClienteDirecciones)) {
            return false;
        }
        ClienteDirecciones other = (ClienteDirecciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.ClienteDirecciones[ id=" + id + " ]";
    }
    
}
