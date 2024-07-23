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
@Table(name = "tblmaster_tablas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblmasterTablas.findAll", query = "SELECT t FROM TblmasterTablas t"),
    @NamedQuery(name = "TblmasterTablas.findById", query = "SELECT t FROM TblmasterTablas t WHERE t.id = :id"),
    @NamedQuery(name = "TblmasterTablas.findByNombre", query = "SELECT t FROM TblmasterTablas t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TblmasterTablas.findByDescripcion", query = "SELECT t FROM TblmasterTablas t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TblmasterTablas.findByNroCampos", query = "SELECT t FROM TblmasterTablas t WHERE t.nroCampos = :nroCampos"),
    @NamedQuery(name = "TblmasterTablas.findByCarga", query = "SELECT t FROM TblmasterTablas t WHERE t.carga = :carga"),
    @NamedQuery(name = "TblmasterTablas.findByAudita", query = "SELECT t FROM TblmasterTablas t WHERE t.audita = :audita"),
    @NamedQuery(name = "TblmasterTablas.findByNegocio", query = "SELECT t FROM TblmasterTablas t WHERE t.negocio = :negocio"),
    @NamedQuery(name = "TblmasterTablas.findByTipo", query = "SELECT t FROM TblmasterTablas t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "TblmasterTablas.findByDetalles", query = "SELECT t FROM TblmasterTablas t WHERE t.detalles = :detalles"),
    @NamedQuery(name = "TblmasterTablas.findByAdicional1", query = "SELECT t FROM TblmasterTablas t WHERE t.adicional1 = :adicional1"),
    @NamedQuery(name = "TblmasterTablas.findByAdicional2", query = "SELECT t FROM TblmasterTablas t WHERE t.adicional2 = :adicional2"),
    @NamedQuery(name = "TblmasterTablas.findByUsuRegistro", query = "SELECT t FROM TblmasterTablas t WHERE t.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "TblmasterTablas.findByFecRegistro", query = "SELECT t FROM TblmasterTablas t WHERE t.fecRegistro = :fecRegistro"),
    @NamedQuery(name = "TblmasterTablas.findByUsuActualizacion", query = "SELECT t FROM TblmasterTablas t WHERE t.usuActualizacion = :usuActualizacion"),
    @NamedQuery(name = "TblmasterTablas.findByFecActualizacion", query = "SELECT t FROM TblmasterTablas t WHERE t.fecActualizacion = :fecActualizacion"),
    @NamedQuery(name = "TblmasterTablas.findByEstado", query = "SELECT t FROM TblmasterTablas t WHERE t.estado = :estado")})
public class TblmasterTablas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nro_campos")
    private int nroCampos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "carga")
    private String carga;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "audita")
    private String audita;
    @Size(max = 50)
    @Column(name = "negocio")
    private String negocio;
    @Size(max = 50)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "detalles")
    private String detalles;
    @Size(max = 50)
    @Column(name = "adicional1")
    private String adicional1;
    @Size(max = 50)
    @Column(name = "adicional2")
    private String adicional2;
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
    @Column(name = "estado")
    private Integer estado;

    public TblmasterTablas() {
    }

    public TblmasterTablas(Integer id) {
        this.id = id;
    }

    public TblmasterTablas(Integer id, String nombre, int nroCampos, String carga, String audita, String detalles, String usuRegistro, Date fecRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.nroCampos = nroCampos;
        this.carga = carga;
        this.audita = audita;
        this.detalles = detalles;
        this.usuRegistro = usuRegistro;
        this.fecRegistro = fecRegistro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNroCampos() {
        return nroCampos;
    }

    public void setNroCampos(int nroCampos) {
        this.nroCampos = nroCampos;
    }

    public String getCarga() {
        return carga;
    }

    public void setCarga(String carga) {
        this.carga = carga;
    }

    public String getAudita() {
        return audita;
    }

    public void setAudita(String audita) {
        this.audita = audita;
    }

    public String getNegocio() {
        return negocio;
    }

    public void setNegocio(String negocio) {
        this.negocio = negocio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getAdicional1() {
        return adicional1;
    }

    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    public String getAdicional2() {
        return adicional2;
    }

    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
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
        if (!(object instanceof TblmasterTablas)) {
            return false;
        }
        TblmasterTablas other = (TblmasterTablas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.TblmasterTablas[ id=" + id + " ]";
    }
    
}
