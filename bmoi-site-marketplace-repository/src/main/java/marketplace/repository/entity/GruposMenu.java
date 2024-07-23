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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Martin Pilar
 */
@Entity
@Table(name = "grupos_menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GruposMenu.findAll", query = "SELECT g FROM GruposMenu g"),
    @NamedQuery(name = "GruposMenu.findById", query = "SELECT g FROM GruposMenu g WHERE g.id = :id"),
    @NamedQuery(name = "GruposMenu.findByOrden", query = "SELECT g FROM GruposMenu g WHERE g.orden = :orden"),
    @NamedQuery(name = "GruposMenu.findByEstado", query = "SELECT g FROM GruposMenu g WHERE g.estado = :estado")})
public class GruposMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orden")
    private int orden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupomenu", fetch = FetchType.LAZY)
    private Set<CategoriasGrupomenu> categoriasGrupomenuSet;
    @JoinColumn(name = "id_grupo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Grupos idGrupo;
    @JoinColumn(name = "cod_menu", referencedColumnName = "codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Menus codMenu;

    public GruposMenu() {
    }

    public GruposMenu(Integer id) {
        this.id = id;
    }

    public GruposMenu(Integer id, int orden, int estado) {
        this.id = id;
        this.orden = orden;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Set<CategoriasGrupomenu> getCategoriasGrupomenuSet() {
        return categoriasGrupomenuSet;
    }

    public void setCategoriasGrupomenuSet(Set<CategoriasGrupomenu> categoriasGrupomenuSet) {
        this.categoriasGrupomenuSet = categoriasGrupomenuSet;
    }

    public Grupos getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Grupos idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Menus getCodMenu() {
        return codMenu;
    }

    public void setCodMenu(Menus codMenu) {
        this.codMenu = codMenu;
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
        if (!(object instanceof GruposMenu)) {
            return false;
        }
        GruposMenu other = (GruposMenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.GruposMenu[ id=" + id + " ]";
    }
    
}
