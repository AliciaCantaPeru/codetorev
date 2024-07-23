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
@Table(name = "producto_colores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoColores.findAll", query = "SELECT p FROM ProductoColores p"),
    @NamedQuery(name = "ProductoColores.findById", query = "SELECT p FROM ProductoColores p WHERE p.id = :id"),
    @NamedQuery(name = "ProductoColores.findByNombre", query = "SELECT p FROM ProductoColores p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "ProductoColores.findByCodigo", query = "SELECT p FROM ProductoColores p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "ProductoColores.findByValorhexadecimal", query = "SELECT p FROM ProductoColores p WHERE p.valorhexadecimal = :valorhexadecimal")})
public class ProductoColores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 7)
    @Column(name = "valorhexadecimal")
    private String valorhexadecimal;
    @OneToMany(mappedBy = "idColor", fetch = FetchType.LAZY)
    private Set<ProductoVariantes> productoVariantesSet;

    public ProductoColores() {
    }

    public ProductoColores(Integer id) {
        this.id = id;
    }

    public ProductoColores(Integer id, String nombre, String codigo) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getValorhexadecimal() {
        return valorhexadecimal;
    }

    public void setValorhexadecimal(String valorhexadecimal) {
        this.valorhexadecimal = valorhexadecimal;
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
        if (!(object instanceof ProductoColores)) {
            return false;
        }
        ProductoColores other = (ProductoColores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.ProductoColores[ id=" + id + " ]";
    }
    
}
