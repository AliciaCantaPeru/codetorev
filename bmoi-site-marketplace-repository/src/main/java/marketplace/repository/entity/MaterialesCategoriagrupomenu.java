/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity;

import java.io.Serializable;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Martin Pilar
 */
@Entity
@Table(name = "materiales_categoriagrupomenu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaterialesCategoriagrupomenu.findAll", query = "SELECT m FROM MaterialesCategoriagrupomenu m"),
    @NamedQuery(name = "MaterialesCategoriagrupomenu.findById", query = "SELECT m FROM MaterialesCategoriagrupomenu m WHERE m.id = :id")})
public class MaterialesCategoriagrupomenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_categoriasgrupomenu", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CategoriasGrupomenu idCategoriasgrupomenu;
    @JoinColumn(name = "id_material", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Materiales idMaterial;
    @OneToMany(mappedBy = "idMaterialCategoriagrupomenu", fetch = FetchType.LAZY)
    private Set<Productos> productosSet;

    public MaterialesCategoriagrupomenu() {
    }

    public MaterialesCategoriagrupomenu(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CategoriasGrupomenu getIdCategoriasgrupomenu() {
        return idCategoriasgrupomenu;
    }

    public void setIdCategoriasgrupomenu(CategoriasGrupomenu idCategoriasgrupomenu) {
        this.idCategoriasgrupomenu = idCategoriasgrupomenu;
    }

    public Materiales getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Materiales idMaterial) {
        this.idMaterial = idMaterial;
    }

    @XmlTransient
    public Set<Productos> getProductosSet() {
        return productosSet;
    }

    public void setProductosSet(Set<Productos> productosSet) {
        this.productosSet = productosSet;
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
        if (!(object instanceof MaterialesCategoriagrupomenu)) {
            return false;
        }
        MaterialesCategoriagrupomenu other = (MaterialesCategoriagrupomenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.MaterialesCategoriagrupomenu[ id=" + id + " ]";
    }
    
}
