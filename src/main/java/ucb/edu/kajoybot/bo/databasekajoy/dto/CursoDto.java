package ucb.edu.kajoybot.bo.databasekajoy.dto;

import ucb.edu.kajoybot.bo.databasekajoy.domain.CursoEntity;

public class CursoDto {
    private String nombre;
    private String tipoCurso;

    public CursoDto(CursoEntity cursoEntity){
        this.nombre=cursoEntity.getNombre();
        this.tipoCurso=cursoEntity.getTipoCurso();
    }
    public CursoDto() {
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
}
