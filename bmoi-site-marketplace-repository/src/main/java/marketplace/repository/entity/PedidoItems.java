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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Martin Pilar
 */
@Entity
@Table(name = "pedido_items")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PedidoItems.findAll", query = "SELECT p FROM PedidoItems p"),
    @NamedQuery(name = "PedidoItems.findById", query = "SELECT p FROM PedidoItems p WHERE p.id = :id"),
    @NamedQuery(name = "PedidoItems.findByPrecio", query = "SELECT p FROM PedidoItems p WHERE p.precio = :precio"),
    @NamedQuery(name = "PedidoItems.findByCantidad", query = "SELECT p FROM PedidoItems p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "PedidoItems.findByDescuento", query = "SELECT p FROM PedidoItems p WHERE p.descuento = :descuento"),
    @NamedQuery(name = "PedidoItems.findByIdPedido", query = "SELECT p FROM PedidoItems p WHERE p.idPedido = :idPedido"),
    @NamedQuery(name = "PedidoItems.findByIdProducto", query = "SELECT p FROM PedidoItems p WHERE p.idProducto = :idProducto")})
public class PedidoItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private BigDecimal precio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descuento")
    private BigDecimal descuento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_pedido")
    private int idPedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_producto")
    private int idProducto;

    public PedidoItems() {
    }

    public PedidoItems(Integer id) {
        this.id = id;
    }

    public PedidoItems(Integer id, BigDecimal precio, int cantidad, BigDecimal descuento, int idPedido, int idProducto) {
        this.id = id;
        this.precio = precio;
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.idPedido = idPedido;
        this.idProducto = idProducto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
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
        if (!(object instanceof PedidoItems)) {
            return false;
        }
        PedidoItems other = (PedidoItems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.PedidoItems[ id=" + id + " ]";
    }
    
}
