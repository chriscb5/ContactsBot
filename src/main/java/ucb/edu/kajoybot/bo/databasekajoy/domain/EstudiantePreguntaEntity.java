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
@Table(name = "estudiante_pregunta")
/*@NamedQueries({
        @NamedQuery(name = "EstudiantePregunta.findAll", query = "SELECT e FROM EstudiantePregunta e"),
        @NamedQuery(name = "EstudiantePregunta.findByIdEstudiantePregunta", query = "SELECT e FROM EstudiantePregunta e WHERE e.idEstudiantePregunta = :idEstudiantePregunta"),
        @NamedQuery(name = "EstudiantePregunta.findByIsCorrect", query = "SELECT e FROM EstudiantePregunta e WHERE e.isCorrect = :isCorrect")})
*/public class EstudiantePreguntaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estudiante_pregunta")
    private Integer idEstudiantePregunta;
    @Column(name = "is_correct")
    private Integer isCorrect;
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id_estudiante")
    @ManyToOne(optional = false)
    private EstudianteEntity idEstudiante;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta")
    @ManyToOne(optional = false)
    private PreguntaEntity idPregunta;

    public EstudiantePreguntaEntity() {
    }

    public EstudiantePreguntaEntity(Integer idEstudiantePregunta) {
        this.idEstudiantePregunta = idEstudiantePregunta;
    }

    public Integer getIdEstudiantePregunta() {
        return idEstudiantePregunta;
    }

    public void setIdEstudiantePregunta(Integer idEstudiantePregunta) {
        this.idEstudiantePregunta = idEstudiantePregunta;
    }

    public Integer getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Integer isCorrect) {
        this.isCorrect = isCorrect;
    }

    public EstudianteEntity getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(EstudianteEntity idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public PreguntaEntity getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(PreguntaEntity idPregunta) {
        this.idPregunta = idPregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstudiantePregunta != null ? idEstudiantePregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudiantePreguntaEntity)) {
            return false;
        }
        EstudiantePreguntaEntity other = (EstudiantePreguntaEntity) object;
        if ((this.idEstudiantePregunta == null && other.idEstudiantePregunta != null) || (this.idEstudiantePregunta != null && !this.idEstudiantePregunta.equals(other.idEstudiantePregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectokajoy.ucb.edu.bo.clases.EstudiantePregunta[ idEstudiantePregunta=" + idEstudiantePregunta + " ]";
    }

}
