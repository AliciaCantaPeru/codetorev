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
@Table(name = "delivery")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Delivery.findAll", query = "SELECT d FROM Delivery d"),
    @NamedQuery(name = "Delivery.findById", query = "SELECT d FROM Delivery d WHERE d.id = :id"),
    @NamedQuery(name = "Delivery.findByIdDeliverymetodo", query = "SELECT d FROM Delivery d WHERE d.idDeliverymetodo = :idDeliverymetodo"),
    @NamedQuery(name = "Delivery.findByIdOrder", query = "SELECT d FROM Delivery d WHERE d.idOrder = :idOrder"),
    @NamedQuery(name = "Delivery.findByIdDireccionDelivery", query = "SELECT d FROM Delivery d WHERE d.idDireccionDelivery = :idDireccionDelivery"),
    @NamedQuery(name = "Delivery.findByCantidad", query = "SELECT d FROM Delivery d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Delivery.findByPesoTotal", query = "SELECT d FROM Delivery d WHERE d.pesoTotal = :pesoTotal"),
    @NamedQuery(name = "Delivery.findByCosto", query = "SELECT d FROM Delivery d WHERE d.costo = :costo"),
    @NamedQuery(name = "Delivery.findByCostoigv", query = "SELECT d FROM Delivery d WHERE d.costoigv = :costoigv"),
    @NamedQuery(name = "Delivery.findByCostoTotal", query = "SELECT d FROM Delivery d WHERE d.costoTotal = :costoTotal"),
    @NamedQuery(name = "Delivery.findByCostoasumidopor", query = "SELECT d FROM Delivery d WHERE d.costoasumidopor = :costoasumidopor"),
    @NamedQuery(name = "Delivery.findByComentarios", query = "SELECT d FROM Delivery d WHERE d.comentarios = :comentarios"),
    @NamedQuery(name = "Delivery.findByEstadoDelivery", query = "SELECT d FROM Delivery d WHERE d.estadoDelivery = :estadoDelivery"),
    @NamedQuery(name = "Delivery.findByFecDelivery", query = "SELECT d FROM Delivery d WHERE d.fecDelivery = :fecDelivery"),
    @NamedQuery(name = "Delivery.findByF1", query = "SELECT d FROM Delivery d WHERE d.f1 = :f1"),
    @NamedQuery(name = "Delivery.findByF2", query = "SELECT d FROM Delivery d WHERE d.f2 = :f2"),
    @NamedQuery(name = "Delivery.findByF3", query = "SELECT d FROM Delivery d WHERE d.f3 = :f3"),
    @NamedQuery(name = "Delivery.findByF4", query = "SELECT d FROM Delivery d WHERE d.f4 = :f4"),
    @NamedQuery(name = "Delivery.findByF5", query = "SELECT d FROM Delivery d WHERE d.f5 = :f5"),
    @NamedQuery(name = "Delivery.findByUsuRegistro", query = "SELECT d FROM Delivery d WHERE d.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "Delivery.findByFecRegistro", query = "SELECT d FROM Delivery d WHERE d.fecRegistro = :fecRegistro"),
    @NamedQuery(name = "Delivery.findByUsuActualizacion", query = "SELECT d FROM Delivery d WHERE d.usuActualizacion = :usuActualizacion"),
    @NamedQuery(name = "Delivery.findByFecActualizacion", query = "SELECT d FROM Delivery d WHERE d.fecActualizacion = :fecActualizacion"),
    @NamedQuery(name = "Delivery.findByEstado", query = "SELECT d FROM Delivery d WHERE d.estado = :estado")})
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_deliverymetodo")
    private int idDeliverymetodo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_order")
    private int idOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_direccion_delivery")
    private int idDireccionDelivery;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "peso_total")
    private BigDecimal pesoTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo")
    private BigDecimal costo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costoigv")
    private BigDecimal costoigv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo_total")
    private BigDecimal costoTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "costoasumidopor")
    private boolean costoasumidopor;
    @Size(max = 250)
    @Column(name = "comentarios")
    private String comentarios;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "estado_delivery")
    private String estadoDelivery;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_delivery")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecDelivery;
    @Size(max = 1)
    @Column(name = "f1")
    private String f1;
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
    @Size(max = 150)
    @Column(name = "usu_actualizacion")
    private String usuActualizacion;
    @Column(name = "fec_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecActualizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;

    public Delivery() {
    }

    public Delivery(Integer id) {
        this.id = id;
    }

    public Delivery(Integer id, int idDeliverymetodo, int idOrder, int idDireccionDelivery, BigDecimal costo, BigDecimal costoigv, BigDecimal costoTotal, boolean costoasumidopor, String estadoDelivery, Date fecDelivery, String usuRegistro, Date fecRegistro, int estado) {
        this.id = id;
        this.idDeliverymetodo = idDeliverymetodo;
        this.idOrder = idOrder;
        this.idDireccionDelivery = idDireccionDelivery;
        this.costo = costo;
        this.costoigv = costoigv;
        this.costoTotal = costoTotal;
        this.costoasumidopor = costoasumidopor;
        this.estadoDelivery = estadoDelivery;
        this.fecDelivery = fecDelivery;
        this.usuRegistro = usuRegistro;
        this.fecRegistro = fecRegistro;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdDeliverymetodo() {
        return idDeliverymetodo;
    }

    public void setIdDeliverymetodo(int idDeliverymetodo) {
        this.idDeliverymetodo = idDeliverymetodo;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdDireccionDelivery() {
        return idDireccionDelivery;
    }

    public void setIdDireccionDelivery(int idDireccionDelivery) {
        this.idDireccionDelivery = idDireccionDelivery;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(BigDecimal pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public BigDecimal getCostoigv() {
        return costoigv;
    }

    public void setCostoigv(BigDecimal costoigv) {
        this.costoigv = costoigv;
    }

    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    public boolean getCostoasumidopor() {
        return costoasumidopor;
    }

    public void setCostoasumidopor(boolean costoasumidopor) {
        this.costoasumidopor = costoasumidopor;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getEstadoDelivery() {
        return estadoDelivery;
    }

    public void setEstadoDelivery(String estadoDelivery) {
        this.estadoDelivery = estadoDelivery;
    }

    public Date getFecDelivery() {
        return fecDelivery;
    }

    public void setFecDelivery(Date fecDelivery) {
        this.fecDelivery = fecDelivery;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
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

    public String getUsuActualizacion() {
        return usuActualizacion;
    }

    public void setUsuActualizacion(String usuActualizacion) {
        this.usuActualizacion = usuActualizacion;
    }

    public Date getFecActualizacion() {
        return fecActualizacion;
    }

    public void setFecActualizacion(Date fecActualizacion) {
        this.fecActualizacion = fecActualizacion;
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
        if (!(object instanceof Delivery)) {
            return false;
        }
        Delivery other = (Delivery) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.Delivery[ id=" + id + " ]";
    }
    
}
