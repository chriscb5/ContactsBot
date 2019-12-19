package ucb.edu.kajoybot.bo.databasekajoy.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "phone_number")
//@NamedQueries({
//        @NamedQuery(name = "PhoneNumber.findAll", query = "SELECT p FROM PhoneNumber p"),
//        @NamedQuery(name = "PhoneNumber.findByPhoneId", query = "SELECT p FROM PhoneNumber p WHERE p.phoneId = :phoneId"),
//        @NamedQuery(name = "PhoneNumber.findByNumber", query = "SELECT p FROM PhoneNumber p WHERE p.number = :number"),
//        @NamedQuery(name = "PhoneNumber.findByStatus", query = "SELECT p FROM PhoneNumber p WHERE p.status = :status")})
public class PhoneNumberEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "phone_id")
    private Integer phoneId;
    @Basic(optional = false)
    @Column(name = "number")
    private String number;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "contact_id", referencedColumnName = "contact_id")
    @ManyToOne(optional = false)
    private ContactEntity contactId;

    public PhoneNumberEntity() {
    }

    public PhoneNumberEntity(Integer phoneId) {
        this.phoneId = phoneId;
    }

    public PhoneNumberEntity(Integer phoneId, String number, int status) {
        this.phoneId = phoneId;
        this.number = number;
        this.status = status;
    }

    public Integer getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Integer phoneId) {
        this.phoneId = phoneId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ContactEntity getContactId() {
        return contactId;
    }

    public void setContactId(ContactEntity contactId) {
        this.contactId = contactId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (phoneId != null ? phoneId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhoneNumberEntity)) {
            return false;
        }
        PhoneNumberEntity other = (PhoneNumberEntity) object;
        if ((this.phoneId == null && other.phoneId != null) || (this.phoneId != null && !this.phoneId.equals(other.phoneId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
//        return "proyectokajoy.ucb.edu.bo.clases.PhoneNumber[ phoneId=" + phoneId + " ]";
        return "PhoneNumberEntity{" +
                "phone_id=" + phoneId +
                ", number='" + number + '\'' +
                ", contact_id='" + contactId + '\'' +
                ", status=" + status +
                '}';
    }

}
