package ucb.edu.kajoybot.bo.databasekajoy.domain;

import javax.persistence.*;

@Entity
@Table(name = "pregunta", schema = "kajoydatabase", catalog = "")
public class PreguntaEntity {
    private int idPregunta;
    private int numPreg;
    private String cPregunta;

    @Id
    @Column(name = "id_pregunta")
    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    @Basic
    @Column(name = "num_preg")
    public int getNumPreg() {
        return numPreg;
    }

    public void setNumPreg(int numPreg) {
        this.numPreg = numPreg;
    }

    @Basic
    @Column(name = "c_pregunta")
    public String getcPregunta() {
        return cPregunta;
    }

    public void setcPregunta(String cPregunta) {
        this.cPregunta = cPregunta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PreguntaEntity that = (PreguntaEntity) o;

        if (idPregunta != that.idPregunta) return false;
        if (numPreg != that.numPreg) return false;
        if (cPregunta != null ? !cPregunta.equals(that.cPregunta) : that.cPregunta != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPregunta;
        result = 31 * result + numPreg;
        result = 31 * result + (cPregunta != null ? cPregunta.hashCode() : 0);
        return result;
    }
}
