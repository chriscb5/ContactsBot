package ucb.edu.kajoybot.bo.databasekajoy.dto;

import ucb.edu.kajoybot.bo.databasekajoy.domain.DocenteEntity;

import java.util.Date;

public class DocenteDto {

    private Integer idDocente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int statuss;
    private String txUser;
    private Date txDate;

    public DocenteDto(DocenteEntity docenteEntity){

        this.idDocente=(docenteEntity.getIdDocente());
        this.nombre=(docenteEntity.getNombre());
        this.apellidoPaterno=(docenteEntity.getApellidoPaterno());
        this.apellidoMaterno=(docenteEntity.getApellidoMaterno());
        this.statuss=(docenteEntity.getStatuss());
        this.txUser=(docenteEntity.getTxUser());
        this.txDate=(docenteEntity.getTxDate());


    }
    public Integer getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Integer idDocente) {
        this.idDocente = idDocente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public int getStatuss() {
        return statuss;
    }

    public void setStatuss(int statuss) {
        this.statuss = statuss;
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