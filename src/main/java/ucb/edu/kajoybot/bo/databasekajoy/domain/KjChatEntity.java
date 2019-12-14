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
@Table(name = "kj_chat")
/*@NamedQueries({
        @NamedQuery(name = "KjChat.findAll", query = "SELECT k FROM KjChat k"),
        @NamedQuery(name = "KjChat.findByKjchatId", query = "SELECT k FROM KjChat k WHERE k.kjchatId = :kjchatId"),
        @NamedQuery(name = "KjChat.findByInMessage", query = "SELECT k FROM KjChat k WHERE k.inMessage = :inMessage"),
        @NamedQuery(name = "KjChat.findByOutMessage", query = "SELECT k FROM KjChat k WHERE k.outMessage = :outMessage"),
        @NamedQuery(name = "KjChat.findByMsgDate", query = "SELECT k FROM KjChat k WHERE k.msgDate = :msgDate"),
        @NamedQuery(name = "KjChat.findByTxUser", query = "SELECT k FROM KjChat k WHERE k.txUser = :txUser"),
        @NamedQuery(name = "KjChat.findByTxHost", query = "SELECT k FROM KjChat k WHERE k.txHost = :txHost"),
        @NamedQuery(name = "KjChat.findByTxDate", query = "SELECT k FROM KjChat k WHERE k.txDate = :txDate")})
*/public class KjChatEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "kjchat_id")
    private Integer kjchatId;
    @Basic(optional = false)
    @Column(name = "in_message")
    private String inMessage;
    @Column(name = "out_message")
    private String outMessage;
    @Basic(optional = false)
    @Column(name = "msg_date")
    @Temporal(TemporalType.DATE)
    private Date msgDate;
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
    @JoinColumn(name = "kjuserid", referencedColumnName = "userid")
    @ManyToOne(optional = false)
    private KjUserEntity kjuserid;
    /*    @JoinColumn(name = "kjuserid", referencedColumnName = "userid")
    @ManyToOne(optional = false)
    private KjEstudianteUserEntity kjuserid;
*/
    public KjChatEntity() {
    }

    public KjChatEntity(Integer kjchatId) {
        this.kjchatId = kjchatId;
    }

    public KjChatEntity(Integer kjchatId, String inMessage, Date msgDate, String txUser, String txHost, Date txDate) {
        this.kjchatId = kjchatId;
        this.inMessage = inMessage;
        this.msgDate = msgDate;
        this.txUser = txUser;
        this.txHost = txHost;
        this.txDate = txDate;
    }

    public Integer getKjchatId() {
        return kjchatId;
    }

    public void setKjchatId(Integer kjchatId) {
        this.kjchatId = kjchatId;
    }

    public String getInMessage() {
        return inMessage;
    }

    public void setInMessage(String inMessage) {
        this.inMessage = inMessage;
    }

    public String getOutMessage() {
        return outMessage;
    }

    public void setOutMessage(String outMessage) {
        this.outMessage = outMessage;
    }

    public Date getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Date msgDate) {
        this.msgDate = msgDate;
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
/*
    public KjEstudianteUserEntity getKjuserid() {
        return kjuserid;
    }

    public void setKjuserid(KjEstudianteUserEntity kjuserid) {
        this.kjuserid = kjuserid;
    }*/
    public KjUserEntity getKjuserid() {
        return kjuserid;
    }

    public void setKjuserid(KjUserEntity kjuserid) {
        this.kjuserid = kjuserid;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kjchatId != null ? kjchatId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KjChatEntity)) {
            return false;
        }
        KjChatEntity other = (KjChatEntity) object;
        if ((this.kjchatId == null && other.kjchatId != null) || (this.kjchatId != null && !this.kjchatId.equals(other.kjchatId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectokajoy.ucb.edu.bo.clases.KjChat[ kjchatId=" + kjchatId + " ]";
    }

}
