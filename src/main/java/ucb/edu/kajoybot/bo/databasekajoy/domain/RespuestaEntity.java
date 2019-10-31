package ucb.edu.kajoybot.bo.databasekajoy.domain;

import javax.persistence.*;

@Entity
@Table(name = "respuesta", schema = "kajoydatabase", catalog = "")
public class RespuestaEntity {
    private int idResp;
    private int numResp;
    private String cRespuesta;
    private int esCorrecta;

    @Id
    @Column(name = "id_resp")
    public int getIdResp() {
        return idResp;
    }

    public void setIdResp(int idResp) {
        this.idResp = idResp;
    }

    @Basic
    @Column(name = "num_resp")
    public int getNumResp() {
        return numResp;
    }

    public void setNumResp(int numResp) {
        this.numResp = numResp;
    }

    @Basic
    @Column(name = "c_respuesta")
    public String getcRespuesta() {
        return cRespuesta;
    }

    public void setcRespuesta(String cRespuesta) {
        this.cRespuesta = cRespuesta;
    }

    @Basic
    @Column(name = "es_correcta")
    public int getEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(int esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RespuestaEntity that = (RespuestaEntity) o;

        if (idResp != that.idResp) return false;
        if (numResp != that.numResp) return false;
        if (esCorrecta != that.esCorrecta) return false;
        if (cRespuesta != null ? !cRespuesta.equals(that.cRespuesta) : that.cRespuesta != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idResp;
        result = 31 * result + numResp;
        result = 31 * result + (cRespuesta != null ? cRespuesta.hashCode() : 0);
        result = 31 * result + esCorrecta;
        return result;
    }
}
