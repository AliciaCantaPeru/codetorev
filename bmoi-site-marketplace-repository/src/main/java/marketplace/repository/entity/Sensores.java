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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Martin Pilar
 */
@Entity
@Table(name = "sensores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sensores.findAll", query = "SELECT s FROM Sensores s"),
    @NamedQuery(name = "Sensores.findById", query = "SELECT s FROM Sensores s WHERE s.id = :id"),
    @NamedQuery(name = "Sensores.findByDevice", query = "SELECT s FROM Sensores s WHERE s.device = :device"),
    @NamedQuery(name = "Sensores.findByDatos", query = "SELECT s FROM Sensores s WHERE s.datos = :datos"),
    @NamedQuery(name = "Sensores.findByPulsos", query = "SELECT s FROM Sensores s WHERE s.pulsos = :pulsos"),
    @NamedQuery(name = "Sensores.findByBateria", query = "SELECT s FROM Sensores s WHERE s.bateria = :bateria"),
    @NamedQuery(name = "Sensores.findByDebug", query = "SELECT s FROM Sensores s WHERE s.debug = :debug"),
    @NamedQuery(name = "Sensores.findByTiempo", query = "SELECT s FROM Sensores s WHERE s.tiempo = :tiempo")})
public class Sensores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 250)
    @Column(name = "device")
    private String device;
    @Size(max = 2500)
    @Column(name = "datos")
    private String datos;
    @Size(max = 255)
    @Column(name = "pulsos")
    private String pulsos;
    @Size(max = 255)
    @Column(name = "bateria")
    private String bateria;
    @Size(max = 255)
    @Column(name = "debug")
    private String debug;
    @Size(max = 255)
    @Column(name = "tiempo")
    private String tiempo;

    public Sensores() {
    }

    public Sensores(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public String getPulsos() {
        return pulsos;
    }

    public void setPulsos(String pulsos) {
        this.pulsos = pulsos;
    }

    public String getBateria() {
        return bateria;
    }

    public void setBateria(String bateria) {
        this.bateria = bateria;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
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
        if (!(object instanceof Sensores)) {
            return false;
        }
        Sensores other = (Sensores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.Sensores[ id=" + id + " ]";
    }
    
}
