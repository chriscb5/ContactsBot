package ucb.edu.kajoybot.bo.databasekajoy.domain;

import javax.persistence.*;

@Entity
@Table(name = "curso", schema = "kajoydatabase", catalog = "")
public class CursoEntity {
    private int idCurso;
    private String nombre;
    private String tipoCurso;
    private String clave;

    @Id
    @Column(name = "id_curso")
    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "tipo_curso")
    public String getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    @Basic
    @Column(name = "clave")
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CursoEntity that = (CursoEntity) o;

        if (idCurso != that.idCurso) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (tipoCurso != null ? !tipoCurso.equals(that.tipoCurso) : that.tipoCurso != null) return false;
        if (clave != null ? !clave.equals(that.clave) : that.clave != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCurso;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (tipoCurso != null ? tipoCurso.hashCode() : 0);
        result = 31 * result + (clave != null ? clave.hashCode() : 0);
        return result;
    }
}
