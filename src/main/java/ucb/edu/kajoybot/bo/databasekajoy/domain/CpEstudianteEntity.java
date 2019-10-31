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
        @NamedQuery(name = "CpEstudiante.findByHEstudId", query = "SELECT c FROM CpEstudiante c WHERE c.hEstudId = :hEstudId"),
        @NamedQuery(name = "CpEstudiante.findByIdEstudiante", query = "SELECT c FROM CpEstudiante c WHERE c.idEstudiante = :idEstudiante"),
        @NamedQuery(name = "CpEstudiante.findByNombre", query = "SELECT c FROM CpEstudiante c WHERE c.nombre = :nombre"),
        @NamedQuery(name = "CpEstudiante.findByStatuss", query = "SELECT c FROM CpEstudiante c WHERE c.statuss = :statuss"),
        @NamedQuery(name = "CpEstudiante.findByTxUser", query = "SELECT c FROM CpEstudiante c WHERE c.txUser = :txUser"),
        @NamedQuery(name = "CpEstudiante.findByTxDate", query = "SELECT c FROM CpEstudiante c WHERE c.txDate = :txDate")})
*/public class CpEstudianteEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "h_estud_id")
    private Integer hEstudId;
    @Basic(optional = false)
    @Column(name = "id_estudiante")
    private int idEstudiante;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "statuss")
    private int statuss;
    @Column(name = "tx_user")
    private String txUser;
    @Column(name = "tx_date")
    @Temporal(TemporalType.DATE)
    private Date txDate;

    public CpEstudianteEntity() {
    }

    public CpEstudianteEntity(Integer hEstudId) {
        this.hEstudId = hEstudId;
    }

    public CpEstudianteEntity(Integer hEstudId, int idEstudiante, String nombre, int statuss) {
        this.hEstudId = hEstudId;
        this.idEstudiante = idEstudiante;
        this.nombre = nombre;
        this.statuss = statuss;
    }

    public Integer getHEstudId() {
        return hEstudId;
    }

    public void setHEstudId(Integer hEstudId) {
        this.hEstudId = hEstudId;
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

    public int getStatuss() {
        return statuss;
    }

    public void setStatuss(int statuss) {
        this.statuss = statuss;
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
        hash += (hEstudId != null ? hEstudId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CpEstudianteEntity)) {
            return false;
        }
        CpEstudianteEntity other = (CpEstudianteEntity) object;
        if ((this.hEstudId == null && other.hEstudId != null) || (this.hEstudId != null && !this.hEstudId.equals(other.hEstudId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectokajoy.ucb.edu.bo.CpEstudiante[ hEstudId=" + hEstudId + " ]";
    }

}
