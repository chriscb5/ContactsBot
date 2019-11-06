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
        @NamedQuery(name = "EstudianteTest.findByIdEstudianteTest", query = "SELECT e FROM EstudianteTest e WHERE e.idEstudianteTest = :idEstudianteTest"),
        @NamedQuery(name = "EstudianteTest.findByPuntaje", query = "SELECT e FROM EstudianteTest e WHERE e.puntaje = :puntaje")})
*/public class EstudianteTestEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estudiante_test")
    private Integer idEstudianteTest;
    @Basic(optional = false)
    @Column(name = "puntaje")
    private int puntaje;
    @JoinColumn(name = "id_test", referencedColumnName = "id_test")
    @ManyToOne(optional = false)
    private TestEntity idTest;
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id_estudiante")
    @ManyToOne(optional = false)
    private EstudianteEntity idEstudiante;

    public EstudianteTestEntity() {
    }

    public EstudianteTestEntity(Integer idEstudianteTest) {
        this.idEstudianteTest = idEstudianteTest;
    }

    public EstudianteTestEntity(Integer idEstudianteTest, int puntaje) {
        this.idEstudianteTest = idEstudianteTest;
        this.puntaje = puntaje;
    }

    public Integer getIdEstudianteTest() {
        return idEstudianteTest;
    }

    public void setIdEstudianteTest(Integer idEstudianteTest) {
        this.idEstudianteTest = idEstudianteTest;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public TestEntity getIdTest() {
        return idTest;
    }

    public void setIdTest(TestEntity idTest) {
        this.idTest = idTest;
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
        hash += (idEstudianteTest != null ? idEstudianteTest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudianteTestEntity)) {
            return false;
        }
        EstudianteTestEntity other = (EstudianteTestEntity) object;
        if ((this.idEstudianteTest == null && other.idEstudianteTest != null) || (this.idEstudianteTest != null && !this.idEstudianteTest.equals(other.idEstudianteTest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectokajoy.ucb.edu.bo.clases.EstudianteTest[ idEstudianteTest=" + idEstudianteTest + " ]";
    }

}
