package ucb.edu.kajoybot.bo.databasekajoy.domain;

import javax.persistence.*;

@Entity
@Table(name = "estudiante_curso", schema = "kajoydatabase", catalog = "")
public class EstudianteCursoEntity {

    @Id
    @Column(name = "id_user_curse")
    private int idUserCurse;

//    @JoinColumn(name = "Usuario_id_user", referencedColumnName = "id_estudiante")
//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    private EstudianteEntity estudianteEntity;

 //   @JoinColumn(name = "Curso_id_curso", referencedColumnName = "id_curso")
  //  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  //  private CursoEntity cursoEntity;



    public int getIdUserCurse() {
        return idUserCurse;
    }

    public void setIdUserCurse(int idUserCurse) {
        this.idUserCurse = idUserCurse;
    }
/*
    public EstudianteEntity getEstudianteEntity() {
        return estudianteEntity;
    }

    public void setEstudianteEntity(EstudianteEntity estudianteEntity) {
        this.estudianteEntity = estudianteEntity;
    }

    public CursoEntity getCursoEntity() {
        return cursoEntity;
    }

    public void setCursoEntity(CursoEntity cursoEntity) {
        this.cursoEntity = cursoEntity;
    }
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstudianteCursoEntity that = (EstudianteCursoEntity) o;

        if (idUserCurse != that.idUserCurse) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idUserCurse;
    }
}
