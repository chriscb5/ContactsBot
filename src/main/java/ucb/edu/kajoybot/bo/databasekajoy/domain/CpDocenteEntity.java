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
@Table(name = "cp_docente")
/*@NamedQueries({
        @NamedQuery(name = "CpDocente.findAll", query = "SELECT c FROM CpDocente c"),
        @NamedQuery(name = "CpDocente.findByHDocenteId", query = "SELECT c FROM CpDocente c WHERE c.hDocenteId = :hDocenteId"),
        @NamedQuery(name = "CpDocente.findByIdDocente", query = "SELECT c FROM CpDocente c WHERE c.idDocente = :idDocente"),
        @NamedQuery(name = "CpDocente.findByNombre", query = "SELECT c FROM CpDocente c WHERE c.nombre = :nombre"),
        @NamedQuery(name = "CpDocente.findByApellidoPaterno", query = "SELECT c FROM CpDocente c WHERE c.apellidoPaterno = :apellidoPaterno"),
        @NamedQuery(name = "CpDocente.findByApellidoMaterno", query = "SELECT c FROM CpDocente c WHERE c.apellidoMaterno = :apellidoMaterno"),
        @NamedQuery(name = "CpDocente.findByTxUser", query = "SELECT c FROM CpDocente c WHERE c.txUser = :txUser"),
        @NamedQuery(name = "CpDocente.findByTxDate", query = "SELECT c FROM CpDocente c WHERE c.txDate = :txDate")})
*/public class CpDocenteEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "h_docente_id")
    private Integer hDocenteId;
    @Basic(optional = false)
    @Column(name = "id_docente")
    private int idDocente;
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

    public CpDocenteEntity() {
    }

    public CpDocenteEntity(Integer hDocenteId) {
        this.hDocenteId = hDocenteId;
    }

    public CpDocenteEntity(Integer hDocenteId, int idDocente, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.hDocenteId = hDocenteId;
        this.idDocente = idDocente;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public Integer getHDocenteId() {
        return hDocenteId;
    }

    public void setHDocenteId(Integer hDocenteId) {
        this.hDocenteId = hDocenteId;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
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
        hash += (hDocenteId != null ? hDocenteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CpDocenteEntity)) {
            return false;
        }
        CpDocenteEntity other = (CpDocenteEntity) object;
        if ((this.hDocenteId == null && other.hDocenteId != null) || (this.hDocenteId != null && !this.hDocenteId.equals(other.hDocenteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectokajoy.ucb.edu.bo.clases.CpDocente[ hDocenteId=" + hDocenteId + " ]";
    }

}
