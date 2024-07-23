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
@Table(name = "categorias_grupomenu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriasGrupomenu.findAll", query = "SELECT c FROM CategoriasGrupomenu c"),
    @NamedQuery(name = "CategoriasGrupomenu.findById", query = "SELECT c FROM CategoriasGrupomenu c WHERE c.id = :id"),
    @NamedQuery(name = "CategoriasGrupomenu.findByOrden", query = "SELECT c FROM CategoriasGrupomenu c WHERE c.orden = :orden"),
    @NamedQuery(name = "CategoriasGrupomenu.findByEstado", query = "SELECT c FROM CategoriasGrupomenu c WHERE c.estado = :estado")})
public class CategoriasGrupomenu implements Serializable {

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
    @Column(name = "estado")
    private Integer estado;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Categorias idCategoria;
    @JoinColumn(name = "id_grupomenu", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GruposMenu idGrupomenu;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoriasgrupomenu", fetch = FetchType.LAZY)
    private Set<MaterialesCategoriagrupomenu> materialesCategoriagrupomenuSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoriaGrupomenu", fetch = FetchType.LAZY)
    private Set<ProductoCatgrupmenu> productoCatgrupmenuSet;

    public CategoriasGrupomenu() {
    }

    public CategoriasGrupomenu(Integer id) {
        this.id = id;
    }

    public CategoriasGrupomenu(Integer id, int orden) {
        this.id = id;
        this.orden = orden;
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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Categorias getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categorias idCategoria) {
        this.idCategoria = idCategoria;
    }

    public GruposMenu getIdGrupomenu() {
        return idGrupomenu;
    }

    public void setIdGrupomenu(GruposMenu idGrupomenu) {
        this.idGrupomenu = idGrupomenu;
    }

    @XmlTransient
    public Set<MaterialesCategoriagrupomenu> getMaterialesCategoriagrupomenuSet() {
        return materialesCategoriagrupomenuSet;
    }

    public void setMaterialesCategoriagrupomenuSet(Set<MaterialesCategoriagrupomenu> materialesCategoriagrupomenuSet) {
        this.materialesCategoriagrupomenuSet = materialesCategoriagrupomenuSet;
    }

    @XmlTransient
    public Set<ProductoCatgrupmenu> getProductoCatgrupmenuSet() {
        return productoCatgrupmenuSet;
    }

    public void setProductoCatgrupmenuSet(Set<ProductoCatgrupmenu> productoCatgrupmenuSet) {
        this.productoCatgrupmenuSet = productoCatgrupmenuSet;
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
        if (!(object instanceof CategoriasGrupomenu)) {
            return false;
        }
        CategoriasGrupomenu other = (CategoriasGrupomenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.CategoriasGrupomenu[ id=" + id + " ]";
    }
    
}
