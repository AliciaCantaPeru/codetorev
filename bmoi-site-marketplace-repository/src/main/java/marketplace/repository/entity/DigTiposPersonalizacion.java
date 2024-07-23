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
@Table(name = "dig_tipos_personalizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DigTiposPersonalizacion.findAll", query = "SELECT d FROM DigTiposPersonalizacion d"),
    @NamedQuery(name = "DigTiposPersonalizacion.findById", query = "SELECT d FROM DigTiposPersonalizacion d WHERE d.id = :id"),
    @NamedQuery(name = "DigTiposPersonalizacion.findByNombre", query = "SELECT d FROM DigTiposPersonalizacion d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DigTiposPersonalizacion.findByDescripcion", query = "SELECT d FROM DigTiposPersonalizacion d WHERE d.descripcion = :descripcion")})
public class DigTiposPersonalizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDigtipopersonalizacion", fetch = FetchType.LAZY)
    private Set<DigProductoTipopersonalizacion> digProductoTipopersonalizacionSet;

    public DigTiposPersonalizacion() {
    }

    public DigTiposPersonalizacion(Integer id) {
        this.id = id;
    }

    public DigTiposPersonalizacion(Integer id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Set<DigProductoTipopersonalizacion> getDigProductoTipopersonalizacionSet() {
        return digProductoTipopersonalizacionSet;
    }

    public void setDigProductoTipopersonalizacionSet(Set<DigProductoTipopersonalizacion> digProductoTipopersonalizacionSet) {
        this.digProductoTipopersonalizacionSet = digProductoTipopersonalizacionSet;
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
        if (!(object instanceof DigTiposPersonalizacion)) {
            return false;
        }
        DigTiposPersonalizacion other = (DigTiposPersonalizacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.DigTiposPersonalizacion[ id=" + id + " ]";
    }
    
}
