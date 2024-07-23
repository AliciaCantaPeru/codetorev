/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "delivery_metodos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeliveryMetodos.findAll", query = "SELECT d FROM DeliveryMetodos d"),
    @NamedQuery(name = "DeliveryMetodos.findById", query = "SELECT d FROM DeliveryMetodos d WHERE d.id = :id"),
    @NamedQuery(name = "DeliveryMetodos.findByNombre", query = "SELECT d FROM DeliveryMetodos d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DeliveryMetodos.findByTiempo", query = "SELECT d FROM DeliveryMetodos d WHERE d.tiempo = :tiempo"),
    @NamedQuery(name = "DeliveryMetodos.findByDescripcion", query = "SELECT d FROM DeliveryMetodos d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "DeliveryMetodos.findByPrecio", query = "SELECT d FROM DeliveryMetodos d WHERE d.precio = :precio"),
    @NamedQuery(name = "DeliveryMetodos.findByEstado", query = "SELECT d FROM DeliveryMetodos d WHERE d.estado = :estado")})
public class DeliveryMetodos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "tiempo")
    private String tiempo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private BigDecimal precio;
    @Column(name = "estado")
    private Integer estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDeliveryMetodo", fetch = FetchType.LAZY)
    private Set<Pedidos> pedidosSet;

    public DeliveryMetodos() {
    }

    public DeliveryMetodos(Integer id) {
        this.id = id;
    }

    public DeliveryMetodos(Integer id, String nombre, String tiempo, String descripcion, BigDecimal precio) {
        this.id = id;
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Set<Pedidos> getPedidosSet() {
        return pedidosSet;
    }

    public void setPedidosSet(Set<Pedidos> pedidosSet) {
        this.pedidosSet = pedidosSet;
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
        if (!(object instanceof DeliveryMetodos)) {
            return false;
        }
        DeliveryMetodos other = (DeliveryMetodos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.DeliveryMetodos[ id=" + id + " ]";
    }
    
}
