package ucb.edu.kajoybot.bo.databasekajoy.dto;

import ucb.edu.kajoybot.bo.databasekajoy.domain.CursoEntity;

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

    public CursoDto() {
    }

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
}
