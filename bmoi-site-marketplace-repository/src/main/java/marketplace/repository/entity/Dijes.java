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
@Table(name = "dijes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dijes.findAll", query = "SELECT d FROM Dijes d"),
    @NamedQuery(name = "Dijes.findById", query = "SELECT d FROM Dijes d WHERE d.id = :id"),
    @NamedQuery(name = "Dijes.findByCodigo", query = "SELECT d FROM Dijes d WHERE d.codigo = :codigo"),
    @NamedQuery(name = "Dijes.findByDescripcion", query = "SELECT d FROM Dijes d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "Dijes.findByFoto", query = "SELECT d FROM Dijes d WHERE d.foto = :foto"),
    @NamedQuery(name = "Dijes.findByColor", query = "SELECT d FROM Dijes d WHERE d.color = :color"),
    @NamedQuery(name = "Dijes.findByEstado", query = "SELECT d FROM Dijes d WHERE d.estado = :estado")})
public class Dijes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 2147483647)
    @Column(name = "foto")
    private String foto;
    @Size(max = 50)
    @Column(name = "color")
    private String color;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Categorias idCategoria;
    @JoinColumn(name = "id_material", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Materiales idMaterial;

    public Dijes() {
    }

    public Dijes(Integer id) {
        this.id = id;
    }

    public Dijes(Integer id, String codigo, String descripcion, int estado) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Categorias getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categorias idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Materiales getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Materiales idMaterial) {
        this.idMaterial = idMaterial;
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
        if (!(object instanceof Dijes)) {
            return false;
        }
        Dijes other = (Dijes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.Dijes[ id=" + id + " ]";
    }
    
}
