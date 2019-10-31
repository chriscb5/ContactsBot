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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ASUS
*/
@Entity
@Table(name = "estudiante_test")
/*@NamedQueries({
        @NamedQuery(name = "EstudianteTest.findAll", query = "SELECT e FROM EstudianteTest e"),
        @NamedQuery(name = "EstudianteTest.findByIdEstTest", query = "SELECT e FROM EstudianteTest e WHERE e.idEstTest = :idEstTest"),
        @NamedQuery(name = "EstudianteTest.findByPuntaje", query = "SELECT e FROM EstudianteTest e WHERE e.puntaje = :puntaje")})
*/public class EstudianteTestEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_est_test")
    private Integer idEstTest;
    @Basic(optional = false)
    @Column(name = "puntaje")
    private int puntaje;
    @JoinColumn(name = "estudiante_id_user", referencedColumnName = "id_estudiante")
    @ManyToOne(optional = false)
    private EstudianteEntity estudianteIdUser;
    @JoinColumn(name = "test_id_test", referencedColumnName = "id_test")
    @ManyToOne(optional = false)
    private TestEntity testIdTest;

    public EstudianteTestEntity() {
    }

    public EstudianteTestEntity(Integer idEstTest) {
        this.idEstTest = idEstTest;
    }

    public EstudianteTestEntity(Integer idEstTest, int puntaje) {
        this.idEstTest = idEstTest;
        this.puntaje = puntaje;
    }

    public Integer getIdEstTest() {
        return idEstTest;
    }

    public void setIdEstTest(Integer idEstTest) {
        this.idEstTest = idEstTest;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public EstudianteEntity getEstudianteIdUser() {
        return estudianteIdUser;
    }

    public void setEstudianteIdUser(EstudianteEntity estudianteIdUser) {
        this.estudianteIdUser = estudianteIdUser;
    }

    public TestEntity getTestIdTest() {
        return testIdTest;
    }

    public void setTestIdTest(TestEntity testIdTest) {
        this.testIdTest = testIdTest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstTest != null ? idEstTest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudianteTestEntity)) {
            return false;
        }
        EstudianteTestEntity other = (EstudianteTestEntity) object;
        if ((this.idEstTest == null && other.idEstTest != null) || (this.idEstTest != null && !this.idEstTest.equals(other.idEstTest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectokajoy.ucb.edu.bo.EstudianteTest[ idEstTest=" + idEstTest + " ]";
    }

}
