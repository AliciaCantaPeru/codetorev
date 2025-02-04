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
@Table(name = "paises")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paises.findAll", query = "SELECT p FROM Paises p"),
    @NamedQuery(name = "Paises.findById", query = "SELECT p FROM Paises p WHERE p.id = :id"),
    @NamedQuery(name = "Paises.findByNombre", query = "SELECT p FROM Paises p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Paises.findByEstado", query = "SELECT p FROM Paises p WHERE p.estado = :estado"),
    @NamedQuery(name = "Paises.findByCodigo", query = "SELECT p FROM Paises p WHERE p.codigo = :codigo")})
public class Paises implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Size(max = 2147483647)
    @Column(name = "codigo")
    private String codigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpais", fetch = FetchType.LAZY)
    private Set<Departamentos> departamentosSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPais", fetch = FetchType.LAZY)
    private Set<Direcciones> direccionesSet;

    public Paises() {
    }

    public Paises(Integer id) {
        this.id = id;
    }

    public Paises(Integer id, String nombre, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @XmlTransient
    public Set<Departamentos> getDepartamentosSet() {
        return departamentosSet;
    }

    public void setDepartamentosSet(Set<Departamentos> departamentosSet) {
        this.departamentosSet = departamentosSet;
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
        if (!(object instanceof Paises)) {
            return false;
        }
        Paises other = (Paises) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.Paises[ id=" + id + " ]";
    }
    
}
