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
@Table(name = "tblmaster_parametros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblmasterParametros.findAll", query = "SELECT t FROM TblmasterParametros t"),
    @NamedQuery(name = "TblmasterParametros.findById", query = "SELECT t FROM TblmasterParametros t WHERE t.id = :id"),
    @NamedQuery(name = "TblmasterParametros.findByClave", query = "SELECT t FROM TblmasterParametros t WHERE t.clave = :clave"),
    @NamedQuery(name = "TblmasterParametros.findByTipo", query = "SELECT t FROM TblmasterParametros t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "TblmasterParametros.findByValor", query = "SELECT t FROM TblmasterParametros t WHERE t.valor = :valor"),
    @NamedQuery(name = "TblmasterParametros.findByInterface1", query = "SELECT t FROM TblmasterParametros t WHERE t.interface1 = :interface1"),
    @NamedQuery(name = "TblmasterParametros.findByDescripcion", query = "SELECT t FROM TblmasterParametros t WHERE t.descripcion = :descripcion")})
public class TblmasterParametros implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 255)
    @Column(name = "valor")
    private String valor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "interface")
    private String interface1;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;

    public TblmasterParametros() {
    }

    public TblmasterParametros(Integer id) {
        this.id = id;
    }

    public TblmasterParametros(Integer id, String clave, String tipo, String interface1) {
        this.id = id;
        this.clave = clave;
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        if (!(object instanceof TblmasterParametros)) {
            return false;
        }
        TblmasterParametros other = (TblmasterParametros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.TblmasterParametros[ id=" + id + " ]";
    }
    
}
