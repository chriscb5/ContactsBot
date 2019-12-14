package ucb.edu.kajoybot.bo.databasekajoy.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "kj_user")
/*@NamedQueries({
        @NamedQuery(name = "KjUser.findAll", query = "SELECT k FROM KjUser k"),
        @NamedQuery(name = "KjUser.findByUserid", query = "SELECT k FROM KjUser k WHERE k.userid = :userid"),
        @NamedQuery(name = "KjUser.findByBotUserId", query = "SELECT k FROM KjUser k WHERE k.botUserId = :botUserId"),
        @NamedQuery(name = "KjUser.findByNombreUser", query = "SELECT k FROM KjUser k WHERE k.nombreUser = :nombreUser"),
        @NamedQuery(name = "KjUser.findByApellidoUser", query = "SELECT k FROM KjUser k WHERE k.apellidoUser = :apellidoUser"),
        @NamedQuery(name = "KjUser.findByTxHost", query = "SELECT k FROM KjUser k WHERE k.txHost = :txHost"),
        @NamedQuery(name = "KjUser.findByTxDate", query = "SELECT k FROM KjUser k WHERE k.txDate = :txDate")})
*/public class KjUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userid")
    private Integer userid;
    @Basic(optional = false)
    @Column(name = "bot_user_id")
    private String botUserId;
    @Column(name = "nombre_user")
    private String nombreUser;
    @Column(name = "apellido_user")
    private String apellidoUser;
    @Basic(optional = false)
    @Column(name = "tx_host")
    private String txHost;
    @Basic(optional = false)
    @Column(name = "tx_date")
    @Temporal(TemporalType.DATE)
    private Date txDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kjuserid")
    private List<KjChatEntity> kjChatList;

    public KjUserEntity() {
    }

    public KjUserEntity(Integer userid) {
        this.userid = userid;
    }

    public KjUserEntity(Integer userid, String botUserId, String txHost, Date txDate) {
        this.userid = userid;
        this.botUserId = botUserId;
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

    public String getNombreUser() {
        return nombreUser;
    }

    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    public String getApellidoUser() {
        return apellidoUser;
    }

    public void setApellidoUser(String apellidoUser) {
        this.apellidoUser = apellidoUser;
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

    public List<KjChatEntity> getKjChatList() {
        return kjChatList;
    }

    public void setKjChatList(List<KjChatEntity> kjChatList) {
        this.kjChatList = kjChatList;
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
        if (!(object instanceof KjUserEntity)) {
            return false;
        }
        KjUserEntity other = (KjUserEntity) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectokajoy.ucb.edu.bo.clases.KjUser[ userid=" + userid + " ]";
    }

}
