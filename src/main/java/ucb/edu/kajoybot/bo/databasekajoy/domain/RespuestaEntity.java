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
@Table(name = "respuesta")
/*@NamedQueries({
        @NamedQuery(name = "Respuesta.findAll", query = "SELECT r FROM Respuesta r"),
        @NamedQuery(name = "Respuesta.findByIdResp", query = "SELECT r FROM Respuesta r WHERE r.idResp = :idResp"),
        @NamedQuery(name = "Respuesta.findByNumResp", query = "SELECT r FROM Respuesta r WHERE r.numResp = :numResp"),
        @NamedQuery(name = "Respuesta.findByCRespuesta", query = "SELECT r FROM Respuesta r WHERE r.cRespuesta = :cRespuesta"),
        @NamedQuery(name = "Respuesta.findByEsCorrecta", query = "SELECT r FROM Respuesta r WHERE r.esCorrecta = :esCorrecta")})
*/public class RespuestaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_resp")
    private Integer idResp;
    @Basic(optional = false)
    @Column(name = "num_resp")
    private int numResp;
    @Basic(optional = false)
    @Column(name = "c_respuesta")
    private String cRespuesta;
    @Basic(optional = false)
    @Column(name = "es_correcta")
    private int esCorrecta;
    @JoinColumn(name = "pregunta_id_pregunta", referencedColumnName = "id_pregunta")
    @ManyToOne(optional = false)
    private PreguntaEntity preguntaIdPregunta;

    public RespuestaEntity() {
    }

    public RespuestaEntity(Integer idResp) {
        this.idResp = idResp;
    }

    public RespuestaEntity(Integer idResp, int numResp, String cRespuesta, int esCorrecta) {
        this.idResp = idResp;
        this.numResp = numResp;
        this.cRespuesta = cRespuesta;
        this.esCorrecta = esCorrecta;
    }

    public Integer getIdResp() {
        return idResp;
    }

    public void setIdResp(Integer idResp) {
        this.idResp = idResp;
    }

    public int getNumResp() {
        return numResp;
    }

    public void setNumResp(int numResp) {
        this.numResp = numResp;
    }

    public String getCRespuesta() {
        return cRespuesta;
    }

    public void setCRespuesta(String cRespuesta) {
        this.cRespuesta = cRespuesta;
    }

    public int getEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(int esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

    public PreguntaEntity getPreguntaIdPregunta() {
        return preguntaIdPregunta;
    }

    public void setPreguntaIdPregunta(PreguntaEntity preguntaIdPregunta) {
        this.preguntaIdPregunta = preguntaIdPregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResp != null ? idResp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaEntity)) {
            return false;
        }
        RespuestaEntity other = (RespuestaEntity) object;
        if ((this.idResp == null && other.idResp != null) || (this.idResp != null && !this.idResp.equals(other.idResp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectokajoy.ucb.edu.bo.Respuesta[ idResp=" + idResp + " ]";
    }

}
