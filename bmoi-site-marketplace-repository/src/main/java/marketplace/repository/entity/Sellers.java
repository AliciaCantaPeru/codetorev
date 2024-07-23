/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Martin Pilar
 */
@Entity
@Table(name = "sellers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sellers.findAll", query = "SELECT s FROM Sellers s"),
    @NamedQuery(name = "Sellers.findById", query = "SELECT s FROM Sellers s WHERE s.id = :id"),
    @NamedQuery(name = "Sellers.findByTipEmpresa", query = "SELECT s FROM Sellers s WHERE s.tipEmpresa = :tipEmpresa"),
    @NamedQuery(name = "Sellers.findByNomComercial", query = "SELECT s FROM Sellers s WHERE s.nomComercial = :nomComercial"),
    @NamedQuery(name = "Sellers.findByRazSocial", query = "SELECT s FROM Sellers s WHERE s.razSocial = :razSocial"),
    @NamedQuery(name = "Sellers.findByRubro", query = "SELECT s FROM Sellers s WHERE s.rubro = :rubro"),
    @NamedQuery(name = "Sellers.findByRuc", query = "SELECT s FROM Sellers s WHERE s.ruc = :ruc"),
    @NamedQuery(name = "Sellers.findByTelefono", query = "SELECT s FROM Sellers s WHERE s.telefono = :telefono"),
    @NamedQuery(name = "Sellers.findByCelular", query = "SELECT s FROM Sellers s WHERE s.celular = :celular"),
    @NamedQuery(name = "Sellers.findByImg", query = "SELECT s FROM Sellers s WHERE s.img = :img"),
    @NamedQuery(name = "Sellers.findByWebsite", query = "SELECT s FROM Sellers s WHERE s.website = :website"),
    @NamedQuery(name = "Sellers.findByEmail1", query = "SELECT s FROM Sellers s WHERE s.email1 = :email1"),
    @NamedQuery(name = "Sellers.findByEmail2", query = "SELECT s FROM Sellers s WHERE s.email2 = :email2"),
    @NamedQuery(name = "Sellers.findByDescripcion", query = "SELECT s FROM Sellers s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "Sellers.findByUsuRegistro", query = "SELECT s FROM Sellers s WHERE s.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "Sellers.findByFecRegistro", query = "SELECT s FROM Sellers s WHERE s.fecRegistro = :fecRegistro"),
    @NamedQuery(name = "Sellers.findByUsuActualizacion", query = "SELECT s FROM Sellers s WHERE s.usuActualizacion = :usuActualizacion"),
    @NamedQuery(name = "Sellers.findByFecActualizacion", query = "SELECT s FROM Sellers s WHERE s.fecActualizacion = :fecActualizacion"),
    @NamedQuery(name = "Sellers.findByEstado", query = "SELECT s FROM Sellers s WHERE s.estado = :estado"),
    @NamedQuery(name = "Sellers.findByFeciniActividades", query = "SELECT s FROM Sellers s WHERE s.feciniActividades = :feciniActividades"),
    @NamedQuery(name = "Sellers.findByTotPedidos", query = "SELECT s FROM Sellers s WHERE s.totPedidos = :totPedidos"),
    @NamedQuery(name = "Sellers.findByTotPedMonto", query = "SELECT s FROM Sellers s WHERE s.totPedMonto = :totPedMonto")})
public class Sellers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "tip_empresa")
    private String tipEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nom_comercial")
    private String nomComercial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "raz_social")
    private String razSocial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "rubro")
    private String rubro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "ruc")
    private String ruc;
    @Size(max = 25)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "celular")
    private String celular;
    @Size(max = 255)
    @Column(name = "img")
    private String img;
    @Size(max = 255)
    @Column(name = "website")
    private String website;
    @Size(max = 255)
    @Column(name = "email1")
    private String email1;
    @Size(max = 255)
    @Column(name = "email2")
    private String email2;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usu_registro")
    private String usuRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecRegistro;
    @Size(max = 50)
    @Column(name = "usu_actualizacion")
    private String usuActualizacion;
    @Column(name = "fec_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecActualizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Column(name = "fecini_actividades")
    @Temporal(TemporalType.DATE)
    private Date feciniActividades;
    @Column(name = "tot_pedidos")
    private Integer totPedidos;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "tot_ped_monto")
    private BigDecimal totPedMonto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSeller", fetch = FetchType.LAZY)
    private Set<SellerPlan> sellerPlanSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idseller", fetch = FetchType.LAZY)
    private Set<SellerPersonas> sellerPersonasSet;
    @OneToMany(mappedBy = "idSeller", fetch = FetchType.LAZY)
    private Set<SellerChats> sellerChatsSet;
    @JoinColumn(name = "iddireccion", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Direcciones iddireccion;
    @JoinColumn(name = "idpostulante", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Postulantes idpostulante;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSeller", fetch = FetchType.LAZY)
    private Set<Productos> productosSet;
    @OneToMany(mappedBy = "idSeller", fetch = FetchType.LAZY)
    private Set<SellerRedes> sellerRedesSet;

    public Sellers() {
    }

    public Sellers(Integer id) {
        this.id = id;
    }

    public Sellers(Integer id, String tipEmpresa, String nomComercial, String razSocial, String rubro, String ruc, String celular, String usuRegistro, Date fecRegistro, int estado) {
        this.id = id;
        this.tipEmpresa = tipEmpresa;
        this.nomComercial = nomComercial;
        this.razSocial = razSocial;
        this.rubro = rubro;
        this.ruc = ruc;
        this.celular = celular;
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

    public String getTipEmpresa() {
        return tipEmpresa;
    }

    public void setTipEmpresa(String tipEmpresa) {
        this.tipEmpresa = tipEmpresa;
    }

    public String getNomComercial() {
        return nomComercial;
    }

    public void setNomComercial(String nomComercial) {
        this.nomComercial = nomComercial;
    }

    public String getRazSocial() {
        return razSocial;
    }

    public void setRazSocial(String razSocial) {
        this.razSocial = razSocial;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Date getFeciniActividades() {
        return feciniActividades;
    }

    public void setFeciniActividades(Date feciniActividades) {
        this.feciniActividades = feciniActividades;
    }

    public Integer getTotPedidos() {
        return totPedidos;
    }

    public void setTotPedidos(Integer totPedidos) {
        this.totPedidos = totPedidos;
    }

    public BigDecimal getTotPedMonto() {
        return totPedMonto;
    }

    public void setTotPedMonto(BigDecimal totPedMonto) {
        this.totPedMonto = totPedMonto;
    }

    @XmlTransient
    public Set<SellerPlan> getSellerPlanSet() {
        return sellerPlanSet;
    }

    public void setSellerPlanSet(Set<SellerPlan> sellerPlanSet) {
        this.sellerPlanSet = sellerPlanSet;
    }

    @XmlTransient
    public Set<SellerPersonas> getSellerPersonasSet() {
        return sellerPersonasSet;
    }

    public void setSellerPersonasSet(Set<SellerPersonas> sellerPersonasSet) {
        this.sellerPersonasSet = sellerPersonasSet;
    }

    @XmlTransient
    public Set<SellerChats> getSellerChatsSet() {
        return sellerChatsSet;
    }

    public void setSellerChatsSet(Set<SellerChats> sellerChatsSet) {
        this.sellerChatsSet = sellerChatsSet;
    }

    public Direcciones getIddireccion() {
        return iddireccion;
    }

    public void setIddireccion(Direcciones iddireccion) {
        this.iddireccion = iddireccion;
    }

    public Postulantes getIdpostulante() {
        return idpostulante;
    }

    public void setIdpostulante(Postulantes idpostulante) {
        this.idpostulante = idpostulante;
    }

    @XmlTransient
    public Set<Productos> getProductosSet() {
        return productosSet;
    }

    public void setProductosSet(Set<Productos> productosSet) {
        this.productosSet = productosSet;
    }

    @XmlTransient
    public Set<SellerRedes> getSellerRedesSet() {
        return sellerRedesSet;
    }

    public void setSellerRedesSet(Set<SellerRedes> sellerRedesSet) {
        this.sellerRedesSet = sellerRedesSet;
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
        if (!(object instanceof Sellers)) {
            return false;
        }
        Sellers other = (Sellers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.Sellers[ id=" + id + " ]";
    }
    
}
