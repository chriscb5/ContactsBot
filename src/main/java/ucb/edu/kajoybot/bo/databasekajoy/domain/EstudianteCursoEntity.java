package ucb.edu.kajoybot.bo.databasekajoy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estudiante_curso", schema = "kajoydatabase", catalog = "")
public class EstudianteCursoEntity {
    private int idUserCurse;

    @Id
    @Column(name = "id_user_curse")
    public int getIdUserCurse() {
        return idUserCurse;
    }

    public void setIdUserCurse(int idUserCurse) {
        this.idUserCurse = idUserCurse;
    }

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
