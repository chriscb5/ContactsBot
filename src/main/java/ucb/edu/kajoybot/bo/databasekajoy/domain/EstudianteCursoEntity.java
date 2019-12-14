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
@Table(name = "estudiante_curso")
/*@NamedQueries({
        @NamedQuery(name = "EstudianteCurso.findAll", query = "SELECT e FROM EstudianteCurso e"),
        @NamedQuery(name = "EstudianteCurso.findByIdEstudianteCurso", query = "SELECT e FROM EstudianteCurso e WHERE e.idEstudianteCurso = :idEstudianteCurso")})
*/public class EstudianteCursoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estudiante_curso")
    private Integer idEstudianteCurso;
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso")
    @ManyToOne(optional = false)
    private CursoEntity idCurso;
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id_estudiante")
    @ManyToOne(optional = false)
    private EstudianteEntity idEstudiante;

    public EstudianteCursoEntity() {
    }

    public EstudianteCursoEntity(Integer idEstudianteCurso) {
        this.idEstudianteCurso = idEstudianteCurso;
    }

    public Integer getIdEstudianteCurso() {
        return idEstudianteCurso;
    }

    public void setIdEstudianteCurso(Integer idEstudianteCurso) {
        this.idEstudianteCurso = idEstudianteCurso;
    }

    public CursoEntity getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(CursoEntity idCurso) {
        this.idCurso = idCurso;
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
        hash += (idEstudianteCurso != null ? idEstudianteCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudianteCursoEntity)) {
            return false;
        }
        EstudianteCursoEntity other = (EstudianteCursoEntity) object;
        if ((this.idEstudianteCurso == null && other.idEstudianteCurso != null) || (this.idEstudianteCurso != null && !this.idEstudianteCurso.equals(other.idEstudianteCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "proyectokajoy.ucb.edu.bo.clases.EstudianteCurso[ idEstudianteCurso=" + idEstudianteCurso + " ]";
        return "EstudianteCursoEntity{" +
                "idEstudianteCurso=" + idEstudianteCurso +
                ", idEstudiante='" + idEstudiante + '\'' +
                ", idCurso='" + idCurso + '\'' +
                '}';
    }

}
