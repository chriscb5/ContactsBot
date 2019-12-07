package ucb.edu.kajoybot.bo.databasekajoy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.PreguntaEntity;
import ucb.edu.kajoybot.bo.databasekajoy.domain.RespuestaEntity;

import java.util.List;

public interface RespuestaRepository extends JpaRepository<RespuestaEntity,Integer> {

    List<RespuestaEntity> findAllByIdRespuesta(int idRespuesta);
    List<RespuestaEntity> findByIdPregunta(PreguntaEntity preguntaEntity);
    RespuestaEntity findByIdPreguntaAndNumeroRespuesta(PreguntaEntity idPregunta,int answerNumber);
}
