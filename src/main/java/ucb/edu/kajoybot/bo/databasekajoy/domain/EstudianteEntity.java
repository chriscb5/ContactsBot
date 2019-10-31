package ucb.edu.kajoybot.bo.databasekajoy.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ASUS
*/
@Entity
@Table(name = "estudiante")
/*@NamedQueries({
        @NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e"),
        @NamedQuery(name = "Estudiante.findByIdEstudiante", query = "SELECT e FROM Estudiante e WHERE e.idEstudiante = :idEstudiante"),
        @NamedQuery(name = "Estudiante.findByNombre", query = "SELECT e FROM Estudiante e WHERE e.nombre = :nombre"),
        @NamedQuery(name = "Estudiante.findByStatus", query = "SELECT e FROM Estudiante e WHERE e.status = :status"),
        @NamedQuery(name = "Estudiante.findByTxUser", query = "SELECT e FROM Estudiante e WHERE e.txUser = :txUser"),
        @NamedQuery(name = "Estudiante.findByTxDate", query = "SELECT e FROM Estudiante e WHERE e.txDate = :txDate")})
*/public class EstudianteEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estudiante")
    private Integer idEstudiante;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    @Column(name = "tx_user")
    private String txUser;
    @Column(name = "tx_date")
    @Temporal(TemporalType.DATE)
    private Date txDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudianteIdUser")
    private Collection<EstudianteTestEntity> estudianteTestCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioiduser")
    private Collection<EstudianteCursoEntity> estudianteCursoCollection;

    public EstudianteEntity() {
    }

    public EstudianteEntity(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public EstudianteEntity(Integer idEstudiante, String nombre, int status) {
        this.idEstudiante = idEstudiante;
        this.nombre = nombre;
        this.status = status;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public Collection<EstudianteTestEntity> getEstudianteTestCollection() {
        return estudianteTestCollection;
    }

    public void setEstudianteTestCollection(Collection<EstudianteTestEntity> estudianteTestCollection) {
        this.estudianteTestCollection = estudianteTestCollection;
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
        hash += (idEstudiante != null ? idEstudiante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudianteEntity)) {
            return false;
        }
        EstudianteEntity other = (EstudianteEntity) object;
        if ((this.idEstudiante == null && other.idEstudiante != null) || (this.idEstudiante != null && !this.idEstudiante.equals(other.idEstudiante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectokajoy.ucb.edu.bo.Estudiante[ idEstudiante=" + idEstudiante + " ]";
    }

}
