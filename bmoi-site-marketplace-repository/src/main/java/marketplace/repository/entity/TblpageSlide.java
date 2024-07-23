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
@Table(name = "tblpage_slide")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblpageSlide.findAll", query = "SELECT t FROM TblpageSlide t"),
    @NamedQuery(name = "TblpageSlide.findById", query = "SELECT t FROM TblpageSlide t WHERE t.id = :id"),
    @NamedQuery(name = "TblpageSlide.findByInterface1", query = "SELECT t FROM TblpageSlide t WHERE t.interface1 = :interface1"),
    @NamedQuery(name = "TblpageSlide.findByPagina", query = "SELECT t FROM TblpageSlide t WHERE t.pagina = :pagina"),
    @NamedQuery(name = "TblpageSlide.findByBloque", query = "SELECT t FROM TblpageSlide t WHERE t.bloque = :bloque"),
    @NamedQuery(name = "TblpageSlide.findBySeccion", query = "SELECT t FROM TblpageSlide t WHERE t.seccion = :seccion"),
    @NamedQuery(name = "TblpageSlide.findByUrlpath", query = "SELECT t FROM TblpageSlide t WHERE t.urlpath = :urlpath"),
    @NamedQuery(name = "TblpageSlide.findByTitulo1", query = "SELECT t FROM TblpageSlide t WHERE t.titulo1 = :titulo1"),
    @NamedQuery(name = "TblpageSlide.findByTitulo2", query = "SELECT t FROM TblpageSlide t WHERE t.titulo2 = :titulo2"),
    @NamedQuery(name = "TblpageSlide.findByTitulo3", query = "SELECT t FROM TblpageSlide t WHERE t.titulo3 = :titulo3"),
    @NamedQuery(name = "TblpageSlide.findByFecVencimiento", query = "SELECT t FROM TblpageSlide t WHERE t.fecVencimiento = :fecVencimiento"),
    @NamedQuery(name = "TblpageSlide.findByOrden", query = "SELECT t FROM TblpageSlide t WHERE t.orden = :orden"),
    @NamedQuery(name = "TblpageSlide.findByF1", query = "SELECT t FROM TblpageSlide t WHERE t.f1 = :f1"),
    @NamedQuery(name = "TblpageSlide.findByF2", query = "SELECT t FROM TblpageSlide t WHERE t.f2 = :f2"),
    @NamedQuery(name = "TblpageSlide.findByF3", query = "SELECT t FROM TblpageSlide t WHERE t.f3 = :f3"),
    @NamedQuery(name = "TblpageSlide.findByUsuRegistro", query = "SELECT t FROM TblpageSlide t WHERE t.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "TblpageSlide.findByFecRegistro", query = "SELECT t FROM TblpageSlide t WHERE t.fecRegistro = :fecRegistro"),
    @NamedQuery(name = "TblpageSlide.findByUsuActualizacion", query = "SELECT t FROM TblpageSlide t WHERE t.usuActualizacion = :usuActualizacion"),
    @NamedQuery(name = "TblpageSlide.findByFecActualizacion", query = "SELECT t FROM TblpageSlide t WHERE t.fecActualizacion = :fecActualizacion"),
    @NamedQuery(name = "TblpageSlide.findByEstado", query = "SELECT t FROM TblpageSlide t WHERE t.estado = :estado")})
public class TblpageSlide implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "interface")
    private String interface1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "pagina")
    private String pagina;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "bloque")
    private String bloque;
    @Size(max = 50)
    @Column(name = "seccion")
    private String seccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "urlpath")
    private String urlpath;
    @Size(max = 400)
    @Column(name = "titulo1")
    private String titulo1;
    @Size(max = 255)
    @Column(name = "titulo2")
    private String titulo2;
    @Size(max = 255)
    @Column(name = "titulo3")
    private String titulo3;
    @Column(name = "fec_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fecVencimiento;
    @Column(name = "orden")
    private Integer orden;
    @Size(max = 1)
    @Column(name = "f1")
    private String f1;
    @Size(max = 1)
    @Column(name = "f2")
    private String f2;
    @Size(max = 1)
    @Column(name = "f3")
    private String f3;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usu_registro")
    private String usuRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecRegistro;
    @Size(max = 50)
    @Column(name = "usu_actualizacion")
    private String usuActualizacion;
    @Column(name = "fec_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecActualizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;

    public TblpageSlide() {
    }

    public TblpageSlide(Integer id) {
        this.id = id;
    }

    public TblpageSlide(Integer id, String interface1, String pagina, String bloque, String urlpath, String usuRegistro, Date fecRegistro, int estado) {
        this.id = id;
        this.interface1 = interface1;
        this.pagina = pagina;
        this.bloque = bloque;
        this.urlpath = urlpath;
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

    public String getInterface1() {
        return interface1;
    }

    public void setInterface1(String interface1) {
        this.interface1 = interface1;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public String getBloque() {
        return bloque;
    }

    public void setBloque(String bloque) {
        this.bloque = bloque;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getUrlpath() {
        return urlpath;
    }

    public void setUrlpath(String urlpath) {
        this.urlpath = urlpath;
    }

    public String getTitulo1() {
        return titulo1;
    }

    public void setTitulo1(String titulo1) {
        this.titulo1 = titulo1;
    }

    public String getTitulo2() {
        return titulo2;
    }

    public void setTitulo2(String titulo2) {
        this.titulo2 = titulo2;
    }

    public String getTitulo3() {
        return titulo3;
    }

    public void setTitulo3(String titulo3) {
        this.titulo3 = titulo3;
    }

    public Date getFecVencimiento() {
        return fecVencimiento;
    }

    public void setFecVencimiento(Date fecVencimiento) {
        this.fecVencimiento = fecVencimiento;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
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
        if (!(object instanceof TblpageSlide)) {
            return false;
        }
        TblpageSlide other = (TblpageSlide) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.TblpageSlide[ id=" + id + " ]";
    }
    
}
