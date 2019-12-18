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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "contact")
//@NamedQueries({
//        @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c"),
//        @NamedQuery(name = "Contact.findByContactId", query = "SELECT c FROM Contact c WHERE c.contactId = :contactId"),
//        @NamedQuery(name = "Contact.findByFirstName", query = "SELECT c FROM Contact c WHERE c.firstName = :firstName"),
//        @NamedQuery(name = "Contact.findBySecondName", query = "SELECT c FROM Contact c WHERE c.secondName = :secondName"),
//        @NamedQuery(name = "Contact.findByFirstSurname", query = "SELECT c FROM Contact c WHERE c.firstSurname = :firstSurname"),
//        @NamedQuery(name = "Contact.findBySecondSurname", query = "SELECT c FROM Contact c WHERE c.secondSurname = :secondSurname"),
//        @NamedQuery(name = "Contact.findByEmail", query = "SELECT c FROM Contact c WHERE c.email = :email"),
//        @NamedQuery(name = "Contact.findByBirthdate", query = "SELECT c FROM Contact c WHERE c.birthdate = :birthdate"),
//        @NamedQuery(name = "Contact.findByImage", query = "SELECT c FROM Contact c WHERE c.image = :image"),
//        @NamedQuery(name = "Contact.findByStatus", query = "SELECT c FROM Contact c WHERE c.status = :status")})
public class ContactEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "contact_id")
    private Integer contactId;
    @Basic(optional = false)
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "second_name")
    private String secondName;
    @Basic(optional = false)
    @Column(name = "first_surname")
    private String firstSurname;
    @Basic(optional = false)
    @Column(name = "second_surname")
    private String secondSurname;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Basic(optional = false)
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private KjUserEntity userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contactId")
    private List<PhoneNumber> phoneNumberList;



    public ContactEntity() {

    }

    public ContactEntity(Integer contactId) {
        this.contactId = contactId;
    }

    public ContactEntity(Integer contactId, String firstName, String firstSurname, String secondSurname, String email, Date birthdate, String image, int status) {
        this.contactId = contactId;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.email = email;
        this.birthdate = birthdate;
        this.image = image;
        this.status = status;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }



    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }



    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }







    public int getStatus() {
        return status;
    }




















    public void setStatus(int status) {
        this.status = status;
    }

    public KjUserEntity getUserId() {










        return userId;
    }

    public void setUserId(KjUserEntity userId) {
        this.userId = userId;


    }

    public List<PhoneNumber> getPhoneNumberList() {
        return phoneNumberList;
    }

    public void setPhoneNumberList(List<PhoneNumber> phoneNumberList) {
        this.phoneNumberList = phoneNumberList;

    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contactId != null ? contactId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContactEntity)) {
            return false;
        }
        ContactEntity other = (ContactEntity) object;
        if ((this.contactId == null && other.contactId != null) || (this.contactId != null && !this.contactId.equals(other.contactId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectokajoy.ucb.edu.bo.clases.Contact[ contactId=" + contactId + " ]";
    }

}