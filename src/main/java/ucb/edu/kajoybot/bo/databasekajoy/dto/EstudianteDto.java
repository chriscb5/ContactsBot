package ucb.edu.kajoybot.bo.databasekajoy.dto;

import ucb.edu.kajoybot.bo.databasekajoy.domain.EstudianteEntity;

import java.util.Date;
import java.util.List;

public class EstudianteDto {
    private int idEstudiante;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String institucion;
    private int status;
    private String txUser;
    private Date txDate;

    private List<CursoDto> cursosList;

    public EstudianteDto(){

    }

    /*public EstudianteDto(EstudianteEntity estudianteEntity){
         EstudianteDto result= new EstudianteDto();
         this.nombre=(estudianteEntity.getNombre());
         this.txUser=(estudianteEntity.getTxUser());
         this.status=(estudianteEntity.getStatuss());


    }
     */

    public EstudianteDto(EstudianteEntity estudianteEntity){
        this.idEstudiante=(estudianteEntity.getIdEstudiante());
        this.nombre=(estudianteEntity.getNombre());
        this.apellidoPaterno=(estudianteEntity.getApellidoPaterno());
        this.apellidoMaterno=(estudianteEntity.getApellidoMaterno());
        this.institucion=(estudianteEntity.getInstitucion());
        this.txUser=(estudianteEntity.getTxUser());
        this.status=(estudianteEntity.getStatuss());
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

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
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

    public List<CursoDto> getCursosList() {
        return cursosList;
    }

    public void setCursosList(List<CursoDto> cursosList) {
        this.cursosList = cursosList;
    }
}
