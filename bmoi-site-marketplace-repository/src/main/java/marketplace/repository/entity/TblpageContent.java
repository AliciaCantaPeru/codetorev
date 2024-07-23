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
@Table(name = "tblpage_content")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblpageContent.findAll", query = "SELECT t FROM TblpageContent t"),
    @NamedQuery(name = "TblpageContent.findById", query = "SELECT t FROM TblpageContent t WHERE t.id = :id"),
    @NamedQuery(name = "TblpageContent.findByInterface1", query = "SELECT t FROM TblpageContent t WHERE t.interface1 = :interface1"),
    @NamedQuery(name = "TblpageContent.findByPagina", query = "SELECT t FROM TblpageContent t WHERE t.pagina = :pagina"),
    @NamedQuery(name = "TblpageContent.findBySeccion", query = "SELECT t FROM TblpageContent t WHERE t.seccion = :seccion"),
    @NamedQuery(name = "TblpageContent.findByBloque", query = "SELECT t FROM TblpageContent t WHERE t.bloque = :bloque"),
    @NamedQuery(name = "TblpageContent.findByPost", query = "SELECT t FROM TblpageContent t WHERE t.post = :post"),
    @NamedQuery(name = "TblpageContent.findByContenido", query = "SELECT t FROM TblpageContent t WHERE t.contenido = :contenido"),
    @NamedQuery(name = "TblpageContent.findByHtml", query = "SELECT t FROM TblpageContent t WHERE t.html = :html"),
    @NamedQuery(name = "TblpageContent.findByOrden", query = "SELECT t FROM TblpageContent t WHERE t.orden = :orden"),
    @NamedQuery(name = "TblpageContent.findByCodIdioma", query = "SELECT t FROM TblpageContent t WHERE t.codIdioma = :codIdioma"),
    @NamedQuery(name = "TblpageContent.findByFecVencimiento", query = "SELECT t FROM TblpageContent t WHERE t.fecVencimiento = :fecVencimiento"),
    @NamedQuery(name = "TblpageContent.findByF1", query = "SELECT t FROM TblpageContent t WHERE t.f1 = :f1"),
    @NamedQuery(name = "TblpageContent.findByF2", query = "SELECT t FROM TblpageContent t WHERE t.f2 = :f2"),
    @NamedQuery(name = "TblpageContent.findByF3", query = "SELECT t FROM TblpageContent t WHERE t.f3 = :f3"),
    @NamedQuery(name = "TblpageContent.findByF4", query = "SELECT t FROM TblpageContent t WHERE t.f4 = :f4"),
    @NamedQuery(name = "TblpageContent.findByF5", query = "SELECT t FROM TblpageContent t WHERE t.f5 = :f5"),
    @NamedQuery(name = "TblpageContent.findByUsuRegistro", query = "SELECT t FROM TblpageContent t WHERE t.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "TblpageContent.findByFecRegistro", query = "SELECT t FROM TblpageContent t WHERE t.fecRegistro = :fecRegistro"),
    @NamedQuery(name = "TblpageContent.findByUsuActualizacion", query = "SELECT t FROM TblpageContent t WHERE t.usuActualizacion = :usuActualizacion"),
    @NamedQuery(name = "TblpageContent.findByFecActualizacion", query = "SELECT t FROM TblpageContent t WHERE t.fecActualizacion = :fecActualizacion"),
    @NamedQuery(name = "TblpageContent.findByEstado", query = "SELECT t FROM TblpageContent t WHERE t.estado = :estado")})
public class TblpageContent implements Serializable {

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
    @Column(name = "seccion")
    private String seccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "bloque")
    private String bloque;
    @Size(max = 150)
    @Column(name = "post")
    private String post;
    @Size(max = 255)
    @Column(name = "contenido")
    private String contenido;
    @Size(max = 2147483647)
    @Column(name = "html")
    private String html;
    @Column(name = "orden")
    private Integer orden;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "cod_idioma")
    private String codIdioma;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_vencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecVencimiento;
    @Size(max = 3)
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

    public TblpageContent() {
    }

    public TblpageContent(Integer id) {
        this.id = id;
    }

    public TblpageContent(Integer id, String interface1, String pagina, String seccion, String bloque, String codIdioma, Date fecVencimiento, String usuRegistro, Date fecRegistro, int estado) {
        this.id = id;
        this.interface1 = interface1;
        this.pagina = pagina;
        this.seccion = seccion;
        this.bloque = bloque;
        this.codIdioma = codIdioma;
        this.fecVencimiento = fecVencimiento;
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

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getBloque() {
        return bloque;
    }

    public void setBloque(String bloque) {
        this.bloque = bloque;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getCodIdioma() {
        return codIdioma;
    }

    public void setCodIdioma(String codIdioma) {
        this.codIdioma = codIdioma;
    }

    public Date getFecVencimiento() {
        return fecVencimiento;
    }

    public void setFecVencimiento(Date fecVencimiento) {
        this.fecVencimiento = fecVencimiento;
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
        if (!(object instanceof TblpageContent)) {
            return false;
        }
        TblpageContent other = (TblpageContent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.TblpageContent[ id=" + id + " ]";
    }
    
}
