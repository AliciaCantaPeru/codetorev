/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Martin Pilar
 */
@Entity
@Table(name = "producto_dimensiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoDimensiones.findAll", query = "SELECT p FROM ProductoDimensiones p"),
    @NamedQuery(name = "ProductoDimensiones.findById", query = "SELECT p FROM ProductoDimensiones p WHERE p.id = :id"),
    @NamedQuery(name = "ProductoDimensiones.findByPeso", query = "SELECT p FROM ProductoDimensiones p WHERE p.peso = :peso"),
    @NamedQuery(name = "ProductoDimensiones.findByAltura", query = "SELECT p FROM ProductoDimensiones p WHERE p.altura = :altura"),
    @NamedQuery(name = "ProductoDimensiones.findByAnchura", query = "SELECT p FROM ProductoDimensiones p WHERE p.anchura = :anchura"),
    @NamedQuery(name = "ProductoDimensiones.findByProfundida", query = "SELECT p FROM ProductoDimensiones p WHERE p.profundida = :profundida")})
public class ProductoDimensiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "peso")
    private double peso;
    @Column(name = "altura")
    private BigInteger altura;
    @Column(name = "anchura")
    private BigInteger anchura;
    @Column(name = "profundida")
    private BigInteger profundida;
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Productos idProducto;

    public ProductoDimensiones() {
    }

    public ProductoDimensiones(Integer id) {
        this.id = id;
    }

    public ProductoDimensiones(Integer id, double peso) {
        this.id = id;
        this.peso = peso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public BigInteger getAltura() {
        return altura;
    }

    public void setAltura(BigInteger altura) {
        this.altura = altura;
    }

    public BigInteger getAnchura() {
        return anchura;
    }

    public void setAnchura(BigInteger anchura) {
        this.anchura = anchura;
    }

    public BigInteger getProfundida() {
        return profundida;
    }

    public void setProfundida(BigInteger profundida) {
        this.profundida = profundida;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
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
        if (!(object instanceof ProductoDimensiones)) {
            return false;
        }
        ProductoDimensiones other = (ProductoDimensiones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.ProductoDimensiones[ id=" + id + " ]";
    }
    
}
