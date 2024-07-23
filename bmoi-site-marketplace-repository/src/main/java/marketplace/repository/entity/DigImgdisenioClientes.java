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
@Table(name = "dig_imgdisenio_clientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DigImgdisenioClientes.findAll", query = "SELECT d FROM DigImgdisenioClientes d"),
    @NamedQuery(name = "DigImgdisenioClientes.findById", query = "SELECT d FROM DigImgdisenioClientes d WHERE d.id = :id"),
    @NamedQuery(name = "DigImgdisenioClientes.findByUrlImg", query = "SELECT d FROM DigImgdisenioClientes d WHERE d.urlImg = :urlImg")})
public class DigImgdisenioClientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "url_img")
    private String urlImg;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Clientes idCliente;

    public DigImgdisenioClientes() {
    }

    public DigImgdisenioClientes(Integer id) {
        this.id = id;
    }

    public DigImgdisenioClientes(Integer id, String urlImg) {
        this.id = id;
        this.urlImg = urlImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public Clientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Clientes idCliente) {
        this.idCliente = idCliente;
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
        if (!(object instanceof DigImgdisenioClientes)) {
            return false;
        }
        DigImgdisenioClientes other = (DigImgdisenioClientes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.DigImgdisenioClientes[ id=" + id + " ]";
    }
    
}
