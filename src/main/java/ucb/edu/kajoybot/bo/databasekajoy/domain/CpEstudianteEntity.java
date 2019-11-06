package ucb.edu.kajoybot.bo.databasekajoy.domain;

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

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "cp_estudiante")
/*@NamedQueries({
        @NamedQuery(name = "CpEstudiante.findAll", query = "SELECT c FROM CpEstudiante c"),
        @NamedQuery(name = "CpEstudiante.findByHEstudianteId", query = "SELECT c FROM CpEstudiante c WHERE c.hEstudianteId = :hEstudianteId"),
        @NamedQuery(name = "CpEstudiante.findByIdEstudiante", query = "SELECT c FROM CpEstudiante c WHERE c.idEstudiante = :idEstudiante"),
        @NamedQuery(name = "CpEstudiante.findByNombre", query = "SELECT c FROM CpEstudiante c WHERE c.nombre = :nombre"),
        @NamedQuery(name = "CpEstudiante.findByApellidoPaterno", query = "SELECT c FROM CpEstudiante c WHERE c.apellidoPaterno = :apellidoPaterno"),
        @NamedQuery(name = "CpEstudiante.findByApellidoMaterno", query = "SELECT c FROM CpEstudiante c WHERE c.apellidoMaterno = :apellidoMaterno"),
        @NamedQuery(name = "CpEstudiante.findByTxUser", query = "SELECT c FROM CpEstudiante c WHERE c.txUser = :txUser"),
        @NamedQuery(name = "CpEstudiante.findByTxDate", query = "SELECT c FROM CpEstudiante c WHERE c.txDate = :txDate")})
*/public class CpEstudianteEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "h_estudiante_id")
    private Integer hEstudianteId;
    @Basic(optional = false)
    @Column(name = "id_estudiante")
    private int idEstudiante;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Basic(optional = false)
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Column(name = "tx_user")
    private String txUser;
    @Column(name = "tx_date")
    @Temporal(TemporalType.DATE)
    private Date txDate;

    public CpEstudianteEntity() {
    }

    public CpEstudianteEntity(Integer hEstudianteId) {
        this.hEstudianteId = hEstudianteId;
    }

    public CpEstudianteEntity(Integer hEstudianteId, int idEstudiante, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.hEstudianteId = hEstudianteId;
        this.idEstudiante = idEstudiante;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public Integer getHEstudianteId() {
        return hEstudianteId;
    }

    public void setHEstudianteId(Integer hEstudianteId) {
        this.hEstudianteId = hEstudianteId;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hEstudianteId != null ? hEstudianteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CpEstudianteEntity)) {
            return false;
        }
        CpEstudianteEntity other = (CpEstudianteEntity) object;
        if ((this.hEstudianteId == null && other.hEstudianteId != null) || (this.hEstudianteId != null && !this.hEstudianteId.equals(other.hEstudianteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectokajoy.ucb.edu.bo.clases.CpEstudiante[ hEstudianteId=" + hEstudianteId + " ]";
    }

}
