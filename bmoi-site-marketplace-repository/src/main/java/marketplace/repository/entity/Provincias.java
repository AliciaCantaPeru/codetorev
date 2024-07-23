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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Martin Pilar
 */
@Entity
@Table(name = "provincias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Provincias.findAll", query = "SELECT p FROM Provincias p"),
    @NamedQuery(name = "Provincias.findById", query = "SELECT p FROM Provincias p WHERE p.id = :id"),
    @NamedQuery(name = "Provincias.findByNomProvincia", query = "SELECT p FROM Provincias p WHERE p.nomProvincia = :nomProvincia")})
public class Provincias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nom_provincia")
    private String nomProvincia;
    @JoinColumn(name = "iddepartamento", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Departamentos iddepartamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idprovincia", fetch = FetchType.LAZY)
    private Set<Distritos> distritosSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProvincia", fetch = FetchType.LAZY)
    private Set<Direcciones> direccionesSet;

    public Provincias() {
    }

    public Provincias(Integer id) {
        this.id = id;
    }

    public Provincias(Integer id, String nomProvincia) {
        this.id = id;
        this.nomProvincia = nomProvincia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomProvincia() {
        return nomProvincia;
    }

    public void setNomProvincia(String nomProvincia) {
        this.nomProvincia = nomProvincia;
    }

    public Departamentos getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(Departamentos iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    @XmlTransient
    public Set<Distritos> getDistritosSet() {
        return distritosSet;
    }

    public void setDistritosSet(Set<Distritos> distritosSet) {
        this.distritosSet = distritosSet;
    }

    @XmlTransient
    public Set<Direcciones> getDireccionesSet() {
        return direccionesSet;
    }

    public void setDireccionesSet(Set<Direcciones> direccionesSet) {
        this.direccionesSet = direccionesSet;
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
        if (!(object instanceof Provincias)) {
            return false;
        }
        Provincias other = (Provincias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.Provincias[ id=" + id + " ]";
    }
    
}
