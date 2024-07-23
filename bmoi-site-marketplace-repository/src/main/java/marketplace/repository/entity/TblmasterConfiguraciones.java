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
@Table(name = "tblmaster_configuraciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblmasterConfiguraciones.findAll", query = "SELECT t FROM TblmasterConfiguraciones t"),
    @NamedQuery(name = "TblmasterConfiguraciones.findById", query = "SELECT t FROM TblmasterConfiguraciones t WHERE t.id = :id"),
    @NamedQuery(name = "TblmasterConfiguraciones.findByClave", query = "SELECT t FROM TblmasterConfiguraciones t WHERE t.clave = :clave"),
    @NamedQuery(name = "TblmasterConfiguraciones.findByValor", query = "SELECT t FROM TblmasterConfiguraciones t WHERE t.valor = :valor"),
    @NamedQuery(name = "TblmasterConfiguraciones.findByInterface1", query = "SELECT t FROM TblmasterConfiguraciones t WHERE t.interface1 = :interface1")})
public class TblmasterConfiguraciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "clave")
    private String clave;
    @Size(max = 2147483647)
    @Column(name = "valor")
    private String valor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "interface")
    private String interface1;

    public TblmasterConfiguraciones() {
    }

    public TblmasterConfiguraciones(Integer id) {
        this.id = id;
    }

    public TblmasterConfiguraciones(Integer id, String clave, String interface1) {
        this.id = id;
        this.clave = clave;
        this.interface1 = interface1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getInterface1() {
        return interface1;
    }

    public void setInterface1(String interface1) {
        this.interface1 = interface1;
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
        if (!(object instanceof TblmasterConfiguraciones)) {
            return false;
        }
        TblmasterConfiguraciones other = (TblmasterConfiguraciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.TblmasterConfiguraciones[ id=" + id + " ]";
    }
    
}
