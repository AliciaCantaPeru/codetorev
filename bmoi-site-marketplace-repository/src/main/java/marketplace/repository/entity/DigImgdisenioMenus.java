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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "dig_imgdisenio_menus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DigImgdisenioMenus.findAll", query = "SELECT d FROM DigImgdisenioMenus d"),
    @NamedQuery(name = "DigImgdisenioMenus.findById", query = "SELECT d FROM DigImgdisenioMenus d WHERE d.id = :id"),
    @NamedQuery(name = "DigImgdisenioMenus.findByNombre", query = "SELECT d FROM DigImgdisenioMenus d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DigImgdisenioMenus.findByDescripcion", query = "SELECT d FROM DigImgdisenioMenus d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "DigImgdisenioMenus.findByEstado", query = "SELECT d FROM DigImgdisenioMenus d WHERE d.estado = :estado")})
public class DigImgdisenioMenus implements Serializable {

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
    @Column(name = "estado")
    private int estado;

    public DigImgdisenioMenus() {
    }

    public DigImgdisenioMenus(Integer id) {
        this.id = id;
    }

    public DigImgdisenioMenus(Integer id, String nombre, int estado) {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        if (!(object instanceof DigImgdisenioMenus)) {
            return false;
        }
        DigImgdisenioMenus other = (DigImgdisenioMenus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.DigImgdisenioMenus[ id=" + id + " ]";
    }
    
}
