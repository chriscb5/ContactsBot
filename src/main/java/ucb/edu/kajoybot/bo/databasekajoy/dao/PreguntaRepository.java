package ucb.edu.kajoybot.bo.databasekajoy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.PreguntaEntity;
import ucb.edu.kajoybot.bo.databasekajoy.domain.TestEntity;

import java.util.List;

public interface PreguntaRepository extends JpaRepository<PreguntaEntity,Integer> {

    List<PreguntaEntity> findAllByIdPregunta(int idPregunta);
    List<PreguntaEntity> findAllByIdTest(int idTest);
    PreguntaEntity findByContenidoPreguntaAndIdTest(String content_question, TestEntity entity);
    PreguntaEntity findByIdTestAndNumeroPregunta(int idTest,int questionNumber);
}
