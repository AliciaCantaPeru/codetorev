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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Martin Pilar
 */
@Entity
@Table(name = "producto_imagen_propio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoImagenPropio.findAll", query = "SELECT p FROM ProductoImagenPropio p"),
    @NamedQuery(name = "ProductoImagenPropio.findById", query = "SELECT p FROM ProductoImagenPropio p WHERE p.id = :id"),
    @NamedQuery(name = "ProductoImagenPropio.findByUrl", query = "SELECT p FROM ProductoImagenPropio p WHERE p.url = :url"),
    @NamedQuery(name = "ProductoImagenPropio.findByEstado", query = "SELECT p FROM ProductoImagenPropio p WHERE p.estado = :estado")})
public class ProductoImagenPropio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @JoinColumn(name = "id_dig_img_sub_img", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DigImgdisenioSubcategorias idDigImgSubImg;
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Productos idProducto;

    public ProductoImagenPropio() {
    }

    public ProductoImagenPropio(Integer id) {
        this.id = id;
    }

    public ProductoImagenPropio(Integer id, String url, boolean estado) {
        this.id = id;
        this.url = url;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public DigImgdisenioSubcategorias getIdDigImgSubImg() {
        return idDigImgSubImg;
    }

    public void setIdDigImgSubImg(DigImgdisenioSubcategorias idDigImgSubImg) {
        this.idDigImgSubImg = idDigImgSubImg;
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
        if (!(object instanceof ProductoImagenPropio)) {
            return false;
        }
        ProductoImagenPropio other = (ProductoImagenPropio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.ProductoImagenPropio[ id=" + id + " ]";
    }
    
}
