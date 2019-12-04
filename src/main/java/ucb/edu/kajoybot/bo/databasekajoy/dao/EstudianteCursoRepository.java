package ucb.edu.kajoybot.bo.databasekajoy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.EstudianteCursoEntity;
import ucb.edu.kajoybot.bo.databasekajoy.domain.EstudianteEntity;

import java.util.List;

public interface EstudianteCursoRepository extends JpaRepository<EstudianteCursoEntity, Integer> {
    List<EstudianteCursoEntity> findAllByIdEstudianteCurso(int idEstudianteCurso);
    List<EstudianteCursoEntity> findAllByIdEstudiante(int idEstudiante);
    List<EstudianteCursoEntity> findAllByIdCurso(int idCurso);
    EstudianteEntity findByIdEstudianteCurso(int IsEstudianteCurso);
}
