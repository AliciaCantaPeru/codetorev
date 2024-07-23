/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace.repository.entity;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "promociones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promociones.findAll", query = "SELECT p FROM Promociones p"),
    @NamedQuery(name = "Promociones.findById", query = "SELECT p FROM Promociones p WHERE p.id = :id"),
    @NamedQuery(name = "Promociones.findByIdProducto", query = "SELECT p FROM Promociones p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "Promociones.findByNombre", query = "SELECT p FROM Promociones p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Promociones.findByTitulo", query = "SELECT p FROM Promociones p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "Promociones.findByPorcentaje", query = "SELECT p FROM Promociones p WHERE p.porcentaje = :porcentaje"),
    @NamedQuery(name = "Promociones.findByRangofecha", query = "SELECT p FROM Promociones p WHERE p.rangofecha = :rangofecha"),
    @NamedQuery(name = "Promociones.findByImgurl1", query = "SELECT p FROM Promociones p WHERE p.imgurl1 = :imgurl1"),
    @NamedQuery(name = "Promociones.findByImgurl2", query = "SELECT p FROM Promociones p WHERE p.imgurl2 = :imgurl2"),
    @NamedQuery(name = "Promociones.findByImgurl3", query = "SELECT p FROM Promociones p WHERE p.imgurl3 = :imgurl3"),
    @NamedQuery(name = "Promociones.findByField1", query = "SELECT p FROM Promociones p WHERE p.field1 = :field1"),
    @NamedQuery(name = "Promociones.findByField2", query = "SELECT p FROM Promociones p WHERE p.field2 = :field2"),
    @NamedQuery(name = "Promociones.findByField3", query = "SELECT p FROM Promociones p WHERE p.field3 = :field3"),
    @NamedQuery(name = "Promociones.findByField4", query = "SELECT p FROM Promociones p WHERE p.field4 = :field4"),
    @NamedQuery(name = "Promociones.findByField5", query = "SELECT p FROM Promociones p WHERE p.field5 = :field5"),
    @NamedQuery(name = "Promociones.findByUsuRegistro", query = "SELECT p FROM Promociones p WHERE p.usuRegistro = :usuRegistro"),
    @NamedQuery(name = "Promociones.findByFecRegistro", query = "SELECT p FROM Promociones p WHERE p.fecRegistro = :fecRegistro"),
    @NamedQuery(name = "Promociones.findByUsuActualizacion", query = "SELECT p FROM Promociones p WHERE p.usuActualizacion = :usuActualizacion"),
    @NamedQuery(name = "Promociones.findByFecActualizacion", query = "SELECT p FROM Promociones p WHERE p.fecActualizacion = :fecActualizacion"),
    @NamedQuery(name = "Promociones.findByEstado", query = "SELECT p FROM Promociones p WHERE p.estado = :estado")})
public class Promociones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_producto")
    private int idProducto;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 250)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentaje")
    private BigInteger porcentaje;
    @Size(max = 21)
    @Column(name = "rangofecha")
    private String rangofecha;
    @Size(max = 250)
    @Column(name = "imgurl1")
    private String imgurl1;
    @Size(max = 250)
    @Column(name = "imgurl2")
    private String imgurl2;
    @Size(max = 250)
    @Column(name = "imgurl3")
    private String imgurl3;
    @Size(max = 1)
    @Column(name = "field1")
    private String field1;
    @Size(max = 1)
    @Column(name = "field2")
    private String field2;
    @Size(max = 1)
    @Column(name = "field3")
    private String field3;
    @Size(max = 1)
    @Column(name = "field4")
    private String field4;
    @Size(max = 1)
    @Column(name = "field5")
    private String field5;
    @Size(max = 150)
    @Column(name = "usu_registro")
    private String usuRegistro;
    @Column(name = "fec_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecRegistro;
    @Size(max = 150)
    @Column(name = "usu_actualizacion")
    private String usuActualizacion;
    @Column(name = "fec_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecActualizacion;
    @Column(name = "estado")
    private Integer estado;

    public Promociones() {
    }

    public Promociones(Integer id) {
        this.id = id;
    }

    public Promociones(Integer id, int idProducto, BigInteger porcentaje) {
        this.id = id;
        this.idProducto = idProducto;
        this.porcentaje = porcentaje;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public BigInteger getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigInteger porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getRangofecha() {
        return rangofecha;
    }

    public void setRangofecha(String rangofecha) {
        this.rangofecha = rangofecha;
    }

    public String getImgurl1() {
        return imgurl1;
    }

    public void setImgurl1(String imgurl1) {
        this.imgurl1 = imgurl1;
    }

    public String getImgurl2() {
        return imgurl2;
    }

    public void setImgurl2(String imgurl2) {
        this.imgurl2 = imgurl2;
    }

    public String getImgurl3() {
        return imgurl3;
    }

    public void setImgurl3(String imgurl3) {
        this.imgurl3 = imgurl3;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public String getField5() {
        return field5;
    }

    public void setField5(String field5) {
        this.field5 = field5;
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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
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
        if (!(object instanceof Promociones)) {
            return false;
        }
        Promociones other = (Promociones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "marketplace.repository.entity.Promociones[ id=" + id + " ]";
    }
    
}
