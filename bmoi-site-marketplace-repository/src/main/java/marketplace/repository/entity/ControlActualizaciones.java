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
@Table(name = "control_actualizaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ControlActualizaciones.findAll", query = "SELECT c FROM ControlActualizaciones c"),
    @NamedQuery(name = "ControlActualizaciones.findById", query = "SELECT c FROM ControlActualizaciones c WHERE c.id = :id"),
    @NamedQuery(name = "ControlActualizaciones.findByCodigo", query = "SELECT c FROM ControlActualizaciones c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "ControlActualizaciones.findByFecRegistro", query = "SELECT c FROM ControlActualizaciones c WHERE c.fecRegistro = :fecRegistro"),
    @NamedQuery(name = "ControlActualizaciones.findByUsuRegistro", query = "SELECT c FROM ControlActualizaciones c WHERE c.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "ControlActualizaciones.findByEstado", query = "SELECT c FROM ControlActualizaciones c WHERE c.estado = :estado")})
public class ControlActualizaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "codigo")
    private String codigo;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;

    public ControlActualizaciones() {
    }

    public ControlActualizaciones(Integer id) {
        this.id = id;
    }

    public ControlActualizaciones(Integer id, String codigo, Date fecRegistro, String usuRegistro, int estado) {
        this.id = id;
        this.codigo = codigo;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
        if (!(object instanceof ControlActualizaciones)) {
            return false;
        }
        ControlActualizaciones other = (ControlActualizaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.ControlActualizaciones[ id=" + id + " ]";
    }
    
}
