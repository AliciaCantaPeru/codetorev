/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
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
@Table(name = "producto_brands")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoBrands.findAll", query = "SELECT p FROM ProductoBrands p"),
    @NamedQuery(name = "ProductoBrands.findById", query = "SELECT p FROM ProductoBrands p WHERE p.id = :id"),
    @NamedQuery(name = "ProductoBrands.findByNombre", query = "SELECT p FROM ProductoBrands p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "ProductoBrands.findByDescripcion", query = "SELECT p FROM ProductoBrands p WHERE p.descripcion = :descripcion")})
public class ProductoBrands implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idBrand", fetch = FetchType.LAZY)
    private Set<Productos> productosSet;

    public ProductoBrands() {
    }

    public ProductoBrands(Integer id) {
        this.id = id;
    }

    public ProductoBrands(Integer id, String nombre, String descripcion) {
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
    public Set<Productos> getProductosSet() {
        return productosSet;
    }

    public void setProductosSet(Set<Productos> productosSet) {
        this.productosSet = productosSet;
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
        if (!(object instanceof ProductoBrands)) {
            return false;
        }
        ProductoBrands other = (ProductoBrands) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.ProductoBrands[ id=" + id + " ]";
    }
    
}
