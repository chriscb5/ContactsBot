package ucb.edu.kajoybot.bo.databasekajoy.domain;

import javax.persistence.*;

@Entity
@Table(name = "test", schema = "kajoydatabase", catalog = "")
public class TestEntity {
    private int idTest;
    private String nombreTest;

    @Id
    @Column(name = "id_test")
    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    @Basic
    @Column(name = "nombre_test")
    public String getNombreTest() {
        return nombreTest;
    }

    public void setNombreTest(String nombreTest) {
        this.nombreTest = nombreTest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestEntity that = (TestEntity) o;

        if (idTest != that.idTest) return false;
        if (nombreTest != null ? !nombreTest.equals(that.nombreTest) : that.nombreTest != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTest;
        result = 31 * result + (nombreTest != null ? nombreTest.hashCode() : 0);
        return result;
    }
}
