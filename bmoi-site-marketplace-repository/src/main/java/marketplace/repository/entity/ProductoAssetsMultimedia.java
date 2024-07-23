/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Martin Pilar
 */
@Entity
@Table(name = "producto_assets_multimedia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoAssetsMultimedia.findAll", query = "SELECT p FROM ProductoAssetsMultimedia p"),
    @NamedQuery(name = "ProductoAssetsMultimedia.findById", query = "SELECT p FROM ProductoAssetsMultimedia p WHERE p.id = :id"),
    @NamedQuery(name = "ProductoAssetsMultimedia.findByGrdMultimedia", query = "SELECT p FROM ProductoAssetsMultimedia p WHERE p.grdMultimedia = :grdMultimedia"),
    @NamedQuery(name = "ProductoAssetsMultimedia.findByPqnMultimedia", query = "SELECT p FROM ProductoAssetsMultimedia p WHERE p.pqnMultimedia = :pqnMultimedia"),
    @NamedQuery(name = "ProductoAssetsMultimedia.findByDescripcion", query = "SELECT p FROM ProductoAssetsMultimedia p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "ProductoAssetsMultimedia.findByPage", query = "SELECT p FROM ProductoAssetsMultimedia p WHERE p.page = :page"),
    @NamedQuery(name = "ProductoAssetsMultimedia.findByF2", query = "SELECT p FROM ProductoAssetsMultimedia p WHERE p.f2 = :f2"),
    @NamedQuery(name = "ProductoAssetsMultimedia.findByF3", query = "SELECT p FROM ProductoAssetsMultimedia p WHERE p.f3 = :f3"),
    @NamedQuery(name = "ProductoAssetsMultimedia.findByF4", query = "SELECT p FROM ProductoAssetsMultimedia p WHERE p.f4 = :f4"),
    @NamedQuery(name = "ProductoAssetsMultimedia.findByF5", query = "SELECT p FROM ProductoAssetsMultimedia p WHERE p.f5 = :f5"),
    @NamedQuery(name = "ProductoAssetsMultimedia.findByUsuRegistro", query = "SELECT p FROM ProductoAssetsMultimedia p WHERE p.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "ProductoAssetsMultimedia.findByFecRegistro", query = "SELECT p FROM ProductoAssetsMultimedia p WHERE p.fecRegistro = :fecRegistro"),
    @NamedQuery(name = "ProductoAssetsMultimedia.findByEstado", query = "SELECT p FROM ProductoAssetsMultimedia p WHERE p.estado = :estado")})
public class ProductoAssetsMultimedia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 250)
    @Column(name = "grd_multimedia")
    private String grdMultimedia;
    @Size(max = 250)
    @Column(name = "pqn_multimedia")
    private String pqnMultimedia;
    @Size(max = 250)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 50)
    @Column(name = "page")
    private String page;
    @Size(max = 1)
    @Column(name = "f2")
    private String f2;
    @Size(max = 1)
    @Column(name = "f3")
    private String f3;
    @Size(max = 1)
    @Column(name = "f4")
    private String f4;
    @Size(max = 1)
    @Column(name = "f5")
    private String f5;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "usu_registro")
    private String usuRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecRegistro;
    @Column(name = "estado")
    private Integer estado;
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Productos idProducto;

    public ProductoAssetsMultimedia() {
    }

    public ProductoAssetsMultimedia(Integer id) {
        this.id = id;
    }

    public ProductoAssetsMultimedia(Integer id, String usuRegistro, Date fecRegistro) {
        this.id = id;
        this.usuRegistro = usuRegistro;
        this.fecRegistro = fecRegistro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrdMultimedia() {
        return grdMultimedia;
    }

    public void setGrdMultimedia(String grdMultimedia) {
        this.grdMultimedia = grdMultimedia;
    }

    public String getPqnMultimedia() {
        return pqnMultimedia;
    }

    public void setPqnMultimedia(String pqnMultimedia) {
        this.pqnMultimedia = pqnMultimedia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getF2() {
        return f2;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }

    public String getF3() {
        return f3;
    }

    public void setF3(String f3) {
        this.f3 = f3;
    }

    public String getF4() {
        return f4;
    }

    public void setF4(String f4) {
        this.f4 = f4;
    }

    public String getF5() {
        return f5;
    }

    public void setF5(String f5) {
        this.f5 = f5;
    }

    public String getUsuRegistro() {
        return usuRegistro;
    }

    public void setUsuRegistro(String usuRegistro) {
        this.usuRegistro = usuRegistro;
    }

    public Date getFecRegistro() {
        return fecRegistro;
    }

    public void setFecRegistro(Date fecRegistro) {
        this.fecRegistro = fecRegistro;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
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
        if (!(object instanceof ProductoAssetsMultimedia)) {
            return false;
        }
        ProductoAssetsMultimedia other = (ProductoAssetsMultimedia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.ProductoAssetsMultimedia[ id=" + id + " ]";
    }
    
}
