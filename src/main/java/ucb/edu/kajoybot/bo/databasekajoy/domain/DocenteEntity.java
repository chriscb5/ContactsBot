package ucb.edu.kajoybot.bo.databasekajoy.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
@Table(name = "docente")
/*@NamedQueries({
        @NamedQuery(name = "Docente.findAll", query = "SELECT d FROM Docente d"),
        @NamedQuery(name = "Docente.findByIdDocente", query = "SELECT d FROM Docente d WHERE d.idDocente = :idDocente"),
        @NamedQuery(name = "Docente.findByNombre", query = "SELECT d FROM Docente d WHERE d.nombre = :nombre"),
        @NamedQuery(name = "Docente.findByStatus", query = "SELECT d FROM Docente d WHERE d.status = :status"),
        @NamedQuery(name = "Docente.findByTxUser", query = "SELECT d FROM Docente d WHERE d.txUser = :txUser"),
        @NamedQuery(name = "Docente.findByTxDate", query = "SELECT d FROM Docente d WHERE d.txDate = :txDate")})
*/public class DocenteEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_docente")
    private Integer idDocente;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @Column(name = "tx_user")
    private String txUser;
    @Column(name = "tx_date")
    @Temporal(TemporalType.DATE)
    private Date txDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "docenteIdDocente")
    private Collection<TestEntity> testCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "docenteIdDocente")
    private Collection<CursoEntity> cursoCollection;

    public DocenteEntity() {
    }

    public DocenteEntity(Integer idDocente) {
        this.idDocente = idDocente;
    }

    public DocenteEntity(Integer idDocente, String nombre, int status) {
        this.idDocente = idDocente;
        this.nombre = nombre;
        this.status = status;
    }

    public Integer getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Integer idDocente) {
        this.idDocente = idDocente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public Collection<TestEntity> getTestCollection() {
        return testCollection;
    }

    public void setTestCollection(Collection<TestEntity> testCollection) {
        this.testCollection = testCollection;
    }

    public Collection<CursoEntity> getCursoCollection() {
        return cursoCollection;
    }

    public void setCursoCollection(Collection<CursoEntity> cursoCollection) {
        this.cursoCollection = cursoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocente != null ? idDocente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocenteEntity)) {
            return false;
        }
        DocenteEntity other = (DocenteEntity) object;
        if ((this.idDocente == null && other.idDocente != null) || (this.idDocente != null && !this.idDocente.equals(other.idDocente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectokajoy.ucb.edu.bo.Docente[ idDocente=" + idDocente + " ]";
    }

}
