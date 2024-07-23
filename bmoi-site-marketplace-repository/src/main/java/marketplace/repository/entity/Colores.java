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
@Table(name = "colores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Colores.findAll", query = "SELECT c FROM Colores c"),
    @NamedQuery(name = "Colores.findById", query = "SELECT c FROM Colores c WHERE c.id = :id"),
    @NamedQuery(name = "Colores.findByCodigo", query = "SELECT c FROM Colores c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Colores.findByValHexadecimal", query = "SELECT c FROM Colores c WHERE c.valHexadecimal = :valHexadecimal"),
    @NamedQuery(name = "Colores.findByDescripcion", query = "SELECT c FROM Colores c WHERE c.descripcion = :descripcion")})
public class Colores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 15)
    @Column(name = "val_hexadecimal")
    private String valHexadecimal;
    @Size(max = 250)
    @Column(name = "descripcion")
    private String descripcion;

    public Colores() {
    }

    public Colores(Integer id) {
        this.id = id;
    }

    public Colores(Integer id, String codigo) {
        this.id = id;
        this.codigo = codigo;
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

    public String getValHexadecimal() {
        return valHexadecimal;
    }

    public void setValHexadecimal(String valHexadecimal) {
        this.valHexadecimal = valHexadecimal;
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
        if (!(object instanceof Colores)) {
            return false;
        }
        Colores other = (Colores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.Colores[ id=" + id + " ]";
    }
    
}
