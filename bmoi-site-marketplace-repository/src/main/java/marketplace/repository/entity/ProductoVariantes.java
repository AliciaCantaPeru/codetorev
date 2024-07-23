/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Martin Pilar
 */
@Entity
@Table(name = "producto_variantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoVariantes.findAll", query = "SELECT p FROM ProductoVariantes p"),
    @NamedQuery(name = "ProductoVariantes.findById", query = "SELECT p FROM ProductoVariantes p WHERE p.id = :id"),
    @NamedQuery(name = "ProductoVariantes.findByStock", query = "SELECT p FROM ProductoVariantes p WHERE p.stock = :stock"),
    @NamedQuery(name = "ProductoVariantes.findByPreciolista", query = "SELECT p FROM ProductoVariantes p WHERE p.preciolista = :preciolista"),
    @NamedQuery(name = "ProductoVariantes.findBySkuvariante", query = "SELECT p FROM ProductoVariantes p WHERE p.skuvariante = :skuvariante")})
public class ProductoVariantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stock")
    private int stock;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "preciolista")
    private BigDecimal preciolista;
    @Size(max = 50)
    @Column(name = "skuvariante")
    private String skuvariante;
    @JoinColumn(name = "id_color", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductoColores idColor;
    @JoinColumn(name = "id_talla", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductoTallas idTalla;
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Productos idProducto;

    public ProductoVariantes() {
    }

    public ProductoVariantes(Integer id) {
        this.id = id;
    }

    public ProductoVariantes(Integer id, int stock, BigDecimal preciolista) {
        this.id = id;
        this.stock = stock;
        this.preciolista = preciolista;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPreciolista() {
        return preciolista;
    }

    public void setPreciolista(BigDecimal preciolista) {
        this.preciolista = preciolista;
    }

    public String getSkuvariante() {
        return skuvariante;
    }

    public void setSkuvariante(String skuvariante) {
        this.skuvariante = skuvariante;
    }

    public ProductoColores getIdColor() {
        return idColor;
    }

    public void setIdColor(ProductoColores idColor) {
        this.idColor = idColor;
    }

    public ProductoTallas getIdTalla() {
        return idTalla;
    }

    public void setIdTalla(ProductoTallas idTalla) {
        this.idTalla = idTalla;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
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
        if (!(object instanceof ProductoVariantes)) {
            return false;
        }
        ProductoVariantes other = (ProductoVariantes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.ProductoVariantes[ id=" + id + " ]";
    }
    
}
