package ucb.edu.kajoybot.bo.databasekajoy.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "estudiante", schema = "kajoydatabase", catalog = "")
/*@NamedQueries({
        @NamedQuery(name = "EstdianteEntity.findAll", query = "SELECT c FROM Estudiante c"),
        @NamedQuery(name = "CpPerson.findByPersonId", query = "SELECT c FROM CpPerson c WHERE c.personId = :personId"),
        @NamedQuery(name = "CpPerson.findByFirstName", query = "SELECT c FROM CpPerson c WHERE c.firstName = :firstName"),
        @NamedQuery(name = "CpPerson.findBySecondName", query = "SELECT c FROM CpPerson c WHERE c.secondName = :secondName"),
        @NamedQuery(name = "CpPerson.findByThirdName", query = "SELECT c FROM CpPerson c WHERE c.thirdName = :thirdName"),
        @NamedQuery(name = "CpPerson.findByFirstSurname", query = "SELECT c FROM CpPerson c WHERE c.firstSurname = :firstSurname"),
        @NamedQuery(name = "CpPerson.findBySecondSurname", query = "SELECT c FROM CpPerson c WHERE c.secondSurname = :secondSurname"),
        @NamedQuery(name = "CpPerson.findByThirdSurname", query = "SELECT c FROM CpPerson c WHERE c.thirdSurname = :thirdSurname"),
        @NamedQuery(name = "CpPerson.findByStatus", query = "SELECT c FROM CpPerson c WHERE c.status = :status"),
        @NamedQuery(name = "CpPerson.findByTxUser", query = "SELECT c FROM CpPerson c WHERE c.txUser = :txUser"),
        @NamedQuery(name = "CpPerson.findByTxHost", query = "SELECT c FROM CpPerson c WHERE c.txHost = :txHost"),
        @NamedQuery(name = "CpPerson.findByTxDate", query = "SELECT c FROM CpPerson c WHERE c.txDate = :txDate")
})*/
public class EstudianteEntity {

    @Id
    @Column(name = "id_estudiante")
    private int idEstudiante;

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }
    
    @Basic
    @Column(name = "nombre")
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "status")
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "tx_user")
    private String txUser;

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    @Basic
    @Column(name = "tx_date")
    private Date txDate;

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudianteId", fetch = FetchType.LAZY)
//    private List<EstudianteCursoEntity> estudianteCursoEntityList;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstudianteEntity that = (EstudianteEntity) o;

        if (idEstudiante != that.idEstudiante) return false;
        if (status != that.status) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (txUser != null ? !txUser.equals(that.txUser) : that.txUser != null) return false;
        if (txDate != null ? !txDate.equals(that.txDate) : that.txDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEstudiante;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (txUser != null ? txUser.hashCode() : 0);
        result = 31 * result + (txDate != null ? txDate.hashCode() : 0);
        return result;
    }
}
