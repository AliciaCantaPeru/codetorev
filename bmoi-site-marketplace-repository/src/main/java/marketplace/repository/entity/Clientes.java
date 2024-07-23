/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity;

import java.io.Serializable;
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
@Table(name = "clientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c"),
    @NamedQuery(name = "Clientes.findById", query = "SELECT c FROM Clientes c WHERE c.id = :id"),
    @NamedQuery(name = "Clientes.findByTipEmpresa", query = "SELECT c FROM Clientes c WHERE c.tipEmpresa = :tipEmpresa"),
    @NamedQuery(name = "Clientes.findByNombre", query = "SELECT c FROM Clientes c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Clientes.findByRazSocial", query = "SELECT c FROM Clientes c WHERE c.razSocial = :razSocial"),
    @NamedQuery(name = "Clientes.findByRubro", query = "SELECT c FROM Clientes c WHERE c.rubro = :rubro"),
    @NamedQuery(name = "Clientes.findByRuc", query = "SELECT c FROM Clientes c WHERE c.ruc = :ruc"),
    @NamedQuery(name = "Clientes.findByTelefono", query = "SELECT c FROM Clientes c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Clientes.findByWeb", query = "SELECT c FROM Clientes c WHERE c.web = :web"),
    @NamedQuery(name = "Clientes.findByEmail", query = "SELECT c FROM Clientes c WHERE c.email = :email"),
    @NamedQuery(name = "Clientes.findByUsuRegistro", query = "SELECT c FROM Clientes c WHERE c.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "Clientes.findByFecRegistro", query = "SELECT c FROM Clientes c WHERE c.fecRegistro = :fecRegistro"),
    @NamedQuery(name = "Clientes.findByUsuActualizacion", query = "SELECT c FROM Clientes c WHERE c.usuActualizacion = :usuActualizacion"),
    @NamedQuery(name = "Clientes.findByFecActualizacion", query = "SELECT c FROM Clientes c WHERE c.fecActualizacion = :fecActualizacion"),
    @NamedQuery(name = "Clientes.findByEstado", query = "SELECT c FROM Clientes c WHERE c.estado = :estado")})
public class Clientes implements Serializable {

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
    @Size(min = 1, max = 350)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 500)
    @Column(name = "raz_social")
    private String razSocial;
    @Size(max = 100)
    @Column(name = "rubro")
    private String rubro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "ruc")
    private String ruc;
    @Size(max = 50)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 255)
    @Column(name = "web")
    private String web;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
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
    @JoinColumn(name = "id_persona", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personas idPersona;
    @OneToMany(mappedBy = "idCliente", fetch = FetchType.LAZY)
    private Set<ClienteRedes> clienteRedesSet;
    @OneToMany(mappedBy = "idCliente", fetch = FetchType.LAZY)
    private Set<DigImgdisenioClientes> digImgdisenioClientesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente", fetch = FetchType.LAZY)
    private Set<ClienteDirecciones> clienteDireccionesSet;

    public Clientes() {
    }

    public Clientes(Integer id) {
        this.id = id;
    }

    public Clientes(Integer id, String tipEmpresa, String nombre, String ruc, String usuRegistro, Date fecRegistro, int estado) {
        this.id = id;
        this.tipEmpresa = tipEmpresa;
        this.nombre = nombre;
        this.ruc = ruc;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Personas getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
    }

    @XmlTransient
    public Set<ClienteRedes> getClienteRedesSet() {
        return clienteRedesSet;
    }

    public void setClienteRedesSet(Set<ClienteRedes> clienteRedesSet) {
        this.clienteRedesSet = clienteRedesSet;
    }

    @XmlTransient
    public Set<DigImgdisenioClientes> getDigImgdisenioClientesSet() {
        return digImgdisenioClientesSet;
    }

    public void setDigImgdisenioClientesSet(Set<DigImgdisenioClientes> digImgdisenioClientesSet) {
        this.digImgdisenioClientesSet = digImgdisenioClientesSet;
    }

    @XmlTransient
    public Set<ClienteDirecciones> getClienteDireccionesSet() {
        return clienteDireccionesSet;
    }

    public void setClienteDireccionesSet(Set<ClienteDirecciones> clienteDireccionesSet) {
        this.clienteDireccionesSet = clienteDireccionesSet;
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
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.Clientes[ id=" + id + " ]";
    }
    
}
