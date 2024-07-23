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
@Table(name = "galerias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Galerias.findAll", query = "SELECT g FROM Galerias g"),
    @NamedQuery(name = "Galerias.findById", query = "SELECT g FROM Galerias g WHERE g.id = :id"),
    @NamedQuery(name = "Galerias.findByIdProducto", query = "SELECT g FROM Galerias g WHERE g.idProducto = :idProducto"),
    @NamedQuery(name = "Galerias.findByFoto", query = "SELECT g FROM Galerias g WHERE g.foto = :foto"),
    @NamedQuery(name = "Galerias.findByFecRegistro", query = "SELECT g FROM Galerias g WHERE g.fecRegistro = :fecRegistro"),
    @NamedQuery(name = "Galerias.findByUsuRegistro", query = "SELECT g FROM Galerias g WHERE g.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "Galerias.findByFecActualizacion", query = "SELECT g FROM Galerias g WHERE g.fecActualizacion = :fecActualizacion"),
    @NamedQuery(name = "Galerias.findByUsuActualizacion", query = "SELECT g FROM Galerias g WHERE g.usuActualizacion = :usuActualizacion"),
    @NamedQuery(name = "Galerias.findByEstado", query = "SELECT g FROM Galerias g WHERE g.estado = :estado")})
public class Galerias implements Serializable {

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
    @Size(max = 2147483647)
    @Column(name = "foto")
    private String foto;
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

    public Galerias() {
    }

    public Galerias(Integer id) {
        this.id = id;
    }

    public Galerias(Integer id, int idProducto, Date fecRegistro, String usuRegistro, int estado) {
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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
        if (!(object instanceof Galerias)) {
            return false;
        }
        Galerias other = (Galerias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.Galerias[ id=" + id + " ]";
    }
    
}
