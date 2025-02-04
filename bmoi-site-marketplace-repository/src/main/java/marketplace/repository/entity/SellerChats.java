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
@Table(name = "seller_chats")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SellerChats.findAll", query = "SELECT s FROM SellerChats s"),
    @NamedQuery(name = "SellerChats.findById", query = "SELECT s FROM SellerChats s WHERE s.id = :id"),
    @NamedQuery(name = "SellerChats.findByNombre", query = "SELECT s FROM SellerChats s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "SellerChats.findByNumero", query = "SELECT s FROM SellerChats s WHERE s.numero = :numero")})
public class SellerChats implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "numero")
    private String numero;
    @JoinColumn(name = "id_seller", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sellers idSeller;

    public SellerChats() {
    }

    public SellerChats(Integer id) {
        this.id = id;
    }

    public SellerChats(Integer id, String nombre, String numero) {
        this.id = id;
        this.nombre = nombre;
        this.numero = numero;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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
        if (!(object instanceof SellerChats)) {
            return false;
        }
        SellerChats other = (SellerChats) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.SellerChats[ id=" + id + " ]";
    }
    
}
