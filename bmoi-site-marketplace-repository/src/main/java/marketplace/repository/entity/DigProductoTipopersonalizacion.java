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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Martin Pilar
 */
@Entity
@Table(name = "dig_producto_tipopersonalizacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DigProductoTipopersonalizacion.findAll", query = "SELECT d FROM DigProductoTipopersonalizacion d"),
    @NamedQuery(name = "DigProductoTipopersonalizacion.findById", query = "SELECT d FROM DigProductoTipopersonalizacion d WHERE d.id = :id")})
public class DigProductoTipopersonalizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_digtipopersonalizacion", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DigTiposPersonalizacion idDigtipopersonalizacion;
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Productos idProducto;

    public DigProductoTipopersonalizacion() {
    }

    public DigProductoTipopersonalizacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DigTiposPersonalizacion getIdDigtipopersonalizacion() {
        return idDigtipopersonalizacion;
    }

    public void setIdDigtipopersonalizacion(DigTiposPersonalizacion idDigtipopersonalizacion) {
        this.idDigtipopersonalizacion = idDigtipopersonalizacion;
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
        if (!(object instanceof DigProductoTipopersonalizacion)) {
            return false;
        }
        DigProductoTipopersonalizacion other = (DigProductoTipopersonalizacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.DigProductoTipopersonalizacion[ id=" + id + " ]";
    }
    
}
