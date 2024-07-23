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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "contactanos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contactanos.findAll", query = "SELECT c FROM Contactanos c"),
    @NamedQuery(name = "Contactanos.findById", query = "SELECT c FROM Contactanos c WHERE c.id = :id"),
    @NamedQuery(name = "Contactanos.findByNombres", query = "SELECT c FROM Contactanos c WHERE c.nombres = :nombres"),
    @NamedQuery(name = "Contactanos.findByApellidos", query = "SELECT c FROM Contactanos c WHERE c.apellidos = :apellidos"),
    @NamedQuery(name = "Contactanos.findByEmail", query = "SELECT c FROM Contactanos c WHERE c.email = :email"),
    @NamedQuery(name = "Contactanos.findByTelefono", query = "SELECT c FROM Contactanos c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Contactanos.findByFecha", query = "SELECT c FROM Contactanos c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Contactanos.findByMensaje", query = "SELECT c FROM Contactanos c WHERE c.mensaje = :mensaje"),
    @NamedQuery(name = "Contactanos.findByRespuesta", query = "SELECT c FROM Contactanos c WHERE c.respuesta = :respuesta"),
    @NamedQuery(name = "Contactanos.findByEstado", query = "SELECT c FROM Contactanos c WHERE c.estado = :estado")})
public class Contactanos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "apellidos")
    private String apellidos;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "telefono")
    private int telefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 500)
    @Column(name = "mensaje")
    private String mensaje;
    @Size(max = 2147483647)
    @Column(name = "respuesta")
    private String respuesta;
    @Size(max = 50)
    @Column(name = "estado")
    private String estado;

    public Contactanos() {
    }

    public Contactanos(Integer id) {
        this.id = id;
    }

    public Contactanos(Integer id, String nombres, String apellidos, String email, int telefono, Date fecha) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
        if (!(object instanceof Contactanos)) {
            return false;
        }
        Contactanos other = (Contactanos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.Contactanos[ id=" + id + " ]";
    }
    
}
