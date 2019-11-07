package ucb.edu.kajoybot.bo.databasekajoy.dto;

import ucb.edu.kajoybot.bo.databasekajoy.domain.CursoEntity;
<<<<<<< HEAD

public class CursoDto {
    private String nombre;
    private String tipoCurso;

    public CursoDto(CursoEntity cursoEntity){
        this.nombre=cursoEntity.getNombre();
        this.tipoCurso=cursoEntity.getTipoCurso();
    }
    public CursoDto() {
=======
import ucb.edu.kajoybot.bo.databasekajoy.domain.DocenteEntity;

public class CursoDto {

    private Integer idCurso;
    private DocenteEntity idDocente;
    private String nombre;
    private String tipoCurso;
    private String clave;

    /*public CursoDto(CursoEntity cursoEntity) {
        this.idCurso = (cursoEntity.getIdCurso());
        this.idDocente = (cursoEntity.getIdDocente());
        this.nombre = (cursoEntity.getNombre());
        this.tipoCurso = (cursoEntity.getTipoCurso());
        this.clave = (cursoEntity.getClave());
    }*/

    public CursoDto(CursoEntity cursoEntity){
        this.nombre = (cursoEntity.getNombre());
        this.tipoCurso = (cursoEntity.getTipoCurso());
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public DocenteEntity getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(DocenteEntity idDocente) {
        this.idDocente = idDocente;
>>>>>>> 72d848194d3747fe8e175f86849e7c69083b1557
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
<<<<<<< HEAD
}
=======

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
>>>>>>> 72d848194d3747fe8e175f86849e7c69083b1557
