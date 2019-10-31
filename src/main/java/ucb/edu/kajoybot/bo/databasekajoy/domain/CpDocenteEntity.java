package ucb.edu.kajoybot.bo.databasekajoy.domain;

import javax.persistence.*;
import java.sql.Date;
@Entity
@Table(name = "cp_estudiante", schema = "kajoydatabase", catalog = "")
public class CpDocenteEntity {
    private int hDocenteId;
    private int idDocente;
    private String nombre;
    private int id;
    private String txUser;
    private Date txDate;

    @Id
    @Column(name = "h_docente_id")
    public int gethDocenteId() {
        return hDocenteId;
    }

    public void sethDocenteId(int hDocenteId) {
        this.hDocenteId = hDocenteId;
    }

    @Basic
    @Column(name = "id_docente")
    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
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
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tx_user")
    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    @Basic
    @Column(name = "tx_date")
    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CpDocenteEntity that = (CpDocenteEntity) o;
        if (hDocenteId != that.hDocenteId) return false;
        if (idDocente != that.idDocente) return false;
        if (id != that.id) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (txUser != null ? !txUser.equals(that.txUser) : that.txUser != null) return false;
        if (txDate != null ? !txDate.equals(that.txDate) : that.txDate != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (hDocenteId ^ (hDocenteId >>> 32));
        result = 31 * result + idDocente;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (txUser != null ? txUser.hashCode() : 0);
        result = 31 * result + (txDate != null ? txDate.hashCode() : 0);
        return result;
    }


}
