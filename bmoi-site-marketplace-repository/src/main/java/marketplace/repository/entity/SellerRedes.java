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
@Table(name = "seller_redes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SellerRedes.findAll", query = "SELECT s FROM SellerRedes s"),
    @NamedQuery(name = "SellerRedes.findById", query = "SELECT s FROM SellerRedes s WHERE s.id = :id"),
    @NamedQuery(name = "SellerRedes.findByNombre", query = "SELECT s FROM SellerRedes s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "SellerRedes.findByRed", query = "SELECT s FROM SellerRedes s WHERE s.red = :red"),
    @NamedQuery(name = "SellerRedes.findByEstado", query = "SELECT s FROM SellerRedes s WHERE s.estado = :estado")})
public class SellerRedes implements Serializable {

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
    @Size(min = 1, max = 255)
    @Column(name = "red")
    private String red;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @JoinColumn(name = "id_seller", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sellers idSeller;

    public SellerRedes() {
    }

    public SellerRedes(Integer id) {
        this.id = id;
    }

    public SellerRedes(Integer id, String nombre, String red, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.red = red;
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

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Sellers getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(Sellers idSeller) {
        this.idSeller = idSeller;
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
        if (!(object instanceof SellerRedes)) {
            return false;
        }
        SellerRedes other = (SellerRedes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.SellerRedes[ id=" + id + " ]";
    }
    
}
