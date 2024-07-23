/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "seller_personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SellerPersonas.findAll", query = "SELECT s FROM SellerPersonas s"),
    @NamedQuery(name = "SellerPersonas.findById", query = "SELECT s FROM SellerPersonas s WHERE s.id = :id"),
    @NamedQuery(name = "SellerPersonas.findByPriNombre", query = "SELECT s FROM SellerPersonas s WHERE s.priNombre = :priNombre"),
    @NamedQuery(name = "SellerPersonas.findBySegNombre", query = "SELECT s FROM SellerPersonas s WHERE s.segNombre = :segNombre"),
    @NamedQuery(name = "SellerPersonas.findByPriApellido", query = "SELECT s FROM SellerPersonas s WHERE s.priApellido = :priApellido"),
    @NamedQuery(name = "SellerPersonas.findBySegApellido", query = "SELECT s FROM SellerPersonas s WHERE s.segApellido = :segApellido"),
    @NamedQuery(name = "SellerPersonas.findBySexo", query = "SELECT s FROM SellerPersonas s WHERE s.sexo = :sexo"),
    @NamedQuery(name = "SellerPersonas.findByTipDocumento", query = "SELECT s FROM SellerPersonas s WHERE s.tipDocumento = :tipDocumento"),
    @NamedQuery(name = "SellerPersonas.findByNumDocumento", query = "SELECT s FROM SellerPersonas s WHERE s.numDocumento = :numDocumento"),
    @NamedQuery(name = "SellerPersonas.findByEmail", query = "SELECT s FROM SellerPersonas s WHERE s.email = :email"),
    @NamedQuery(name = "SellerPersonas.findByCelular1", query = "SELECT s FROM SellerPersonas s WHERE s.celular1 = :celular1"),
    @NamedQuery(name = "SellerPersonas.findByCelular2", query = "SELECT s FROM SellerPersonas s WHERE s.celular2 = :celular2"),
    @NamedQuery(name = "SellerPersonas.findByFoto", query = "SELECT s FROM SellerPersonas s WHERE s.foto = :foto"),
    @NamedQuery(name = "SellerPersonas.findByCargo", query = "SELECT s FROM SellerPersonas s WHERE s.cargo = :cargo"),
    @NamedQuery(name = "SellerPersonas.findByCntPrincipal", query = "SELECT s FROM SellerPersonas s WHERE s.cntPrincipal = :cntPrincipal"),
    @NamedQuery(name = "SellerPersonas.findByUsuRegistro", query = "SELECT s FROM SellerPersonas s WHERE s.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "SellerPersonas.findByFecRegistro", query = "SELECT s FROM SellerPersonas s WHERE s.fecRegistro = :fecRegistro"),
    @NamedQuery(name = "SellerPersonas.findByUsuActualizacion", query = "SELECT s FROM SellerPersonas s WHERE s.usuActualizacion = :usuActualizacion"),
    @NamedQuery(name = "SellerPersonas.findByFecActualizacion", query = "SELECT s FROM SellerPersonas s WHERE s.fecActualizacion = :fecActualizacion"),
    @NamedQuery(name = "SellerPersonas.findByEstado", query = "SELECT s FROM SellerPersonas s WHERE s.estado = :estado"),
    @NamedQuery(name = "SellerPersonas.findByFecNacimiento", query = "SELECT s FROM SellerPersonas s WHERE s.fecNacimiento = :fecNacimiento"),
    @NamedQuery(name = "SellerPersonas.findByTemaApp", query = "SELECT s FROM SellerPersonas s WHERE s.temaApp = :temaApp")})
public class SellerPersonas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "pri_nombre")
    private String priNombre;
    @Size(max = 100)
    @Column(name = "seg_nombre")
    private String segNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "pri_apellido")
    private String priApellido;
    @Size(max = 120)
    @Column(name = "seg_apellido")
    private String segApellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "sexo")
    private String sexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tip_documento")
    private String tipDocumento;
    @Size(max = 25)
    @Column(name = "num_documento")
    private String numDocumento;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "celular1")
    private String celular1;
    @Size(max = 15)
    @Column(name = "celular2")
    private String celular2;
    @Size(max = 255)
    @Column(name = "foto")
    private String foto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "cargo")
    private String cargo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cnt_principal")
    private int cntPrincipal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "usu_registro")
    private String usuRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecRegistro;
    @Size(max = 150)
    @Column(name = "usu_actualizacion")
    private String usuActualizacion;
    @Column(name = "fec_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecActualizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Column(name = "fec_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fecNacimiento;
    @Size(max = 100)
    @Column(name = "tema_app")
    private String temaApp;
    @JoinColumn(name = "idseller", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sellers idseller;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idSellerpersona", fetch = FetchType.LAZY)
    private SellerCuentas sellerCuentas;

    public SellerPersonas() {
    }

    public SellerPersonas(Integer id) {
        this.id = id;
    }

    public SellerPersonas(Integer id, String priNombre, String priApellido, String sexo, String tipDocumento, String email, String celular1, String cargo, int cntPrincipal, String usuRegistro, Date fecRegistro, int estado) {
        this.id = id;
        this.priNombre = priNombre;
        this.priApellido = priApellido;
        this.sexo = sexo;
        this.tipDocumento = tipDocumento;
        this.email = email;
        this.celular1 = celular1;
        this.cargo = cargo;
        this.cntPrincipal = cntPrincipal;
        this.usuRegistro = usuRegistro;
        this.fecRegistro = fecRegistro;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPriNombre() {
        return priNombre;
    }

    public void setPriNombre(String priNombre) {
        this.priNombre = priNombre;
    }

    public String getSegNombre() {
        return segNombre;
    }

    public void setSegNombre(String segNombre) {
        this.segNombre = segNombre;
    }

    public String getPriApellido() {
        return priApellido;
    }

    public void setPriApellido(String priApellido) {
        this.priApellido = priApellido;
    }

    public String getSegApellido() {
        return segApellido;
    }

    public void setSegApellido(String segApellido) {
        this.segApellido = segApellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipDocumento() {
        return tipDocumento;
    }

    public void setTipDocumento(String tipDocumento) {
        this.tipDocumento = tipDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular1() {
        return celular1;
    }

    public void setCelular1(String celular1) {
        this.celular1 = celular1;
    }

    public String getCelular2() {
        return celular2;
    }

    public void setCelular2(String celular2) {
        this.celular2 = celular2;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getCntPrincipal() {
        return cntPrincipal;
    }

    public void setCntPrincipal(int cntPrincipal) {
        this.cntPrincipal = cntPrincipal;
    }

    public String getUsuRegistro() {
        return usuRegistro;
    }

    public void setUsuRegistro(String usuRegistro) {
        this.usuRegistro = usuRegistro;
    }

    public Date getFecRegistro() {
        return fecRegistro;
    }

    public void setFecRegistro(Date fecRegistro) {
        this.fecRegistro = fecRegistro;
    }

    public String getUsuActualizacion() {
        return usuActualizacion;
    }

    public void setUsuActualizacion(String usuActualizacion) {
        this.usuActualizacion = usuActualizacion;
    }

    public Date getFecActualizacion() {
        return fecActualizacion;
    }

    public void setFecActualizacion(Date fecActualizacion) {
        this.fecActualizacion = fecActualizacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFecNacimiento() {
        return fecNacimiento;
    }

    public void setFecNacimiento(Date fecNacimiento) {
        this.fecNacimiento = fecNacimiento;
    }

    public String getTemaApp() {
        return temaApp;
    }

    public void setTemaApp(String temaApp) {
        this.temaApp = temaApp;
    }

    public Sellers getIdseller() {
        return idseller;
    }

    public void setIdseller(Sellers idseller) {
        this.idseller = idseller;
    }

    public SellerCuentas getSellerCuentas() {
        return sellerCuentas;
    }

    public void setSellerCuentas(SellerCuentas sellerCuentas) {
        this.sellerCuentas = sellerCuentas;
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
        if (!(object instanceof SellerPersonas)) {
            return false;
        }
        SellerPersonas other = (SellerPersonas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.SellerPersonas[ id=" + id + " ]";
    }
    
}
