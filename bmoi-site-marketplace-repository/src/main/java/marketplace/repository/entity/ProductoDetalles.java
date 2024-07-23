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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "producto_detalles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoDetalles.findAll", query = "SELECT p FROM ProductoDetalles p"),
    @NamedQuery(name = "ProductoDetalles.findById", query = "SELECT p FROM ProductoDetalles p WHERE p.id = :id"),
    @NamedQuery(name = "ProductoDetalles.findByIdProducto", query = "SELECT p FROM ProductoDetalles p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "ProductoDetalles.findByDescripcion", query = "SELECT p FROM ProductoDetalles p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "ProductoDetalles.findByUrlimgDetalletalla", query = "SELECT p FROM ProductoDetalles p WHERE p.urlimgDetalletalla = :urlimgDetalletalla"),
    @NamedQuery(name = "ProductoDetalles.findByUrlimg1", query = "SELECT p FROM ProductoDetalles p WHERE p.urlimg1 = :urlimg1"),
    @NamedQuery(name = "ProductoDetalles.findByUrlimg2", query = "SELECT p FROM ProductoDetalles p WHERE p.urlimg2 = :urlimg2"),
    @NamedQuery(name = "ProductoDetalles.findByUrlimg3", query = "SELECT p FROM ProductoDetalles p WHERE p.urlimg3 = :urlimg3")})
public class ProductoDetalles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_producto")
    private int idProducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 250)
    @Column(name = "urlimg_detalletalla")
    private String urlimgDetalletalla;
    @Size(max = 250)
    @Column(name = "urlimg1")
    private String urlimg1;
    @Size(max = 250)
    @Column(name = "urlimg2")
    private String urlimg2;
    @Size(max = 250)
    @Column(name = "urlimg3")
    private String urlimg3;

    public ProductoDetalles() {
    }

    public ProductoDetalles(Integer id) {
        this.id = id;
    }

    public ProductoDetalles(Integer id, int idProducto, String descripcion) {
        this.id = id;
        this.idProducto = idProducto;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlimgDetalletalla() {
        return urlimgDetalletalla;
    }

    public void setUrlimgDetalletalla(String urlimgDetalletalla) {
        this.urlimgDetalletalla = urlimgDetalletalla;
    }

    public String getUrlimg1() {
        return urlimg1;
    }

    public void setUrlimg1(String urlimg1) {
        this.urlimg1 = urlimg1;
    }

    public String getUrlimg2() {
        return urlimg2;
    }

    public void setUrlimg2(String urlimg2) {
        this.urlimg2 = urlimg2;
    }

    public String getUrlimg3() {
        return urlimg3;
    }

    public void setUrlimg3(String urlimg3) {
        this.urlimg3 = urlimg3;
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
        if (!(object instanceof ProductoDetalles)) {
            return false;
        }
        ProductoDetalles other = (ProductoDetalles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.ProductoDetalles[ id=" + id + " ]";
    }
    
}
