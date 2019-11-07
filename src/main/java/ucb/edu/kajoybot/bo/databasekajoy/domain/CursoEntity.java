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
@Table(name = "curso")
/*@NamedQueries({
        @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
        @NamedQuery(name = "Curso.findByIdCurso", query = "SELECT c FROM Curso c WHERE c.idCurso = :idCurso"),
        @NamedQuery(name = "Curso.findByNombre", query = "SELECT c FROM Curso c WHERE c.nombre = :nombre"),
        @NamedQuery(name = "Curso.findByTipoCurso", query = "SELECT c FROM Curso c WHERE c.tipoCurso = :tipoCurso"),
        @NamedQuery(name = "Curso.findByClave", query = "SELECT c FROM Curso c WHERE c.clave = :clave")})
*/public class CursoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_curso")
    private Integer idCurso;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "tipo_curso")
    private String tipoCurso;
    @Column(name = "clave")
    private String clave;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCurso")
    private Collection<TestEntity> testCollection;
    @JoinColumn(name = "id_docente", referencedColumnName = "id_docente")
    @ManyToOne(optional = false)
    private DocenteEntity idDocente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCurso")
    private Collection<EstudianteCursoEntity> estudianteCursoCollection;

    public CursoEntity() {
    }

    public CursoEntity(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public CursoEntity(Integer idCurso, String nombre, String tipoCurso) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.tipoCurso = tipoCurso;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Collection<TestEntity> getTestCollection() {
        return testCollection;
    }

    public void setTestCollection(Collection<TestEntity> testCollection) {
        this.testCollection = testCollection;
    }

    public DocenteEntity getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(DocenteEntity idDocente) {
        this.idDocente = idDocente;
    }

    public Collection<EstudianteCursoEntity> getEstudianteCursoCollection() {
        return estudianteCursoCollection;
    }

    public void setEstudianteCursoCollection(Collection<EstudianteCursoEntity> estudianteCursoCollection) {
        this.estudianteCursoCollection = estudianteCursoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCurso != null ? idCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoEntity)) {
            return false;
        }
        CursoEntity other = (CursoEntity) object;
        if ((this.idCurso == null && other.idCurso != null) || (this.idCurso != null && !this.idCurso.equals(other.idCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CursoEntity{" +
                "nombre="+nombre+
                ", tipoCurso='"+tipoCurso+
                '}';
    }

}
