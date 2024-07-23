/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Martin Pilar
 */
@Entity
@Table(name = "dig_clipart_categoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DigClipartCategoria.findAll", query = "SELECT d FROM DigClipartCategoria d"),
    @NamedQuery(name = "DigClipartCategoria.findById", query = "SELECT d FROM DigClipartCategoria d WHERE d.id = :id"),
    @NamedQuery(name = "DigClipartCategoria.findByNombre", query = "SELECT d FROM DigClipartCategoria d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DigClipartCategoria.findByKeyword", query = "SELECT d FROM DigClipartCategoria d WHERE d.keyword = :keyword"),
    @NamedQuery(name = "DigClipartCategoria.findByRanking", query = "SELECT d FROM DigClipartCategoria d WHERE d.ranking = :ranking"),
    @NamedQuery(name = "DigClipartCategoria.findByUrlimg", query = "SELECT d FROM DigClipartCategoria d WHERE d.urlimg = :urlimg"),
    @NamedQuery(name = "DigClipartCategoria.findByEstado", query = "SELECT d FROM DigClipartCategoria d WHERE d.estado = :estado")})
public class DigClipartCategoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 50)
    @Column(name = "keyword")
    private String keyword;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ranking")
    private int ranking;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "urlimg")
    private String urlimg;
    @Column(name = "estado")
    private Integer estado;
    @JoinColumn(name = "id_clipartmenu", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DigClipartMenu idClipartmenu;

    public DigClipartCategoria() {
    }

    public DigClipartCategoria(Integer id) {
        this.id = id;
    }

    public DigClipartCategoria(Integer id, int ranking, String urlimg) {
        this.id = id;
        this.ranking = ranking;
        this.urlimg = urlimg;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getUrlimg() {
        return urlimg;
    }

    public void setUrlimg(String urlimg) {
        this.urlimg = urlimg;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public DigClipartMenu getIdClipartmenu() {
        return idClipartmenu;
    }

    public void setIdClipartmenu(DigClipartMenu idClipartmenu) {
        this.idClipartmenu = idClipartmenu;
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
        if (!(object instanceof DigClipartCategoria)) {
            return false;
        }
        DigClipartCategoria other = (DigClipartCategoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.DigClipartCategoria[ id=" + id + " ]";
    }
    
}
