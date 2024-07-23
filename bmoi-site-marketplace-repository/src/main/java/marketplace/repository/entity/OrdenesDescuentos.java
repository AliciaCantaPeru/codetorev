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
@Table(name = "ordenes_descuentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenesDescuentos.findAll", query = "SELECT o FROM OrdenesDescuentos o"),
    @NamedQuery(name = "OrdenesDescuentos.findById", query = "SELECT o FROM OrdenesDescuentos o WHERE o.id = :id"),
    @NamedQuery(name = "OrdenesDescuentos.findByIdOrder", query = "SELECT o FROM OrdenesDescuentos o WHERE o.idOrder = :idOrder"),
    @NamedQuery(name = "OrdenesDescuentos.findByDescripcion", query = "SELECT o FROM OrdenesDescuentos o WHERE o.descripcion = :descripcion"),
    @NamedQuery(name = "OrdenesDescuentos.findByMonto", query = "SELECT o FROM OrdenesDescuentos o WHERE o.monto = :monto"),
    @NamedQuery(name = "OrdenesDescuentos.findByPorcentaje", query = "SELECT o FROM OrdenesDescuentos o WHERE o.porcentaje = :porcentaje"),
    @NamedQuery(name = "OrdenesDescuentos.findByIgv", query = "SELECT o FROM OrdenesDescuentos o WHERE o.igv = :igv"),
    @NamedQuery(name = "OrdenesDescuentos.findByDescuentoconigv", query = "SELECT o FROM OrdenesDescuentos o WHERE o.descuentoconigv = :descuentoconigv"),
    @NamedQuery(name = "OrdenesDescuentos.findByF1", query = "SELECT o FROM OrdenesDescuentos o WHERE o.f1 = :f1"),
    @NamedQuery(name = "OrdenesDescuentos.findByF2", query = "SELECT o FROM OrdenesDescuentos o WHERE o.f2 = :f2"),
    @NamedQuery(name = "OrdenesDescuentos.findByF3", query = "SELECT o FROM OrdenesDescuentos o WHERE o.f3 = :f3"),
    @NamedQuery(name = "OrdenesDescuentos.findByF4", query = "SELECT o FROM OrdenesDescuentos o WHERE o.f4 = :f4"),
    @NamedQuery(name = "OrdenesDescuentos.findByF5", query = "SELECT o FROM OrdenesDescuentos o WHERE o.f5 = :f5"),
    @NamedQuery(name = "OrdenesDescuentos.findByFecRegistro", query = "SELECT o FROM OrdenesDescuentos o WHERE o.fecRegistro = :fecRegistro"),
    @NamedQuery(name = "OrdenesDescuentos.findByUsuRegistro", query = "SELECT o FROM OrdenesDescuentos o WHERE o.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "OrdenesDescuentos.findByUsuActualizacion", query = "SELECT o FROM OrdenesDescuentos o WHERE o.usuActualizacion = :usuActualizacion"),
    @NamedQuery(name = "OrdenesDescuentos.findByFecActualizacion", query = "SELECT o FROM OrdenesDescuentos o WHERE o.fecActualizacion = :fecActualizacion"),
    @NamedQuery(name = "OrdenesDescuentos.findByEstado", query = "SELECT o FROM OrdenesDescuentos o WHERE o.estado = :estado")})
public class OrdenesDescuentos implements Serializable {

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
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto")
    private BigDecimal monto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentaje")
    private BigDecimal porcentaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "igv")
    private BigDecimal igv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descuentoconigv")
    private BigDecimal descuentoconigv;
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
    @Column(name = "fec_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecRegistro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "usu_registro")
    private String usuRegistro;
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

    public OrdenesDescuentos() {
    }

    public OrdenesDescuentos(Integer id) {
        this.id = id;
    }

    public OrdenesDescuentos(Integer id, int idOrder, BigDecimal monto, BigDecimal porcentaje, BigDecimal igv, BigDecimal descuentoconigv, Date fecRegistro, String usuRegistro, int estado) {
        this.id = id;
        this.idOrder = idOrder;
        this.monto = monto;
        this.porcentaje = porcentaje;
        this.igv = igv;
        this.descuentoconigv = descuentoconigv;
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

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public BigDecimal getIgv() {
        return igv;
    }

    public void setIgv(BigDecimal igv) {
        this.igv = igv;
    }

    public BigDecimal getDescuentoconigv() {
        return descuentoconigv;
    }

    public void setDescuentoconigv(BigDecimal descuentoconigv) {
        this.descuentoconigv = descuentoconigv;
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
        if (!(object instanceof OrdenesDescuentos)) {
            return false;
        }
        OrdenesDescuentos other = (OrdenesDescuentos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.OrdenesDescuentos[ id=" + id + " ]";
    }
    
}
