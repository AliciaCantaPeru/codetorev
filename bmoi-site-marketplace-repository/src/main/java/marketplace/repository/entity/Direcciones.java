/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Martin Pilar
 */
@Entity
@Table(name = "direcciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Direcciones.findAll", query = "SELECT d FROM Direcciones d"),
    @NamedQuery(name = "Direcciones.findById", query = "SELECT d FROM Direcciones d WHERE d.id = :id"),
    @NamedQuery(name = "Direcciones.findByDireccion", query = "SELECT d FROM Direcciones d WHERE d.direccion = :direccion"),
    @NamedQuery(name = "Direcciones.findByCalle", query = "SELECT d FROM Direcciones d WHERE d.calle = :calle"),
    @NamedQuery(name = "Direcciones.findByReferencia", query = "SELECT d FROM Direcciones d WHERE d.referencia = :referencia")})
public class Direcciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 256)
    @Column(name = "calle")
    private String calle;
    @Size(max = 256)
    @Column(name = "referencia")
    private String referencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDireccion", fetch = FetchType.LAZY)
    private Set<Pedidos> pedidosSet;
    @OneToMany(mappedBy = "iddireccion", fetch = FetchType.LAZY)
    private Set<Sellers> sellersSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDireccion", fetch = FetchType.LAZY)
    private Set<ClienteDirecciones> clienteDireccionesSet;
    @JoinColumn(name = "id_departamento", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Departamentos idDepartamento;
    @JoinColumn(name = "id_distrito", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Distritos idDistrito;
    @JoinColumn(name = "id_pais", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Paises idPais;
    @JoinColumn(name = "id_provincia", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Provincias idProvincia;

    public Direcciones() {
    }

    public Direcciones(Integer id) {
        this.id = id;
    }

    public Direcciones(Integer id, String direccion) {
        this.id = id;
        this.direccion = direccion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    @XmlTransient
    public Set<Pedidos> getPedidosSet() {
        return pedidosSet;
    }

    public void setPedidosSet(Set<Pedidos> pedidosSet) {
        this.pedidosSet = pedidosSet;
    }

    @XmlTransient
    public Set<Sellers> getSellersSet() {
        return sellersSet;
    }

    public void setSellersSet(Set<Sellers> sellersSet) {
        this.sellersSet = sellersSet;
    }

    @XmlTransient
    public Set<ClienteDirecciones> getClienteDireccionesSet() {
        return clienteDireccionesSet;
    }

    public void setClienteDireccionesSet(Set<ClienteDirecciones> clienteDireccionesSet) {
        this.clienteDireccionesSet = clienteDireccionesSet;
    }

    public Departamentos getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Departamentos idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Distritos getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(Distritos idDistrito) {
        this.idDistrito = idDistrito;
    }

    public Paises getIdPais() {
        return idPais;
    }

    public void setIdPais(Paises idPais) {
        this.idPais = idPais;
    }

    public Provincias getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Provincias idProvincia) {
        this.idProvincia = idProvincia;
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
        if (!(object instanceof Direcciones)) {
            return false;
        }
        Direcciones other = (Direcciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.Direcciones[ id=" + id + " ]";
    }
    
}
