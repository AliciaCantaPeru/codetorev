/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Martin Pilar
 */
@Entity
@Table(name = "postulantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Postulantes.findAll", query = "SELECT p FROM Postulantes p"),
    @NamedQuery(name = "Postulantes.findById", query = "SELECT p FROM Postulantes p WHERE p.id = :id"),
    @NamedQuery(name = "Postulantes.findByPriNombre", query = "SELECT p FROM Postulantes p WHERE p.priNombre = :priNombre"),
    @NamedQuery(name = "Postulantes.findBySegNombre", query = "SELECT p FROM Postulantes p WHERE p.segNombre = :segNombre"),
    @NamedQuery(name = "Postulantes.findByPriApellido", query = "SELECT p FROM Postulantes p WHERE p.priApellido = :priApellido"),
    @NamedQuery(name = "Postulantes.findBySegApellido", query = "SELECT p FROM Postulantes p WHERE p.segApellido = :segApellido"),
    @NamedQuery(name = "Postulantes.findByEmail", query = "SELECT p FROM Postulantes p WHERE p.email = :email"),
    @NamedQuery(name = "Postulantes.findByTelefono", query = "SELECT p FROM Postulantes p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Postulantes.findByCelular", query = "SELECT p FROM Postulantes p WHERE p.celular = :celular"),
    @NamedQuery(name = "Postulantes.findByComoLlegaste", query = "SELECT p FROM Postulantes p WHERE p.comoLlegaste = :comoLlegaste"),
    @NamedQuery(name = "Postulantes.findByTipoEmpresa", query = "SELECT p FROM Postulantes p WHERE p.tipoEmpresa = :tipoEmpresa"),
    @NamedQuery(name = "Postulantes.findByNombreMarca", query = "SELECT p FROM Postulantes p WHERE p.nombreMarca = :nombreMarca"),
    @NamedQuery(name = "Postulantes.findByRazonSocial", query = "SELECT p FROM Postulantes p WHERE p.razonSocial = :razonSocial"),
    @NamedQuery(name = "Postulantes.findByRubro", query = "SELECT p FROM Postulantes p WHERE p.rubro = :rubro"),
    @NamedQuery(name = "Postulantes.findByRuc", query = "SELECT p FROM Postulantes p WHERE p.ruc = :ruc"),
    @NamedQuery(name = "Postulantes.findByFecInicioActividades", query = "SELECT p FROM Postulantes p WHERE p.fecInicioActividades = :fecInicioActividades"),
    @NamedQuery(name = "Postulantes.findByIdPais", query = "SELECT p FROM Postulantes p WHERE p.idPais = :idPais"),
    @NamedQuery(name = "Postulantes.findByIdDepartamento", query = "SELECT p FROM Postulantes p WHERE p.idDepartamento = :idDepartamento"),
    @NamedQuery(name = "Postulantes.findByIdProvincia", query = "SELECT p FROM Postulantes p WHERE p.idProvincia = :idProvincia"),
    @NamedQuery(name = "Postulantes.findByIdDistrito", query = "SELECT p FROM Postulantes p WHERE p.idDistrito = :idDistrito"),
    @NamedQuery(name = "Postulantes.findByDireccion", query = "SELECT p FROM Postulantes p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Postulantes.findByReferencia", query = "SELECT p FROM Postulantes p WHERE p.referencia = :referencia"),
    @NamedQuery(name = "Postulantes.findByCategoriaVende", query = "SELECT p FROM Postulantes p WHERE p.categoriaVende = :categoriaVende"),
    @NamedQuery(name = "Postulantes.findByEmiteBoleta", query = "SELECT p FROM Postulantes p WHERE p.emiteBoleta = :emiteBoleta"),
    @NamedQuery(name = "Postulantes.findByCapDespacho24h", query = "SELECT p FROM Postulantes p WHERE p.capDespacho24h = :capDespacho24h"),
    @NamedQuery(name = "Postulantes.findByCapProduccionNuevo48h", query = "SELECT p FROM Postulantes p WHERE p.capProduccionNuevo48h = :capProduccionNuevo48h"),
    @NamedQuery(name = "Postulantes.findByCapDespachoNuevo48h", query = "SELECT p FROM Postulantes p WHERE p.capDespachoNuevo48h = :capDespachoNuevo48h"),
    @NamedQuery(name = "Postulantes.findByCapDevolucion", query = "SELECT p FROM Postulantes p WHERE p.capDevolucion = :capDevolucion"),
    @NamedQuery(name = "Postulantes.findByVentaOnline", query = "SELECT p FROM Postulantes p WHERE p.ventaOnline = :ventaOnline"),
    @NamedQuery(name = "Postulantes.findByLinkWeb", query = "SELECT p FROM Postulantes p WHERE p.linkWeb = :linkWeb"),
    @NamedQuery(name = "Postulantes.findByCantidadProductos", query = "SELECT p FROM Postulantes p WHERE p.cantidadProductos = :cantidadProductos"),
    @NamedQuery(name = "Postulantes.findByAprobado", query = "SELECT p FROM Postulantes p WHERE p.aprobado = :aprobado"),
    @NamedQuery(name = "Postulantes.findByHumano", query = "SELECT p FROM Postulantes p WHERE p.humano = :humano"),
    @NamedQuery(name = "Postulantes.findByEstado", query = "SELECT p FROM Postulantes p WHERE p.estado = :estado")})
public class Postulantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "pri_nombre")
    private String priNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "seg_nombre")
    private String segNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "pri_apellido")
    private String priApellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "seg_apellido")
    private String segApellido;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "email")
    private String email;
    @Size(max = 25)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "celular")
    private String celular;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "como_llegaste")
    private String comoLlegaste;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "tipo_empresa")
    private String tipoEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre_marca")
    private String nombreMarca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "razon_social")
    private String razonSocial;
    @Size(max = 100)
    @Column(name = "rubro")
    private String rubro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "ruc")
    private String ruc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "fec_inicio_actividades")
    private String fecInicioActividades;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_pais")
    private int idPais;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_departamento")
    private int idDepartamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_provincia")
    private int idProvincia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_distrito")
    private int idDistrito;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 500)
    @Column(name = "referencia")
    private String referencia;
    @Size(max = 500)
    @Column(name = "categoria_vende")
    private String categoriaVende;
    @Basic(optional = false)
    @NotNull
    @Column(name = "emite_boleta")
    private int emiteBoleta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cap_despacho_24h")
    private int capDespacho24h;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cap_produccion_nuevo_48h")
    private int capProduccionNuevo48h;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cap_despacho_nuevo_48h")
    private int capDespachoNuevo48h;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cap_devolucion")
    private int capDevolucion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_online")
    private int ventaOnline;
    @Size(max = 500)
    @Column(name = "link_web")
    private String linkWeb;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "cantidad_productos")
    private String cantidadProductos;
    @Column(name = "aprobado")
    private Integer aprobado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "humano")
    private int humano;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpostulante", fetch = FetchType.LAZY)
    private Set<PostulantesRedesSociales> postulantesRedesSocialesSet;
    @OneToMany(mappedBy = "idpostulante", fetch = FetchType.LAZY)
    private Set<Sellers> sellersSet;

    public Postulantes() {
    }

    public Postulantes(Integer id) {
        this.id = id;
    }

    public Postulantes(Integer id, String priNombre, String segNombre, String priApellido, String segApellido, String email, String celular, String comoLlegaste, String tipoEmpresa, String nombreMarca, String razonSocial, String ruc, String fecInicioActividades, int idPais, int idDepartamento, int idProvincia, int idDistrito, String direccion, int emiteBoleta, int capDespacho24h, int capProduccionNuevo48h, int capDespachoNuevo48h, int capDevolucion, int ventaOnline, String cantidadProductos, int humano, int estado) {
        this.id = id;
        this.priNombre = priNombre;
        this.segNombre = segNombre;
        this.priApellido = priApellido;
        this.segApellido = segApellido;
        this.email = email;
        this.celular = celular;
        this.comoLlegaste = comoLlegaste;
        this.tipoEmpresa = tipoEmpresa;
        this.nombreMarca = nombreMarca;
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.fecInicioActividades = fecInicioActividades;
        this.idPais = idPais;
        this.idDepartamento = idDepartamento;
        this.idProvincia = idProvincia;
        this.idDistrito = idDistrito;
        this.direccion = direccion;
        this.emiteBoleta = emiteBoleta;
        this.capDespacho24h = capDespacho24h;
        this.capProduccionNuevo48h = capProduccionNuevo48h;
        this.capDespachoNuevo48h = capDespachoNuevo48h;
        this.capDevolucion = capDevolucion;
        this.ventaOnline = ventaOnline;
        this.cantidadProductos = cantidadProductos;
        this.humano = humano;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getComoLlegaste() {
        return comoLlegaste;
    }

    public void setComoLlegaste(String comoLlegaste) {
        this.comoLlegaste = comoLlegaste;
    }

    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
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

    public String getFecInicioActividades() {
        return fecInicioActividades;
    }

    public void setFecInicioActividades(String fecInicioActividades) {
        this.fecInicioActividades = fecInicioActividades;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCategoriaVende() {
        return categoriaVende;
    }

    public void setCategoriaVende(String categoriaVende) {
        this.categoriaVende = categoriaVende;
    }

    public int getEmiteBoleta() {
        return emiteBoleta;
    }

    public void setEmiteBoleta(int emiteBoleta) {
        this.emiteBoleta = emiteBoleta;
    }

    public int getCapDespacho24h() {
        return capDespacho24h;
    }

    public void setCapDespacho24h(int capDespacho24h) {
        this.capDespacho24h = capDespacho24h;
    }

    public int getCapProduccionNuevo48h() {
        return capProduccionNuevo48h;
    }

    public void setCapProduccionNuevo48h(int capProduccionNuevo48h) {
        this.capProduccionNuevo48h = capProduccionNuevo48h;
    }

    public int getCapDespachoNuevo48h() {
        return capDespachoNuevo48h;
    }

    public void setCapDespachoNuevo48h(int capDespachoNuevo48h) {
        this.capDespachoNuevo48h = capDespachoNuevo48h;
    }

    public int getCapDevolucion() {
        return capDevolucion;
    }

    public void setCapDevolucion(int capDevolucion) {
        this.capDevolucion = capDevolucion;
    }

    public int getVentaOnline() {
        return ventaOnline;
    }

    public void setVentaOnline(int ventaOnline) {
        this.ventaOnline = ventaOnline;
    }

    public String getLinkWeb() {
        return linkWeb;
    }

    public void setLinkWeb(String linkWeb) {
        this.linkWeb = linkWeb;
    }

    public String getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(String cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public Integer getAprobado() {
        return aprobado;
    }

    public void setAprobado(Integer aprobado) {
        this.aprobado = aprobado;
    }

    public int getHumano() {
        return humano;
    }

    public void setHumano(int humano) {
        this.humano = humano;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Set<PostulantesRedesSociales> getPostulantesRedesSocialesSet() {
        return postulantesRedesSocialesSet;
    }

    public void setPostulantesRedesSocialesSet(Set<PostulantesRedesSociales> postulantesRedesSocialesSet) {
        this.postulantesRedesSocialesSet = postulantesRedesSocialesSet;
    }

    @XmlTransient
    public Set<Sellers> getSellersSet() {
        return sellersSet;
    }

    public void setSellersSet(Set<Sellers> sellersSet) {
        this.sellersSet = sellersSet;
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
        if (!(object instanceof Postulantes)) {
            return false;
        }
        Postulantes other = (Postulantes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.Postulantes[ id=" + id + " ]";
    }
    
}
