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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Martin Pilar
 */
@Entity
@Table(name = "postulantes_redes_sociales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PostulantesRedesSociales.findAll", query = "SELECT p FROM PostulantesRedesSociales p"),
    @NamedQuery(name = "PostulantesRedesSociales.findById", query = "SELECT p FROM PostulantesRedesSociales p WHERE p.id = :id"),
    @NamedQuery(name = "PostulantesRedesSociales.findByNombre", query = "SELECT p FROM PostulantesRedesSociales p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PostulantesRedesSociales.findByUrl", query = "SELECT p FROM PostulantesRedesSociales p WHERE p.url = :url")})
public class PostulantesRedesSociales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "url")
    private String url;
    @JoinColumn(name = "idpostulante", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Postulantes idpostulante;

    public PostulantesRedesSociales() {
    }

    public PostulantesRedesSociales(Integer id) {
        this.id = id;
    }

    public PostulantesRedesSociales(Integer id, String nombre, String url) {
        this.id = id;
        this.nombre = nombre;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Postulantes getIdpostulante() {
        return idpostulante;
    }

    public void setIdpostulante(Postulantes idpostulante) {
        this.idpostulante = idpostulante;
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
        if (!(object instanceof PostulantesRedesSociales)) {
            return false;
        }
        PostulantesRedesSociales other = (PostulantesRedesSociales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.PostulantesRedesSociales[ id=" + id + " ]";
    }
    
}
