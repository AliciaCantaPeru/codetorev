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
@Table(name = "pedidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedidos.findAll", query = "SELECT p FROM Pedidos p"),
    @NamedQuery(name = "Pedidos.findById", query = "SELECT p FROM Pedidos p WHERE p.id = :id"),
    @NamedQuery(name = "Pedidos.findByEmaComprador", query = "SELECT p FROM Pedidos p WHERE p.emaComprador = :emaComprador"),
    @NamedQuery(name = "Pedidos.findByFecPedido", query = "SELECT p FROM Pedidos p WHERE p.fecPedido = :fecPedido"),
    @NamedQuery(name = "Pedidos.findBySubtotal", query = "SELECT p FROM Pedidos p WHERE p.subtotal = :subtotal"),
    @NamedQuery(name = "Pedidos.findByIdIntencionPago", query = "SELECT p FROM Pedidos p WHERE p.idIntencionPago = :idIntencionPago"),
    @NamedQuery(name = "Pedidos.findByIdUsuario", query = "SELECT p FROM Pedidos p WHERE p.idUsuario = :idUsuario"),
    @NamedQuery(name = "Pedidos.findByTotal", query = "SELECT p FROM Pedidos p WHERE p.total = :total"),
    @NamedQuery(name = "Pedidos.findByFecEnvio", query = "SELECT p FROM Pedidos p WHERE p.fecEnvio = :fecEnvio"),
    @NamedQuery(name = "Pedidos.findByEstPedido", query = "SELECT p FROM Pedidos p WHERE p.estPedido = :estPedido"),
    @NamedQuery(name = "Pedidos.findByIdBasket", query = "SELECT p FROM Pedidos p WHERE p.idBasket = :idBasket")})
public class Pedidos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "ema_comprador")
    private String emaComprador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_pedido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecPedido;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "subtotal")
    private BigDecimal subtotal;
    @Size(max = 2147483647)
    @Column(name = "id_intencion_pago")
    private String idIntencionPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private BigDecimal total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_envio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecEnvio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "est_pedido")
    private String estPedido;
    @Size(max = 2147483647)
    @Column(name = "id_basket")
    private String idBasket;
    @JoinColumn(name = "id_delivery_metodo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DeliveryMetodos idDeliveryMetodo;
    @JoinColumn(name = "id_direccion", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Direcciones idDireccion;
    @JoinColumn(name = "id_pedido_estado", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PedidoEstados idPedidoEstado;

    public Pedidos() {
    }

    public Pedidos(Integer id) {
        this.id = id;
    }

    public Pedidos(Integer id, String emaComprador, Date fecPedido, BigDecimal subtotal, int idUsuario, BigDecimal total, Date fecEnvio, String estPedido) {
        this.id = id;
        this.emaComprador = emaComprador;
        this.fecPedido = fecPedido;
        this.subtotal = subtotal;
        this.idUsuario = idUsuario;
        this.total = total;
        this.fecEnvio = fecEnvio;
        this.estPedido = estPedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmaComprador() {
        return emaComprador;
    }

    public void setEmaComprador(String emaComprador) {
        this.emaComprador = emaComprador;
    }

    public Date getFecPedido() {
        return fecPedido;
    }

    public void setFecPedido(Date fecPedido) {
        this.fecPedido = fecPedido;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public String getIdIntencionPago() {
        return idIntencionPago;
    }

    public void setIdIntencionPago(String idIntencionPago) {
        this.idIntencionPago = idIntencionPago;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getFecEnvio() {
        return fecEnvio;
    }

    public void setFecEnvio(Date fecEnvio) {
        this.fecEnvio = fecEnvio;
    }

    public String getEstPedido() {
        return estPedido;
    }

    public void setEstPedido(String estPedido) {
        this.estPedido = estPedido;
    }

    public String getIdBasket() {
        return idBasket;
    }

    public void setIdBasket(String idBasket) {
        this.idBasket = idBasket;
    }

    public DeliveryMetodos getIdDeliveryMetodo() {
        return idDeliveryMetodo;
    }

    public void setIdDeliveryMetodo(DeliveryMetodos idDeliveryMetodo) {
        this.idDeliveryMetodo = idDeliveryMetodo;
    }

    public Direcciones getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Direcciones idDireccion) {
        this.idDireccion = idDireccion;
    }

    public PedidoEstados getIdPedidoEstado() {
        return idPedidoEstado;
    }

    public void setIdPedidoEstado(PedidoEstados idPedidoEstado) {
        this.idPedidoEstado = idPedidoEstado;
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
        if (!(object instanceof Pedidos)) {
            return false;
        }
        Pedidos other = (Pedidos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.Pedidos[ id=" + id + " ]";
    }
    
}
