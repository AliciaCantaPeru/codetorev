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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Martin Pilar
 */
@Entity
@Table(name = "medidas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medidas.findAll", query = "SELECT m FROM Medidas m"),
    @NamedQuery(name = "Medidas.findById", query = "SELECT m FROM Medidas m WHERE m.id = :id"),
    @NamedQuery(name = "Medidas.findByDescripcion", query = "SELECT m FROM Medidas m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Medidas.findByEstado", query = "SELECT m FROM Medidas m WHERE m.estado = :estado"),
    @NamedQuery(name = "Medidas.findByFecActualizacion", query = "SELECT m FROM Medidas m WHERE m.fecActualizacion = :fecActualizacion"),
    @NamedQuery(name = "Medidas.findByFecRegistro", query = "SELECT m FROM Medidas m WHERE m.fecRegistro = :fecRegistro"),
    @NamedQuery(name = "Medidas.findByIdProducto", query = "SELECT m FROM Medidas m WHERE m.idProducto = :idProducto"),
    @NamedQuery(name = "Medidas.findByNombre", query = "SELECT m FROM Medidas m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Medidas.findByPreNormal", query = "SELECT m FROM Medidas m WHERE m.preNormal = :preNormal"),
    @NamedQuery(name = "Medidas.findByPreNormal2", query = "SELECT m FROM Medidas m WHERE m.preNormal2 = :preNormal2"),
    @NamedQuery(name = "Medidas.findByPreOferta", query = "SELECT m FROM Medidas m WHERE m.preOferta = :preOferta"),
    @NamedQuery(name = "Medidas.findByUsuActualizacion", query = "SELECT m FROM Medidas m WHERE m.usuActualizacion = :usuActualizacion"),
    @NamedQuery(name = "Medidas.findByUsuRegistro", query = "SELECT m FROM Medidas m WHERE m.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "Medidas.findByUnidadMedida", query = "SELECT m FROM Medidas m WHERE m.unidadMedida = :unidadMedida")})
public class Medidas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fec_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecActualizacion;
    @Column(name = "fec_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecRegistro;
    @Column(name = "id_producto")
    private Integer idProducto;
    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pre_normal")
    private Double preNormal;
    @Column(name = "pre_normal2")
    private Double preNormal2;
    @Column(name = "pre_oferta")
    private Double preOferta;
    @Size(max = 255)
    @Column(name = "usu_actualizacion")
    private String usuActualizacion;
    @Size(max = 255)
    @Column(name = "usu_registro")
    private String usuRegistro;
    @Size(max = 50)
    @Column(name = "unidad_medida")
    private String unidadMedida;

    public Medidas() {
    }

    public Medidas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFecActualizacion() {
        return fecActualizacion;
    }

    public void setFecActualizacion(Date fecActualizacion) {
        this.fecActualizacion = fecActualizacion;
    }

    public Date getFecRegistro() {
        return fecRegistro;
    }

    public void setFecRegistro(Date fecRegistro) {
        this.fecRegistro = fecRegistro;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPreNormal() {
        return preNormal;
    }

    public void setPreNormal(Double preNormal) {
        this.preNormal = preNormal;
    }

    public Double getPreNormal2() {
        return preNormal2;
    }

    public void setPreNormal2(Double preNormal2) {
        this.preNormal2 = preNormal2;
    }

    public Double getPreOferta() {
        return preOferta;
    }

    public void setPreOferta(Double preOferta) {
        this.preOferta = preOferta;
    }

    public String getUsuActualizacion() {
        return usuActualizacion;
    }

    public void setUsuActualizacion(String usuActualizacion) {
        this.usuActualizacion = usuActualizacion;
    }

    public String getUsuRegistro() {
        return usuRegistro;
    }

    public void setUsuRegistro(String usuRegistro) {
        this.usuRegistro = usuRegistro;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
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
        if (!(object instanceof Medidas)) {
            return false;
        }
        Medidas other = (Medidas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.Medidas[ id=" + id + " ]";
    }
    
}
