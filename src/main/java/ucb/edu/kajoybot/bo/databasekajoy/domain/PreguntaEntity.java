package ucb.edu.kajoybot.bo.databasekajoy.domain;

import java.io.Serializable;
import java.util.Collection;
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
        @NamedQuery(name = "Pregunta.findByNumPreg", query = "SELECT p FROM Pregunta p WHERE p.numPreg = :numPreg"),
        @NamedQuery(name = "Pregunta.findByCPregunta", query = "SELECT p FROM Pregunta p WHERE p.cPregunta = :cPregunta")})
*/public class PreguntaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pregunta")
    private Integer idPregunta;
    @Basic(optional = false)
    @Column(name = "num_preg")
    private int numPreg;
    @Basic(optional = false)
    @Column(name = "c_pregunta")
    private String cPregunta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preguntaIdPregunta")
    private Collection<RespuestaEntity> respuestaCollection;
    @JoinColumn(name = "Test_id_test", referencedColumnName = "id_test")
    @ManyToOne(optional = false)
    private TestEntity testidtest;

    public PreguntaEntity() {
    }

    public PreguntaEntity(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public PreguntaEntity(Integer idPregunta, int numPreg, String cPregunta) {
        this.idPregunta = idPregunta;
        this.numPreg = numPreg;
        this.cPregunta = cPregunta;
    }

    public Integer getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public int getNumPreg() {
        return numPreg;
    }

    public void setNumPreg(int numPreg) {
        this.numPreg = numPreg;
    }

    public String getCPregunta() {
        return cPregunta;
    }

    public void setCPregunta(String cPregunta) {
        this.cPregunta = cPregunta;
    }

    public Collection<RespuestaEntity> getRespuestaCollection() {
        return respuestaCollection;
    }

    public void setRespuestaCollection(Collection<RespuestaEntity> respuestaCollection) {
        this.respuestaCollection = respuestaCollection;
    }

    public TestEntity getTestidtest() {
        return testidtest;
    }

    public void setTestidtest(TestEntity testidtest) {
        this.testidtest = testidtest;
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
        return "proyectokajoy.ucb.edu.bo.Pregunta[ idPregunta=" + idPregunta + " ]";
    }

}
