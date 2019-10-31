package ucb.edu.kajoybot.bo.databasekajoy.domain;

import javax.persistence.*;

@Entity
@Table(name = "estudiante_test", schema = "kajoydatabase", catalog = "")
public class EstudianteTestEntity {
    private int idEstTest;
    private int puntaje;

    @Id
    @Column(name = "id_est_test")
    public int getIdEstTest() {
        return idEstTest;
    }

    public void setIdEstTest(int idEstTest) {
        this.idEstTest = idEstTest;
    }

    @Basic
    @Column(name = "puntaje")
    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstudianteTestEntity that = (EstudianteTestEntity) o;

        if (idEstTest != that.idEstTest) return false;
        if (puntaje != that.puntaje) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEstTest;
        result = 31 * result + puntaje;
        return result;
    }
}
