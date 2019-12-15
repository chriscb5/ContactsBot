package ucb.edu.kajoybot.bo.databasekajoy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.CursoEntity;
import ucb.edu.kajoybot.bo.databasekajoy.domain.TestEntity;

import java.util.List;

public interface TestRepository extends JpaRepository<TestEntity,Integer> {
    List<TestEntity> findAllByIdTest(int idTest);

//    TestEntity findAllByNombreTest(String nombreTest);

    List<TestEntity> findAllByIdCurso(CursoEntity cursoEntity);
    TestEntity findByNombreTest(String nombreTest);
    TestEntity findAllByNombreTest(String nombreTest);
}
