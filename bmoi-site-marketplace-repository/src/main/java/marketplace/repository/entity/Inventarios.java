/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "inventarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventarios.findAll", query = "SELECT i FROM Inventarios i"),
    @NamedQuery(name = "Inventarios.findById", query = "SELECT i FROM Inventarios i WHERE i.id = :id"),
    @NamedQuery(name = "Inventarios.findByIdProducto", query = "SELECT i FROM Inventarios i WHERE i.idProducto = :idProducto"),
    @NamedQuery(name = "Inventarios.findByDescripcion", query = "SELECT i FROM Inventarios i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "Inventarios.findByPrecio", query = "SELECT i FROM Inventarios i WHERE i.precio = :precio"),
    @NamedQuery(name = "Inventarios.findByPreDescuento", query = "SELECT i FROM Inventarios i WHERE i.preDescuento = :preDescuento"),
    @NamedQuery(name = "Inventarios.findByPreCompra", query = "SELECT i FROM Inventarios i WHERE i.preCompra = :preCompra"),
    @NamedQuery(name = "Inventarios.findByPreVenta", query = "SELECT i FROM Inventarios i WHERE i.preVenta = :preVenta"),
    @NamedQuery(name = "Inventarios.findByStock", query = "SELECT i FROM Inventarios i WHERE i.stock = :stock"),
    @NamedQuery(name = "Inventarios.findByFecRegistro", query = "SELECT i FROM Inventarios i WHERE i.fecRegistro = :fecRegistro"),
    @NamedQuery(name = "Inventarios.findByUsuRegistro", query = "SELECT i FROM Inventarios i WHERE i.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "Inventarios.findByFecActualizacion", query = "SELECT i FROM Inventarios i WHERE i.fecActualizacion = :fecActualizacion"),
    @NamedQuery(name = "Inventarios.findByUsuActualizacion", query = "SELECT i FROM Inventarios i WHERE i.usuActualizacion = :usuActualizacion"),
    @NamedQuery(name = "Inventarios.findByEstado", query = "SELECT i FROM Inventarios i WHERE i.estado = :estado")})
public class Inventarios implements Serializable {

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
    @Size(max = 2500)
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private BigDecimal precio;
    @Column(name = "pre_descuento")
    private BigDecimal preDescuento;
    @Column(name = "pre_compra")
    private BigDecimal preCompra;
    @Column(name = "pre_venta")
    private BigDecimal preVenta;
    @Column(name = "stock")
    private Integer stock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecRegistro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "usu_registro")
    private String usuRegistro;
    @Column(name = "fec_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecActualizacion;
    @Size(max = 12)
    @Column(name = "usu_actualizacion")
    private String usuActualizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;

    public Inventarios() {
    }

    public Inventarios(Integer id) {
        this.id = id;
    }

    public Inventarios(Integer id, int idProducto, Date fecRegistro, String usuRegistro, int estado) {
        this.id = id;
        this.idProducto = idProducto;
        this.fecRegistro = fecRegistro;
        this.usuRegistro = usuRegistro;
        this.estado = estado;
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getPreDescuento() {
        return preDescuento;
    }

    public void setPreDescuento(BigDecimal preDescuento) {
        this.preDescuento = preDescuento;
    }

    public BigDecimal getPreCompra() {
        return preCompra;
    }

    public void setPreCompra(BigDecimal preCompra) {
        this.preCompra = preCompra;
    }

    public BigDecimal getPreVenta() {
        return preVenta;
    }

    public void setPreVenta(BigDecimal preVenta) {
        this.preVenta = preVenta;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getFecRegistro() {
        return fecRegistro;
    }

    public void setFecRegistro(Date fecRegistro) {
        this.fecRegistro = fecRegistro;
    }

    public String getUsuRegistro() {
        return usuRegistro;
    }

    public void setUsuRegistro(String usuRegistro) {
        this.usuRegistro = usuRegistro;
    }

    public Date getFecActualizacion() {
        return fecActualizacion;
    }

    public void setFecActualizacion(Date fecActualizacion) {
        this.fecActualizacion = fecActualizacion;
    }

    public String getUsuActualizacion() {
        return usuActualizacion;
    }

    public void setUsuActualizacion(String usuActualizacion) {
        this.usuActualizacion = usuActualizacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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
        if (!(object instanceof Inventarios)) {
            return false;
        }
        Inventarios other = (Inventarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.Inventarios[ id=" + id + " ]";
    }
    
}
