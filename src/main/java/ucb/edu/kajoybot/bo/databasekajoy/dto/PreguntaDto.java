package ucb.edu.kajoybot.bo.databasekajoy.dto;

import ucb.edu.kajoybot.bo.databasekajoy.domain.PreguntaEntity;

public class PreguntaDto {

    private Integer idPregunta;
    private int numeroPregunta;
    private String contenidoPregunta;

    public PreguntaDto(PreguntaEntity preguntaEntity) {
        this.idPregunta = preguntaEntity.getIdPregunta();
        this.numeroPregunta = preguntaEntity.getNumeroPregunta();
        this.contenidoPregunta = preguntaEntity.getContenidoPregunta();
    }

    public Integer getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public int getNumeroPregunta() {
        return numeroPregunta;
    }

    public void setNumeroPregunta(int numeroPregunta) {
        this.numeroPregunta = numeroPregunta;
    }

    public String getContenidoPregunta() {
        return contenidoPregunta;
    }

    public void setContenidoPregunta(String contenidoPregunta) {
        this.contenidoPregunta = contenidoPregunta;
    }
}
