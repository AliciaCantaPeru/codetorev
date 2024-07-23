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
@Table(name = "producto_tallas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoTallas.findAll", query = "SELECT p FROM ProductoTallas p"),
    @NamedQuery(name = "ProductoTallas.findById", query = "SELECT p FROM ProductoTallas p WHERE p.id = :id"),
    @NamedQuery(name = "ProductoTallas.findByCodigo", query = "SELECT p FROM ProductoTallas p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "ProductoTallas.findByNombre", query = "SELECT p FROM ProductoTallas p WHERE p.nombre = :nombre")})
public class ProductoTallas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "idTalla", fetch = FetchType.LAZY)
    private Set<ProductoVariantes> productoVariantesSet;

    public ProductoTallas() {
    }

    public ProductoTallas(Integer id) {
        this.id = id;
    }

    public ProductoTallas(Integer id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Set<ProductoVariantes> getProductoVariantesSet() {
        return productoVariantesSet;
    }

    public void setProductoVariantesSet(Set<ProductoVariantes> productoVariantesSet) {
        this.productoVariantesSet = productoVariantesSet;
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
        if (!(object instanceof ProductoTallas)) {
            return false;
        }
        ProductoTallas other = (ProductoTallas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.ProductoTallas[ id=" + id + " ]";
    }
    
}
