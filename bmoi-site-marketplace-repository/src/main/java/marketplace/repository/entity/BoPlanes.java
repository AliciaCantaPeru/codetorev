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
@Table(name = "bo_planes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BoPlanes.findAll", query = "SELECT b FROM BoPlanes b"),
    @NamedQuery(name = "BoPlanes.findById", query = "SELECT b FROM BoPlanes b WHERE b.id = :id"),
    @NamedQuery(name = "BoPlanes.findByNombre", query = "SELECT b FROM BoPlanes b WHERE b.nombre = :nombre"),
    @NamedQuery(name = "BoPlanes.findByDescripcion", query = "SELECT b FROM BoPlanes b WHERE b.descripcion = :descripcion")})
public class BoPlanes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlan", fetch = FetchType.LAZY)
    private Set<SellerPlan> sellerPlanSet;

    public BoPlanes() {
    }

    public BoPlanes(Integer id) {
        this.id = id;
    }

    public BoPlanes(Integer id, String nombre, String descripcion) {
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
    public Set<SellerPlan> getSellerPlanSet() {
        return sellerPlanSet;
    }

    public void setSellerPlanSet(Set<SellerPlan> sellerPlanSet) {
        this.sellerPlanSet = sellerPlanSet;
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
        if (!(object instanceof BoPlanes)) {
            return false;
        }
        BoPlanes other = (BoPlanes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.BoPlanes[ id=" + id + " ]";
    }
    
}
