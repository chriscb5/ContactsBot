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
        @NamedQuery(name = "EstudianteCurso.findByIdUserCurse", query = "SELECT e FROM EstudianteCurso e WHERE e.idUserCurse = :idUserCurse")})
*/public class EstudianteCursoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user_curse")
    private Integer idUserCurse;
    @JoinColumn(name = "Curso_id_curso", referencedColumnName = "id_curso")
    @ManyToOne(optional = false)
    private CursoEntity cursoidcurso;
    @JoinColumn(name = "Usuario_id_user", referencedColumnName = "id_estudiante")
    @ManyToOne(optional = false)
    private EstudianteEntity usuarioiduser;

    public EstudianteCursoEntity() {
    }

    public EstudianteCursoEntity(Integer idUserCurse) {
        this.idUserCurse = idUserCurse;
    }

    public Integer getIdUserCurse() {
        return idUserCurse;
    }

    public void setIdUserCurse(Integer idUserCurse) {
        this.idUserCurse = idUserCurse;
    }

    public CursoEntity getCursoidcurso() {
        return cursoidcurso;
    }

    public void setCursoidcurso(CursoEntity cursoidcurso) {
        this.cursoidcurso = cursoidcurso;
    }

    public EstudianteEntity getUsuarioiduser() {
        return usuarioiduser;
    }

    public void setUsuarioiduser(EstudianteEntity usuarioiduser) {
        this.usuarioiduser = usuarioiduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUserCurse != null ? idUserCurse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudianteCursoEntity)) {
            return false;
        }
        EstudianteCursoEntity other = (EstudianteCursoEntity) object;
        if ((this.idUserCurse == null && other.idUserCurse != null) || (this.idUserCurse != null && !this.idUserCurse.equals(other.idUserCurse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectokajoy.ucb.edu.bo.EstudianteCurso[ idUserCurse=" + idUserCurse + " ]";
    }

}
