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
@Table(name = "dig_clipart_menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DigClipartMenu.findAll", query = "SELECT d FROM DigClipartMenu d"),
    @NamedQuery(name = "DigClipartMenu.findById", query = "SELECT d FROM DigClipartMenu d WHERE d.id = :id"),
    @NamedQuery(name = "DigClipartMenu.findByNombre", query = "SELECT d FROM DigClipartMenu d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DigClipartMenu.findByKeyword", query = "SELECT d FROM DigClipartMenu d WHERE d.keyword = :keyword"),
    @NamedQuery(name = "DigClipartMenu.findByRanking", query = "SELECT d FROM DigClipartMenu d WHERE d.ranking = :ranking"),
    @NamedQuery(name = "DigClipartMenu.findByEstado", query = "SELECT d FROM DigClipartMenu d WHERE d.estado = :estado")})
public class DigClipartMenu implements Serializable {

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
    @Column(name = "estado")
    private Integer estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClipartmenu", fetch = FetchType.LAZY)
    private Set<DigClipartCategoria> digClipartCategoriaSet;

    public DigClipartMenu() {
    }

    public DigClipartMenu(Integer id) {
        this.id = id;
    }

    public DigClipartMenu(Integer id, int ranking) {
        this.id = id;
        this.ranking = ranking;
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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Set<DigClipartCategoria> getDigClipartCategoriaSet() {
        return digClipartCategoriaSet;
    }

    public void setDigClipartCategoriaSet(Set<DigClipartCategoria> digClipartCategoriaSet) {
        this.digClipartCategoriaSet = digClipartCategoriaSet;
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
        if (!(object instanceof DigClipartMenu)) {
            return false;
        }
        DigClipartMenu other = (DigClipartMenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.DigClipartMenu[ id=" + id + " ]";
    }
    
}
