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
@Table(name = "seller_plan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SellerPlan.findAll", query = "SELECT s FROM SellerPlan s"),
    @NamedQuery(name = "SellerPlan.findById", query = "SELECT s FROM SellerPlan s WHERE s.id = :id"),
    @NamedQuery(name = "SellerPlan.findByFecInicio", query = "SELECT s FROM SellerPlan s WHERE s.fecInicio = :fecInicio"),
    @NamedQuery(name = "SellerPlan.findByFecFinal", query = "SELECT s FROM SellerPlan s WHERE s.fecFinal = :fecFinal"),
    @NamedQuery(name = "SellerPlan.findByF1", query = "SELECT s FROM SellerPlan s WHERE s.f1 = :f1"),
    @NamedQuery(name = "SellerPlan.findByF2", query = "SELECT s FROM SellerPlan s WHERE s.f2 = :f2"),
    @NamedQuery(name = "SellerPlan.findByF3", query = "SELECT s FROM SellerPlan s WHERE s.f3 = :f3"),
    @NamedQuery(name = "SellerPlan.findByF4", query = "SELECT s FROM SellerPlan s WHERE s.f4 = :f4"),
    @NamedQuery(name = "SellerPlan.findByF5", query = "SELECT s FROM SellerPlan s WHERE s.f5 = :f5"),
    @NamedQuery(name = "SellerPlan.findByUsuRegistro", query = "SELECT s FROM SellerPlan s WHERE s.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "SellerPlan.findByFecRegistro", query = "SELECT s FROM SellerPlan s WHERE s.fecRegistro = :fecRegistro"),
    @NamedQuery(name = "SellerPlan.findByUsuActualizacion", query = "SELECT s FROM SellerPlan s WHERE s.usuActualizacion = :usuActualizacion"),
    @NamedQuery(name = "SellerPlan.findByFecActualizacion", query = "SELECT s FROM SellerPlan s WHERE s.fecActualizacion = :fecActualizacion"),
    @NamedQuery(name = "SellerPlan.findByEstado", query = "SELECT s FROM SellerPlan s WHERE s.estado = :estado")})
public class SellerPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_final")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecFinal;
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
    @JoinColumn(name = "id_plan", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BoPlanes idPlan;
    @JoinColumn(name = "id_seller", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sellers idSeller;

    public SellerPlan() {
    }

    public SellerPlan(Integer id) {
        this.id = id;
    }

    public SellerPlan(Integer id, Date fecInicio, Date fecFinal, String usuRegistro, Date fecRegistro, int estado) {
        this.id = id;
        this.fecInicio = fecInicio;
        this.fecFinal = fecFinal;
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

    public Date getFecInicio() {
        return fecInicio;
    }

    public void setFecInicio(Date fecInicio) {
        this.fecInicio = fecInicio;
    }

    public Date getFecFinal() {
        return fecFinal;
    }

    public void setFecFinal(Date fecFinal) {
        this.fecFinal = fecFinal;
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

    public BoPlanes getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(BoPlanes idPlan) {
        this.idPlan = idPlan;
    }

    public Sellers getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(Sellers idSeller) {
        this.idSeller = idSeller;
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
        if (!(object instanceof SellerPlan)) {
            return false;
        }
        SellerPlan other = (SellerPlan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.SellerPlan[ id=" + id + " ]";
    }
    
}
