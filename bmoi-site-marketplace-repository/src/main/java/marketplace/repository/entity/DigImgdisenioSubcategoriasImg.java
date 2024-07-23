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
@Table(name = "dig_imgdisenio_subcategorias_img")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DigImgdisenioSubcategoriasImg.findAll", query = "SELECT d FROM DigImgdisenioSubcategoriasImg d"),
    @NamedQuery(name = "DigImgdisenioSubcategoriasImg.findById", query = "SELECT d FROM DigImgdisenioSubcategoriasImg d WHERE d.id = :id"),
    @NamedQuery(name = "DigImgdisenioSubcategoriasImg.findByUrl", query = "SELECT d FROM DigImgdisenioSubcategoriasImg d WHERE d.url = :url")})
public class DigImgdisenioSubcategoriasImg implements Serializable {

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
    @JoinColumn(name = "id_dig_img_sub", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DigImgdisenioSubcategorias idDigImgSub;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDigImgSubImg", fetch = FetchType.LAZY)
    private Set<ProductoImagen> productoImagenSet;

    public DigImgdisenioSubcategoriasImg() {
    }

    public DigImgdisenioSubcategoriasImg(Integer id) {
        this.id = id;
    }

    public DigImgdisenioSubcategoriasImg(Integer id, String url) {
        this.id = id;
        this.url = url;
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

    public DigImgdisenioSubcategorias getIdDigImgSub() {
        return idDigImgSub;
    }

    public void setIdDigImgSub(DigImgdisenioSubcategorias idDigImgSub) {
        this.idDigImgSub = idDigImgSub;
    }

    @XmlTransient
    public Set<ProductoImagen> getProductoImagenSet() {
        return productoImagenSet;
    }

    public void setProductoImagenSet(Set<ProductoImagen> productoImagenSet) {
        this.productoImagenSet = productoImagenSet;
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
        if (!(object instanceof DigImgdisenioSubcategoriasImg)) {
            return false;
        }
        DigImgdisenioSubcategoriasImg other = (DigImgdisenioSubcategoriasImg) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.DigImgdisenioSubcategoriasImg[ id=" + id + " ]";
    }
    
}
