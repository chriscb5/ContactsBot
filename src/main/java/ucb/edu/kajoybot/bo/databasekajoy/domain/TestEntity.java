package ucb.edu.kajoybot.bo.databasekajoy.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ASUS
*/
@Entity
@Table(name = "test")
 /*   @NamedQueries({
        @NamedQuery(name = "Test.findAll", query = "SELECT t FROM Test t"),
        @NamedQuery(name = "Test.findByIdTest", query = "SELECT t FROM Test t WHERE t.idTest = :idTest"),
        @NamedQuery(name = "Test.findByNombreTest", query = "SELECT t FROM Test t WHERE t.nombreTest = :nombreTest")})
*/public class TestEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_test")
    private Integer idTest;
    @Basic(optional = false)
    @Column(name = "nombre_test")
    private String nombreTest;
    @JoinColumn(name = "Curso_id_curso", referencedColumnName = "id_curso")
    @ManyToOne(optional = false)
    private CursoEntity cursoidcurso;
    @JoinColumn(name = "docente_id_docente", referencedColumnName = "id_docente")
    @ManyToOne(optional = false)
    private DocenteEntity docenteIdDocente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testIdTest")
    private Collection<EstudianteTestEntity> estudianteTestCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testidtest")
    private Collection<PreguntaEntity> preguntaCollection;

    public TestEntity() {
    }

    public TestEntity(Integer idTest) {
        this.idTest = idTest;
    }

    public TestEntity(Integer idTest, String nombreTest) {
        this.idTest = idTest;
        this.nombreTest = nombreTest;
    }

    public Integer getIdTest() {
        return idTest;
    }

    public void setIdTest(Integer idTest) {
        this.idTest = idTest;
    }

    public String getNombreTest() {
        return nombreTest;
    }

    public void setNombreTest(String nombreTest) {
        this.nombreTest = nombreTest;
    }

    public CursoEntity getCursoidcurso() {
        return cursoidcurso;
    }

    public void setCursoidcurso(CursoEntity cursoidcurso) {
        this.cursoidcurso = cursoidcurso;
    }

    public DocenteEntity getDocenteIdDocente() {
        return docenteIdDocente;
    }

    public void setDocenteIdDocente(DocenteEntity docenteIdDocente) {
        this.docenteIdDocente = docenteIdDocente;
    }

    public Collection<EstudianteTestEntity> getEstudianteTestCollection() {
        return estudianteTestCollection;
    }

    public void setEstudianteTestCollection(Collection<EstudianteTestEntity> estudianteTestCollection) {
        this.estudianteTestCollection = estudianteTestCollection;
    }

    public Collection<PreguntaEntity> getPreguntaCollection() {
        return preguntaCollection;
    }

    public void setPreguntaCollection(Collection<PreguntaEntity> preguntaCollection) {
        this.preguntaCollection = preguntaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTest != null ? idTest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestEntity)) {
            return false;
        }
        TestEntity other = (TestEntity) object;
        if ((this.idTest == null && other.idTest != null) || (this.idTest != null && !this.idTest.equals(other.idTest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectokajoy.ucb.edu.bo.Test[ idTest=" + idTest + " ]";
    }

}
