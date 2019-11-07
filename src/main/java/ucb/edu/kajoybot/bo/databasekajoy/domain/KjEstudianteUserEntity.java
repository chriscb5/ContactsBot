package ucb.edu.kajoybot.bo.databasekajoy.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "kjestudianteuser")
/*@NamedQueries({
        @NamedQuery(name = "Kjestudianteuser.findAll", query = "SELECT k FROM Kjestudianteuser k"),
        @NamedQuery(name = "Kjestudianteuser.findByUserid", query = "SELECT k FROM Kjestudianteuser k WHERE k.userid = :userid"),
        @NamedQuery(name = "Kjestudianteuser.findByBotUserId", query = "SELECT k FROM Kjestudianteuser k WHERE k.botUserId = :botUserId"),
        @NamedQuery(name = "Kjestudianteuser.findByTxUser", query = "SELECT k FROM Kjestudianteuser k WHERE k.txUser = :txUser"),
        @NamedQuery(name = "Kjestudianteuser.findByTxHost", query = "SELECT k FROM Kjestudianteuser k WHERE k.txHost = :txHost"),
        @NamedQuery(name = "Kjestudianteuser.findByTxDate", query = "SELECT k FROM Kjestudianteuser k WHERE k.txDate = :txDate")})
*/public class KjEstudianteUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userid")
    private Integer userid;
    @Basic(optional = false)
    @Column(name = "bot_user_id")
    private String botUserId;
    @Basic(optional = false)
    @Column(name = "tx_user")
    private String txUser;
    @Basic(optional = false)
    @Column(name = "tx_host")
    private String txHost;
    @Basic(optional = false)
    @Column(name = "tx_date")
    @Temporal(TemporalType.DATE)
    private Date txDate;
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id_estudiante")
    @ManyToOne(optional = false)
    private EstudianteEntity idEstudiante;

    public KjEstudianteUserEntity() {
    }

    public KjEstudianteUserEntity(Integer userid) {
        this.userid = userid;
    }

    public KjEstudianteUserEntity(Integer userid, String botUserId, String txUser, String txHost, Date txDate) {
        this.userid = userid;
        this.botUserId = botUserId;
        this.txUser = txUser;
        this.txHost = txHost;
        this.txDate = txDate;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getBotUserId() {
        return botUserId;
    }

    public void setBotUserId(String botUserId) {
        this.botUserId = botUserId;
    }

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public EstudianteEntity getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(EstudianteEntity idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KjEstudianteUserEntity)) {
            return false;
        }
        KjEstudianteUserEntity other = (KjEstudianteUserEntity) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectokajoy.ucb.edu.bo.clases.Kjestudianteuser[ userid=" + userid + " ]";
    }

}
