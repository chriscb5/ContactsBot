package ucb.edu.kajoybot.bo.databasekajoy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.EstudianteEntity;
import ucb.edu.kajoybot.bo.databasekajoy.domain.EstudianteTestEntity;
import ucb.edu.kajoybot.bo.databasekajoy.domain.TestEntity;

public interface EstudianteTestRepository extends JpaRepository<EstudianteTestEntity,Integer> {
    EstudianteTestEntity findByIdTestAndIdEstudiante(TestEntity idTest, EstudianteEntity idEstudiante);
}
