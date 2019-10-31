package ucb.edu.kajoybot.bo.databasekajoy.dto;

import ucb.edu.kajoybot.bo.databasekajoy.domain.EstudianteEntity;

import java.util.Date;

public class EstudianteDto {
    private int idEstudiante;
    private String nombre;
    private int status;
    private String txUser;
    private Date txDate;

    public EstudianteDto(){

    }

    public EstudianteDto(EstudianteEntity estudianteEntity){
         EstudianteDto result= new EstudianteDto();
         this.nombre=(estudianteEntity.getNombre());
         this.txUser=(estudianteEntity.getTxUser());
         this.status=(estudianteEntity.getStatus());


    }
    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
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
}
