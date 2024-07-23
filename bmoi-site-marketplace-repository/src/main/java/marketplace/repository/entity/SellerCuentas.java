/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Martin Pilar
 */
@Entity
@Table(name = "seller_cuentas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SellerCuentas.findAll", query = "SELECT s FROM SellerCuentas s"),
    @NamedQuery(name = "SellerCuentas.findByContrasenia", query = "SELECT s FROM SellerCuentas s WHERE s.contrasenia = :contrasenia"),
    @NamedQuery(name = "SellerCuentas.findByKeyNewContrasenia", query = "SELECT s FROM SellerCuentas s WHERE s.keyNewContrasenia = :keyNewContrasenia"),
    @NamedQuery(name = "SellerCuentas.findByFecRegistro", query = "SELECT s FROM SellerCuentas s WHERE s.fecRegistro = :fecRegistro"),
    @NamedQuery(name = "SellerCuentas.findByEstado", query = "SELECT s FROM SellerCuentas s WHERE s.estado = :estado"),
    @NamedQuery(name = "SellerCuentas.findById", query = "SELECT s FROM SellerCuentas s WHERE s.id = :id")})
public class SellerCuentas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "contrasenia")
    private String contrasenia;
    @Size(max = 2147483647)
    @Column(name = "key_new_contrasenia")
    private String keyNewContrasenia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "id_rol", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Roles idRol;
    @JoinColumn(name = "id_sellerpersona", referencedColumnName = "id")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private SellerPersonas idSellerpersona;

    public SellerCuentas() {
    }

    public SellerCuentas(Long id) {
        this.id = id;
    }

    public SellerCuentas(Long id, String contrasenia, Date fecRegistro, int estado) {
        this.id = id;
        this.contrasenia = contrasenia;
        this.fecRegistro = fecRegistro;
        this.estado = estado;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getKeyNewContrasenia() {
        return keyNewContrasenia;
    }

    public void setKeyNewContrasenia(String keyNewContrasenia) {
        this.keyNewContrasenia = keyNewContrasenia;
    }

    public Date getFecRegistro() {
        return fecRegistro;
    }

    public void setFecRegistro(Date fecRegistro) {
        this.fecRegistro = fecRegistro;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Roles getIdRol() {
        return idRol;
    }

    public void setIdRol(Roles idRol) {
        this.idRol = idRol;
    }

    public SellerPersonas getIdSellerpersona() {
        return idSellerpersona;
    }

    public void setIdSellerpersona(SellerPersonas idSellerpersona) {
        this.idSellerpersona = idSellerpersona;
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
        if (!(object instanceof SellerCuentas)) {
            return false;
        }
        SellerCuentas other = (SellerCuentas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.SellerCuentas[ id=" + id + " ]";
    }
    
}
