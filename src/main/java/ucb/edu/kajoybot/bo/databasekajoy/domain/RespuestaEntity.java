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
        @NamedQuery(name = "Respuesta.findByIdRespuesta", query = "SELECT r FROM Respuesta r WHERE r.idRespuesta = :idRespuesta"),
        @NamedQuery(name = "Respuesta.findByNumeroRespuesta", query = "SELECT r FROM Respuesta r WHERE r.numeroRespuesta = :numeroRespuesta"),
        @NamedQuery(name = "Respuesta.findByContenidoRespuesta", query = "SELECT r FROM Respuesta r WHERE r.contenidoRespuesta = :contenidoRespuesta"),
        @NamedQuery(name = "Respuesta.findByEsCorrecta", query = "SELECT r FROM Respuesta r WHERE r.esCorrecta = :esCorrecta")})
*/public class RespuestaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_respuesta")
    private Integer idRespuesta;
    @Basic(optional = false)
    @Column(name = "numero_respuesta")
    private int numeroRespuesta;
    @Basic(optional = false)
    @Column(name = "contenido_respuesta")
    private String contenidoRespuesta;
    @Basic(optional = false)
    @Column(name = "es_correcta")
    private int esCorrecta;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta")
    @ManyToOne(optional = false)
    private PreguntaEntity idPregunta;

    public RespuestaEntity() {
    }

    public RespuestaEntity(Integer idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public RespuestaEntity(Integer idRespuesta, int numeroRespuesta, String contenidoRespuesta, int esCorrecta) {
        this.idRespuesta = idRespuesta;
        this.numeroRespuesta = numeroRespuesta;
        this.contenidoRespuesta = contenidoRespuesta;
        this.esCorrecta = esCorrecta;
    }

    public Integer getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(Integer idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public int getNumeroRespuesta() {
        return numeroRespuesta;
    }

    public void setNumeroRespuesta(int numeroRespuesta) {
        this.numeroRespuesta = numeroRespuesta;
    }

    public String getContenidoRespuesta() {
        return contenidoRespuesta;
    }

    public void setContenidoRespuesta(String contenidoRespuesta) {
        this.contenidoRespuesta = contenidoRespuesta;
    }

    public int getEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(int esCorrecta) {
        this.esCorrecta = esCorrecta;
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
        hash += (idRespuesta != null ? idRespuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaEntity)) {
            return false;
        }
        RespuestaEntity other = (RespuestaEntity) object;
        if ((this.idRespuesta == null && other.idRespuesta != null) || (this.idRespuesta != null && !this.idRespuesta.equals(other.idRespuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectokajoy.ucb.edu.bo.clases.Respuesta[ idRespuesta=" + idRespuesta + " ]";
    }

}
