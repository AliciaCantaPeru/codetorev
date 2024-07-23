/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Martin Pilar
 */
@Entity
@Table(name = "dig_imgdisenio_grupos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DigImgdisenioGrupos.findAll", query = "SELECT d FROM DigImgdisenioGrupos d"),
    @NamedQuery(name = "DigImgdisenioGrupos.findById", query = "SELECT d FROM DigImgdisenioGrupos d WHERE d.id = :id"),
    @NamedQuery(name = "DigImgdisenioGrupos.findByNombre", query = "SELECT d FROM DigImgdisenioGrupos d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DigImgdisenioGrupos.findByDescripcion", query = "SELECT d FROM DigImgdisenioGrupos d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "DigImgdisenioGrupos.findByUrl", query = "SELECT d FROM DigImgdisenioGrupos d WHERE d.url = :url"),
    @NamedQuery(name = "DigImgdisenioGrupos.findByEstado", query = "SELECT d FROM DigImgdisenioGrupos d WHERE d.estado = :estado"),
    @NamedQuery(name = "DigImgdisenioGrupos.findByOrden", query = "SELECT d FROM DigImgdisenioGrupos d WHERE d.orden = :orden")})
public class DigImgdisenioGrupos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 150)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orden")
    private int orden;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMenu", fetch = FetchType.LAZY)
    private Set<DigImgdisenioCategorias> digImgdisenioCategoriasSet;

    public DigImgdisenioGrupos() {
    }

    public DigImgdisenioGrupos(Integer id) {
        this.id = id;
    }

    public DigImgdisenioGrupos(Integer id, String nombre, String url, int estado, int orden) {
        this.id = id;
        this.nombre = nombre;
        this.url = url;
        this.estado = estado;
        this.orden = orden;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    @XmlTransient
    public Set<DigImgdisenioCategorias> getDigImgdisenioCategoriasSet() {
        return digImgdisenioCategoriasSet;
    }

    public void setDigImgdisenioCategoriasSet(Set<DigImgdisenioCategorias> digImgdisenioCategoriasSet) {
        this.digImgdisenioCategoriasSet = digImgdisenioCategoriasSet;
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
        if (!(object instanceof DigImgdisenioGrupos)) {
            return false;
        }
        DigImgdisenioGrupos other = (DigImgdisenioGrupos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.DigImgdisenioGrupos[ id=" + id + " ]";
    }
    
}
