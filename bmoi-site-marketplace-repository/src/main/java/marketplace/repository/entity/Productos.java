/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p"),
    @NamedQuery(name = "Productos.findById", query = "SELECT p FROM Productos p WHERE p.id = :id"),
    @NamedQuery(name = "Productos.findBySku", query = "SELECT p FROM Productos p WHERE p.sku = :sku"),
    @NamedQuery(name = "Productos.findByNombrecorto", query = "SELECT p FROM Productos p WHERE p.nombrecorto = :nombrecorto"),
    @NamedQuery(name = "Productos.findByTitulo", query = "SELECT p FROM Productos p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "Productos.findByMultimedia1", query = "SELECT p FROM Productos p WHERE p.multimedia1 = :multimedia1"),
    @NamedQuery(name = "Productos.findByMultimedia2", query = "SELECT p FROM Productos p WHERE p.multimedia2 = :multimedia2"),
    @NamedQuery(name = "Productos.findByDscMultimedia1", query = "SELECT p FROM Productos p WHERE p.dscMultimedia1 = :dscMultimedia1"),
    @NamedQuery(name = "Productos.findByStock", query = "SELECT p FROM Productos p WHERE p.stock = :stock"),
    @NamedQuery(name = "Productos.findByMoneda", query = "SELECT p FROM Productos p WHERE p.moneda = :moneda"),
    @NamedQuery(name = "Productos.findByPreciobasico", query = "SELECT p FROM Productos p WHERE p.preciobasico = :preciobasico"),
    @NamedQuery(name = "Productos.findByPreciolista", query = "SELECT p FROM Productos p WHERE p.preciolista = :preciolista"),
    @NamedQuery(name = "Productos.findByPrecioenvio", query = "SELECT p FROM Productos p WHERE p.precioenvio = :precioenvio"),
    @NamedQuery(name = "Productos.findByEnviogratis", query = "SELECT p FROM Productos p WHERE p.enviogratis = :enviogratis"),
    @NamedQuery(name = "Productos.findByDscMultimedia2", query = "SELECT p FROM Productos p WHERE p.dscMultimedia2 = :dscMultimedia2"),
    @NamedQuery(name = "Productos.findByF2", query = "SELECT p FROM Productos p WHERE p.f2 = :f2"),
    @NamedQuery(name = "Productos.findByProductonuevo", query = "SELECT p FROM Productos p WHERE p.productonuevo = :productonuevo"),
    @NamedQuery(name = "Productos.findByOnlinestatus", query = "SELECT p FROM Productos p WHERE p.onlinestatus = :onlinestatus"),
    @NamedQuery(name = "Productos.findByUnidadmedida", query = "SELECT p FROM Productos p WHERE p.unidadmedida = :unidadmedida"),
    @NamedQuery(name = "Productos.findByAvgstar", query = "SELECT p FROM Productos p WHERE p.avgstar = :avgstar"),
    @NamedQuery(name = "Productos.findByEnoferta", query = "SELECT p FROM Productos p WHERE p.enoferta = :enoferta"),
    @NamedQuery(name = "Productos.findByIniciodigital", query = "SELECT p FROM Productos p WHERE p.iniciodigital = :iniciodigital"),
    @NamedQuery(name = "Productos.findByPersonalizable", query = "SELECT p FROM Productos p WHERE p.personalizable = :personalizable"),
    @NamedQuery(name = "Productos.findByNroSegmentospersonalizable", query = "SELECT p FROM Productos p WHERE p.nroSegmentospersonalizable = :nroSegmentospersonalizable"),
    @NamedQuery(name = "Productos.findByDestacado", query = "SELECT p FROM Productos p WHERE p.destacado = :destacado"),
    @NamedQuery(name = "Productos.findByImpuesto", query = "SELECT p FROM Productos p WHERE p.impuesto = :impuesto"),
    @NamedQuery(name = "Productos.findByImpuestoporcentaje", query = "SELECT p FROM Productos p WHERE p.impuestoporcentaje = :impuestoporcentaje"),
    @NamedQuery(name = "Productos.findByIdSellerchat", query = "SELECT p FROM Productos p WHERE p.idSellerchat = :idSellerchat"),
    @NamedQuery(name = "Productos.findByF7", query = "SELECT p FROM Productos p WHERE p.f7 = :f7"),
    @NamedQuery(name = "Productos.findByF8", query = "SELECT p FROM Productos p WHERE p.f8 = :f8"),
    @NamedQuery(name = "Productos.findByF9", query = "SELECT p FROM Productos p WHERE p.f9 = :f9"),
    @NamedQuery(name = "Productos.findByDescripcion", query = "SELECT p FROM Productos p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Productos.findByUsuRegistro", query = "SELECT p FROM Productos p WHERE p.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "Productos.findByFecRegistro", query = "SELECT p FROM Productos p WHERE p.fecRegistro = :fecRegistro"),
    @NamedQuery(name = "Productos.findByUsuActualizacion", query = "SELECT p FROM Productos p WHERE p.usuActualizacion = :usuActualizacion"),
    @NamedQuery(name = "Productos.findByFecActualizacion", query = "SELECT p FROM Productos p WHERE p.fecActualizacion = :fecActualizacion"),
    @NamedQuery(name = "Productos.findByEstado", query = "SELECT p FROM Productos p WHERE p.estado = :estado")})
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "sku")
    private String sku;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombrecorto")
    private String nombrecorto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "multimedia1")
    private String multimedia1;
    @Size(max = 250)
    @Column(name = "multimedia2")
    private String multimedia2;
    @Size(max = 250)
    @Column(name = "dsc_multimedia1")
    private String dscMultimedia1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stock")
    private int stock;
    @Size(max = 3)
    @Column(name = "moneda")
    private String moneda;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "preciobasico")
    private BigDecimal preciobasico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "preciolista")
    private BigDecimal preciolista;
    @Column(name = "precioenvio")
    private BigDecimal precioenvio;
    @Column(name = "enviogratis")
    private Integer enviogratis;
    @Size(max = 250)
    @Column(name = "dsc_multimedia2")
    private String dscMultimedia2;
    @Column(name = "f2")
    private Integer f2;
    @Size(max = 30)
    @Column(name = "productonuevo")
    private String productonuevo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "onlinestatus")
    private int onlinestatus;
    @Size(max = 15)
    @Column(name = "unidadmedida")
    private String unidadmedida;
    @Column(name = "avgstar")
    private BigInteger avgstar;
    @Size(max = 30)
    @Column(name = "enoferta")
    private String enoferta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iniciodigital")
    private int iniciodigital;
    @Basic(optional = false)
    @NotNull
    @Column(name = "personalizable")
    private int personalizable;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nro_segmentospersonalizable")
    private int nroSegmentospersonalizable;
    @Column(name = "destacado")
    private Integer destacado;
    @Size(max = 30)
    @Column(name = "impuesto")
    private String impuesto;
    @Column(name = "impuestoporcentaje")
    private BigInteger impuestoporcentaje;
    @Column(name = "id_sellerchat")
    private Integer idSellerchat;
    @Size(max = 1)
    @Column(name = "f7")
    private String f7;
    @Size(max = 1)
    @Column(name = "f8")
    private String f8;
    @Size(max = 1)
    @Column(name = "f9")
    private String f9;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private Set<ProductoAssetsMultimedia> productoAssetsMultimediaSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private Set<DigProductoTipopersonalizacion> digProductoTipopersonalizacionSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private Set<ProductoDimensiones> productoDimensionesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private Set<ProductoVariantes> productoVariantesSet;
    @JoinColumn(name = "id_material_categoriagrupomenu", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MaterialesCategoriagrupomenu idMaterialCategoriagrupomenu;
    @JoinColumn(name = "codigo_menu", referencedColumnName = "codigo")
    @ManyToOne(fetch = FetchType.LAZY)
    private Menus codigoMenu;
    @JoinColumn(name = "id_brand", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductoBrands idBrand;
    @OneToMany(mappedBy = "idPrdpadre", fetch = FetchType.LAZY)
    private Set<Productos> productosSet;
    @JoinColumn(name = "id_prdpadre", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Productos idPrdpadre;
    @JoinColumn(name = "id_seller", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sellers idSeller;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private Set<ProductoImagen> productoImagenSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private Set<ProductoCatgrupmenu> productoCatgrupmenuSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.LAZY)
    private Set<ProductoImagenPropio> productoImagenPropioSet;

    public Productos() {
    }

    public Productos(Integer id) {
        this.id = id;
    }

    public Productos(Integer id, String sku, String nombrecorto, String titulo, String multimedia1, int stock, BigDecimal preciobasico, BigDecimal preciolista, int onlinestatus, int iniciodigital, int personalizable, int nroSegmentospersonalizable, String descripcion, String usuRegistro, Date fecRegistro, int estado) {
        this.id = id;
        this.sku = sku;
        this.nombrecorto = nombrecorto;
        this.titulo = titulo;
        this.multimedia1 = multimedia1;
        this.stock = stock;
        this.preciobasico = preciobasico;
        this.preciolista = preciolista;
        this.onlinestatus = onlinestatus;
        this.iniciodigital = iniciodigital;
        this.personalizable = personalizable;
        this.nroSegmentospersonalizable = nroSegmentospersonalizable;
        this.descripcion = descripcion;
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNombrecorto() {
        return nombrecorto;
    }

    public void setNombrecorto(String nombrecorto) {
        this.nombrecorto = nombrecorto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMultimedia1() {
        return multimedia1;
    }

    public void setMultimedia1(String multimedia1) {
        this.multimedia1 = multimedia1;
    }

    public String getMultimedia2() {
        return multimedia2;
    }

    public void setMultimedia2(String multimedia2) {
        this.multimedia2 = multimedia2;
    }

    public String getDscMultimedia1() {
        return dscMultimedia1;
    }

    public void setDscMultimedia1(String dscMultimedia1) {
        this.dscMultimedia1 = dscMultimedia1;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getPreciobasico() {
        return preciobasico;
    }

    public void setPreciobasico(BigDecimal preciobasico) {
        this.preciobasico = preciobasico;
    }

    public BigDecimal getPreciolista() {
        return preciolista;
    }

    public void setPreciolista(BigDecimal preciolista) {
        this.preciolista = preciolista;
    }

    public BigDecimal getPrecioenvio() {
        return precioenvio;
    }

    public void setPrecioenvio(BigDecimal precioenvio) {
        this.precioenvio = precioenvio;
    }

    public Integer getEnviogratis() {
        return enviogratis;
    }

    public void setEnviogratis(Integer enviogratis) {
        this.enviogratis = enviogratis;
    }

    public String getDscMultimedia2() {
        return dscMultimedia2;
    }

    public void setDscMultimedia2(String dscMultimedia2) {
        this.dscMultimedia2 = dscMultimedia2;
    }

    public Integer getF2() {
        return f2;
    }

    public void setF2(Integer f2) {
        this.f2 = f2;
    }

    public String getProductonuevo() {
        return productonuevo;
    }

    public void setProductonuevo(String productonuevo) {
        this.productonuevo = productonuevo;
    }

    public int getOnlinestatus() {
        return onlinestatus;
    }

    public void setOnlinestatus(int onlinestatus) {
        this.onlinestatus = onlinestatus;
    }

    public String getUnidadmedida() {
        return unidadmedida;
    }

    public void setUnidadmedida(String unidadmedida) {
        this.unidadmedida = unidadmedida;
    }

    public BigInteger getAvgstar() {
        return avgstar;
    }

    public void setAvgstar(BigInteger avgstar) {
        this.avgstar = avgstar;
    }

    public String getEnoferta() {
        return enoferta;
    }

    public void setEnoferta(String enoferta) {
        this.enoferta = enoferta;
    }

    public int getIniciodigital() {
        return iniciodigital;
    }

    public void setIniciodigital(int iniciodigital) {
        this.iniciodigital = iniciodigital;
    }

    public int getPersonalizable() {
        return personalizable;
    }

    public void setPersonalizable(int personalizable) {
        this.personalizable = personalizable;
    }

    public int getNroSegmentospersonalizable() {
        return nroSegmentospersonalizable;
    }

    public void setNroSegmentospersonalizable(int nroSegmentospersonalizable) {
        this.nroSegmentospersonalizable = nroSegmentospersonalizable;
    }

    public Integer getDestacado() {
        return destacado;
    }

    public void setDestacado(Integer destacado) {
        this.destacado = destacado;
    }

    public String getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    public BigInteger getImpuestoporcentaje() {
        return impuestoporcentaje;
    }

    public void setImpuestoporcentaje(BigInteger impuestoporcentaje) {
        this.impuestoporcentaje = impuestoporcentaje;
    }

    public Integer getIdSellerchat() {
        return idSellerchat;
    }

    public void setIdSellerchat(Integer idSellerchat) {
        this.idSellerchat = idSellerchat;
    }

    public String getF7() {
        return f7;
    }

    public void setF7(String f7) {
        this.f7 = f7;
    }

    public String getF8() {
        return f8;
    }

    public void setF8(String f8) {
        this.f8 = f8;
    }

    public String getF9() {
        return f9;
    }

    public void setF9(String f9) {
        this.f9 = f9;
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

    @XmlTransient
    public Set<ProductoAssetsMultimedia> getProductoAssetsMultimediaSet() {
        return productoAssetsMultimediaSet;
    }

    public void setProductoAssetsMultimediaSet(Set<ProductoAssetsMultimedia> productoAssetsMultimediaSet) {
        this.productoAssetsMultimediaSet = productoAssetsMultimediaSet;
    }

    @XmlTransient
    public Set<DigProductoTipopersonalizacion> getDigProductoTipopersonalizacionSet() {
        return digProductoTipopersonalizacionSet;
    }

    public void setDigProductoTipopersonalizacionSet(Set<DigProductoTipopersonalizacion> digProductoTipopersonalizacionSet) {
        this.digProductoTipopersonalizacionSet = digProductoTipopersonalizacionSet;
    }

    @XmlTransient
    public Set<ProductoDimensiones> getProductoDimensionesSet() {
        return productoDimensionesSet;
    }

    public void setProductoDimensionesSet(Set<ProductoDimensiones> productoDimensionesSet) {
        this.productoDimensionesSet = productoDimensionesSet;
    }

    @XmlTransient
    public Set<ProductoVariantes> getProductoVariantesSet() {
        return productoVariantesSet;
    }

    public void setProductoVariantesSet(Set<ProductoVariantes> productoVariantesSet) {
        this.productoVariantesSet = productoVariantesSet;
    }

    public MaterialesCategoriagrupomenu getIdMaterialCategoriagrupomenu() {
        return idMaterialCategoriagrupomenu;
    }

    public void setIdMaterialCategoriagrupomenu(MaterialesCategoriagrupomenu idMaterialCategoriagrupomenu) {
        this.idMaterialCategoriagrupomenu = idMaterialCategoriagrupomenu;
    }

    public Menus getCodigoMenu() {
        return codigoMenu;
    }

    public void setCodigoMenu(Menus codigoMenu) {
        this.codigoMenu = codigoMenu;
    }

    public ProductoBrands getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(ProductoBrands idBrand) {
        this.idBrand = idBrand;
    }

    @XmlTransient
    public Set<Productos> getProductosSet() {
        return productosSet;
    }

    public void setProductosSet(Set<Productos> productosSet) {
        this.productosSet = productosSet;
    }

    public Productos getIdPrdpadre() {
        return idPrdpadre;
    }

    public void setIdPrdpadre(Productos idPrdpadre) {
        this.idPrdpadre = idPrdpadre;
    }

    public Sellers getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(Sellers idSeller) {
        this.idSeller = idSeller;
    }

    @XmlTransient
    public Set<ProductoImagen> getProductoImagenSet() {
        return productoImagenSet;
    }

    public void setProductoImagenSet(Set<ProductoImagen> productoImagenSet) {
        this.productoImagenSet = productoImagenSet;
    }

    @XmlTransient
    public Set<ProductoCatgrupmenu> getProductoCatgrupmenuSet() {
        return productoCatgrupmenuSet;
    }

    public void setProductoCatgrupmenuSet(Set<ProductoCatgrupmenu> productoCatgrupmenuSet) {
        this.productoCatgrupmenuSet = productoCatgrupmenuSet;
    }

    @XmlTransient
    public Set<ProductoImagenPropio> getProductoImagenPropioSet() {
        return productoImagenPropioSet;
    }

    public void setProductoImagenPropioSet(Set<ProductoImagenPropio> productoImagenPropioSet) {
        this.productoImagenPropioSet = productoImagenPropioSet;
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
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.Productos[ id=" + id + " ]";
    }
    
}
