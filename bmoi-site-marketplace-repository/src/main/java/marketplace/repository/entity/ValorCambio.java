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
@Table(name = "valor_cambio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ValorCambio.findAll", query = "SELECT v FROM ValorCambio v"),
    @NamedQuery(name = "ValorCambio.findById", query = "SELECT v FROM ValorCambio v WHERE v.id = :id"),
    @NamedQuery(name = "ValorCambio.findByMoneda", query = "SELECT v FROM ValorCambio v WHERE v.moneda = :moneda"),
    @NamedQuery(name = "ValorCambio.findByValCompra", query = "SELECT v FROM ValorCambio v WHERE v.valCompra = :valCompra"),
    @NamedQuery(name = "ValorCambio.findByValVenta", query = "SELECT v FROM ValorCambio v WHERE v.valVenta = :valVenta"),
    @NamedQuery(name = "ValorCambio.findByFecha", query = "SELECT v FROM ValorCambio v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "ValorCambio.findBySimbolo", query = "SELECT v FROM ValorCambio v WHERE v.simbolo = :simbolo")})
public class ValorCambio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 20)
    @Column(name = "moneda")
    private String moneda;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "val_compra")
    private BigDecimal valCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "val_venta")
    private BigDecimal valVenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "simbolo")
    private String simbolo;

    public ValorCambio() {
    }

    public ValorCambio(Integer id) {
        this.id = id;
    }

    public ValorCambio(Integer id, BigDecimal valCompra, BigDecimal valVenta, Date fecha, String simbolo) {
        this.id = id;
        this.valCompra = valCompra;
        this.valVenta = valVenta;
        this.fecha = fecha;
        this.simbolo = simbolo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getValCompra() {
        return valCompra;
    }

    public void setValCompra(BigDecimal valCompra) {
        this.valCompra = valCompra;
    }

    public BigDecimal getValVenta() {
        return valVenta;
    }

    public void setValVenta(BigDecimal valVenta) {
        this.valVenta = valVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
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
        if (!(object instanceof ValorCambio)) {
            return false;
        }
        ValorCambio other = (ValorCambio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.ValorCambio[ id=" + id + " ]";
    }
    
}
