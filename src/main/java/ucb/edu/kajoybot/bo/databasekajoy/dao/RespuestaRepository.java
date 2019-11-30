package ucb.edu.kajoybot.bo.databasekajoy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.RespuestaEntity;

import java.util.List;

public interface RespuestaRepository extends JpaRepository<RespuestaEntity,Integer> {

    List<RespuestaEntity> findAllByIdRespuesta(int idRespuesta);
    List<RespuestaEntity> findByIdPregunta(int idPregunta);
    RespuestaEntity findByIdPreguntaAndNumeroRespuesta(int idPregunta,int answerNumber);
}
