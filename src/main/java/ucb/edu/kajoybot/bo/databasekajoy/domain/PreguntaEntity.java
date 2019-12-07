package ucb.edu.kajoybot.bo.databasekajoy.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "pregunta")
/*@NamedQueries({
        @NamedQuery(name = "Pregunta.findAll", query = "SELECT p FROM Pregunta p"),
        @NamedQuery(name = "Pregunta.findByIdPregunta", query = "SELECT p FROM Pregunta p WHERE p.idPregunta = :idPregunta"),
        @NamedQuery(name = "Pregunta.findByNumeroPregunta", query = "SELECT p FROM Pregunta p WHERE p.numeroPregunta = :numeroPregunta"),
        @NamedQuery(name = "Pregunta.findByContenidoPregunta", query = "SELECT p FROM Pregunta p WHERE p.contenidoPregunta = :contenidoPregunta")})
*/public class PreguntaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pregunta")
    private Integer idPregunta;
    @Basic(optional = false)
    @Column(name = "numero_pregunta")
    private int numeroPregunta;
    @Basic(optional = false)
    @Column(name = "contenido_pregunta")
    private String contenidoPregunta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPregunta")
    private List<RespuestaEntity> respuestaCollection;
    @JoinColumn(name = "id_test", referencedColumnName = "id_test")
    @ManyToOne(optional = false)
    private TestEntity idTest;

    public PreguntaEntity() {
    }

    public PreguntaEntity(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public PreguntaEntity(Integer idPregunta, int numeroPregunta, String contenidoPregunta) {
        this.idPregunta = idPregunta;
        this.numeroPregunta = numeroPregunta;
        this.contenidoPregunta = contenidoPregunta;
    }

    public Integer getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public int getNumeroPregunta() {
        return numeroPregunta;
    }

    public void setNumeroPregunta(int numeroPregunta) {
        this.numeroPregunta = numeroPregunta;
    }

    public String getContenidoPregunta() {
        return contenidoPregunta;
    }

    public void setContenidoPregunta(String contenidoPregunta) {
        this.contenidoPregunta = contenidoPregunta;
    }

    public Collection<RespuestaEntity> getRespuestaList() {
        return respuestaCollection;
    }

    public void setRespuestaList(List<RespuestaEntity> respuestaCollection) {
        this.respuestaCollection = respuestaCollection;
    }

    public TestEntity getIdTest() {
        return idTest;
    }

    public void setIdTest(TestEntity idTest) {
        this.idTest = idTest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPregunta != null ? idPregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreguntaEntity)) {
            return false;
        }
        PreguntaEntity other = (PreguntaEntity) object;
        if ((this.idPregunta == null && other.idPregunta != null) || (this.idPregunta != null && !this.idPregunta.equals(other.idPregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectokajoy.ucb.edu.bo.clases.Pregunta[ idPregunta=" + idPregunta + " ]";
    }

}
