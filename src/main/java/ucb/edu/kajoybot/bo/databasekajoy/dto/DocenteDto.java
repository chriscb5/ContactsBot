package ucb.edu.kajoybot.bo.databasekajoy.dto;

import ucb.edu.kajoybot.bo.databasekajoy.domain.DocenteEntity;

import java.util.Date;

public class DocenteDto {
    private int idDocente;
    private String nombre;
    private int status;
    private String txUser;
    private Date txDate;

    public DocenteDto() {
    }

    public DocenteDto(DocenteEntity docenteEntity){
        DocenteDto result= new DocenteDto();
        this.nombre=(docenteEntity.getNombre());
        this.txUser=(docenteEntity.getTxUser());
        this.status=(docenteEntity.getStatus());

    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
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
}
