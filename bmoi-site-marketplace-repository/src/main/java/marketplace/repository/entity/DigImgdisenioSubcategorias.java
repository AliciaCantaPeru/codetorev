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
@Table(name = "dig_imgdisenio_subcategorias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DigImgdisenioSubcategorias.findAll", query = "SELECT d FROM DigImgdisenioSubcategorias d"),
    @NamedQuery(name = "DigImgdisenioSubcategorias.findById", query = "SELECT d FROM DigImgdisenioSubcategorias d WHERE d.id = :id"),
    @NamedQuery(name = "DigImgdisenioSubcategorias.findByNombre", query = "SELECT d FROM DigImgdisenioSubcategorias d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DigImgdisenioSubcategorias.findByDescripcion", query = "SELECT d FROM DigImgdisenioSubcategorias d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "DigImgdisenioSubcategorias.findByUrl", query = "SELECT d FROM DigImgdisenioSubcategorias d WHERE d.url = :url"),
    @NamedQuery(name = "DigImgdisenioSubcategorias.findByOrden", query = "SELECT d FROM DigImgdisenioSubcategorias d WHERE d.orden = :orden"),
    @NamedQuery(name = "DigImgdisenioSubcategorias.findByEstado", query = "SELECT d FROM DigImgdisenioSubcategorias d WHERE d.estado = :estado")})
public class DigImgdisenioSubcategorias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 150)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orden")
    private int orden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDigImgSub", fetch = FetchType.LAZY)
    private Set<DigImgdisenioSubcategoriasImg> digImgdisenioSubcategoriasImgSet;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DigImgdisenioCategorias idCategoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDigImgSubImg", fetch = FetchType.LAZY)
    private Set<ProductoImagenPropio> productoImagenPropioSet;

    public DigImgdisenioSubcategorias() {
    }

    public DigImgdisenioSubcategorias(Integer id) {
        this.id = id;
    }

    public DigImgdisenioSubcategorias(Integer id, String nombre, String url, int orden, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.url = url;
        this.orden = orden;
        this.estado = estado;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Set<DigImgdisenioSubcategoriasImg> getDigImgdisenioSubcategoriasImgSet() {
        return digImgdisenioSubcategoriasImgSet;
    }

    public void setDigImgdisenioSubcategoriasImgSet(Set<DigImgdisenioSubcategoriasImg> digImgdisenioSubcategoriasImgSet) {
        this.digImgdisenioSubcategoriasImgSet = digImgdisenioSubcategoriasImgSet;
    }

    public DigImgdisenioCategorias getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(DigImgdisenioCategorias idCategoria) {
        this.idCategoria = idCategoria;
    }

    @XmlTransient
    public Set<ProductoImagenPropio> getProductoImagenPropioSet() {
        return productoImagenPropioSet;
    }

    public void setProductoImagenPropioSet(Set<ProductoImagenPropio> productoImagenPropioSet) {
        this.productoImagenPropioSet = productoImagenPropioSet;
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
        if (!(object instanceof DigImgdisenioSubcategorias)) {
            return false;
        }
        DigImgdisenioSubcategorias other = (DigImgdisenioSubcategorias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.DigImgdisenioSubcategorias[ id=" + id + " ]";
    }
    
}
