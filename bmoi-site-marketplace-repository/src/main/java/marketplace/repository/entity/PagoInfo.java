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
@Table(name = "pago_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagoInfo.findAll", query = "SELECT p FROM PagoInfo p"),
    @NamedQuery(name = "PagoInfo.findById", query = "SELECT p FROM PagoInfo p WHERE p.id = :id"),
    @NamedQuery(name = "PagoInfo.findByIdOrder", query = "SELECT p FROM PagoInfo p WHERE p.idOrder = :idOrder"),
    @NamedQuery(name = "PagoInfo.findByIdDireccionfacturacion", query = "SELECT p FROM PagoInfo p WHERE p.idDireccionfacturacion = :idDireccionfacturacion"),
    @NamedQuery(name = "PagoInfo.findByFechapago", query = "SELECT p FROM PagoInfo p WHERE p.fechapago = :fechapago"),
    @NamedQuery(name = "PagoInfo.findByProveedorpago", query = "SELECT p FROM PagoInfo p WHERE p.proveedorpago = :proveedorpago"),
    @NamedQuery(name = "PagoInfo.findByMetodopago", query = "SELECT p FROM PagoInfo p WHERE p.metodopago = :metodopago"),
    @NamedQuery(name = "PagoInfo.findByEstadopago", query = "SELECT p FROM PagoInfo p WHERE p.estadopago = :estadopago"),
    @NamedQuery(name = "PagoInfo.findByReferenciaid", query = "SELECT p FROM PagoInfo p WHERE p.referenciaid = :referenciaid"),
    @NamedQuery(name = "PagoInfo.findByReferenciatranscaccion", query = "SELECT p FROM PagoInfo p WHERE p.referenciatranscaccion = :referenciatranscaccion"),
    @NamedQuery(name = "PagoInfo.findByNnumerotransaccio", query = "SELECT p FROM PagoInfo p WHERE p.nnumerotransaccio = :nnumerotransaccio"),
    @NamedQuery(name = "PagoInfo.findByMensageexterno", query = "SELECT p FROM PagoInfo p WHERE p.mensageexterno = :mensageexterno"),
    @NamedQuery(name = "PagoInfo.findByIgvPorcentaje", query = "SELECT p FROM PagoInfo p WHERE p.igvPorcentaje = :igvPorcentaje"),
    @NamedQuery(name = "PagoInfo.findByIgvMonto", query = "SELECT p FROM PagoInfo p WHERE p.igvMonto = :igvMonto"),
    @NamedQuery(name = "PagoInfo.findByMonto", query = "SELECT p FROM PagoInfo p WHERE p.monto = :monto"),
    @NamedQuery(name = "PagoInfo.findByF1", query = "SELECT p FROM PagoInfo p WHERE p.f1 = :f1"),
    @NamedQuery(name = "PagoInfo.findByF2", query = "SELECT p FROM PagoInfo p WHERE p.f2 = :f2"),
    @NamedQuery(name = "PagoInfo.findByF3", query = "SELECT p FROM PagoInfo p WHERE p.f3 = :f3"),
    @NamedQuery(name = "PagoInfo.findByF4", query = "SELECT p FROM PagoInfo p WHERE p.f4 = :f4"),
    @NamedQuery(name = "PagoInfo.findByF5", query = "SELECT p FROM PagoInfo p WHERE p.f5 = :f5"),
    @NamedQuery(name = "PagoInfo.findByUsuRegistro", query = "SELECT p FROM PagoInfo p WHERE p.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "PagoInfo.findByFecRegistro", query = "SELECT p FROM PagoInfo p WHERE p.fecRegistro = :fecRegistro"),
    @NamedQuery(name = "PagoInfo.findByUsuActualizacion", query = "SELECT p FROM PagoInfo p WHERE p.usuActualizacion = :usuActualizacion"),
    @NamedQuery(name = "PagoInfo.findByFecActualizacion", query = "SELECT p FROM PagoInfo p WHERE p.fecActualizacion = :fecActualizacion"),
    @NamedQuery(name = "PagoInfo.findByEstado", query = "SELECT p FROM PagoInfo p WHERE p.estado = :estado")})
public class PagoInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_order")
    private int idOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_direccionfacturacion")
    private int idDireccionfacturacion;
    @Column(name = "fechapago")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechapago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "proveedorpago")
    private String proveedorpago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "metodopago")
    private String metodopago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "estadopago")
    private String estadopago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "referenciaid")
    private String referenciaid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "referenciatranscaccion")
    private String referenciatranscaccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nnumerotransaccio")
    private String nnumerotransaccio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "mensageexterno")
    private String mensageexterno;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "igv_porcentaje")
    private BigDecimal igvPorcentaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "igv_monto")
    private BigDecimal igvMonto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto")
    private BigDecimal monto;
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
    @Size(min = 1, max = 100)
    @Column(name = "usu_registro")
    private String usuRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecRegistro;
    @Size(max = 100)
    @Column(name = "usu_actualizacion")
    private String usuActualizacion;
    @Column(name = "fec_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecActualizacion;
    @Size(max = 50)
    @Column(name = "estado")
    private String estado;

    public PagoInfo() {
    }

    public PagoInfo(Integer id) {
        this.id = id;
    }

    public PagoInfo(Integer id, int idOrder, int idDireccionfacturacion, String proveedorpago, String metodopago, String estadopago, String referenciaid, String referenciatranscaccion, String nnumerotransaccio, String mensageexterno, BigDecimal igvPorcentaje, BigDecimal igvMonto, BigDecimal monto, String usuRegistro, Date fecRegistro) {
        this.id = id;
        this.idOrder = idOrder;
        this.idDireccionfacturacion = idDireccionfacturacion;
        this.proveedorpago = proveedorpago;
        this.metodopago = metodopago;
        this.estadopago = estadopago;
        this.referenciaid = referenciaid;
        this.referenciatranscaccion = referenciatranscaccion;
        this.nnumerotransaccio = nnumerotransaccio;
        this.mensageexterno = mensageexterno;
        this.igvPorcentaje = igvPorcentaje;
        this.igvMonto = igvMonto;
        this.monto = monto;
        this.usuRegistro = usuRegistro;
        this.fecRegistro = fecRegistro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdDireccionfacturacion() {
        return idDireccionfacturacion;
    }

    public void setIdDireccionfacturacion(int idDireccionfacturacion) {
        this.idDireccionfacturacion = idDireccionfacturacion;
    }

    public Date getFechapago() {
        return fechapago;
    }

    public void setFechapago(Date fechapago) {
        this.fechapago = fechapago;
    }

    public String getProveedorpago() {
        return proveedorpago;
    }

    public void setProveedorpago(String proveedorpago) {
        this.proveedorpago = proveedorpago;
    }

    public String getMetodopago() {
        return metodopago;
    }

    public void setMetodopago(String metodopago) {
        this.metodopago = metodopago;
    }

    public String getEstadopago() {
        return estadopago;
    }

    public void setEstadopago(String estadopago) {
        this.estadopago = estadopago;
    }

    public String getReferenciaid() {
        return referenciaid;
    }

    public void setReferenciaid(String referenciaid) {
        this.referenciaid = referenciaid;
    }

    public String getReferenciatranscaccion() {
        return referenciatranscaccion;
    }

    public void setReferenciatranscaccion(String referenciatranscaccion) {
        this.referenciatranscaccion = referenciatranscaccion;
    }

    public String getNnumerotransaccio() {
        return nnumerotransaccio;
    }

    public void setNnumerotransaccio(String nnumerotransaccio) {
        this.nnumerotransaccio = nnumerotransaccio;
    }

    public String getMensageexterno() {
        return mensageexterno;
    }

    public void setMensageexterno(String mensageexterno) {
        this.mensageexterno = mensageexterno;
    }

    public BigDecimal getIgvPorcentaje() {
        return igvPorcentaje;
    }

    public void setIgvPorcentaje(BigDecimal igvPorcentaje) {
        this.igvPorcentaje = igvPorcentaje;
    }

    public BigDecimal getIgvMonto() {
        return igvMonto;
    }

    public void setIgvMonto(BigDecimal igvMonto) {
        this.igvMonto = igvMonto;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
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
        if (!(object instanceof PagoInfo)) {
            return false;
        }
        PagoInfo other = (PagoInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.PagoInfo[ id=" + id + " ]";
    }
    
}
