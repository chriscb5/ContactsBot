package ucb.edu.kajoybot.bo.databasekajoy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.CursoEntity;
import ucb.edu.kajoybot.bo.databasekajoy.domain.DocenteEntity;

import java.util.List;

public interface CursoRepository extends JpaRepository<CursoEntity, Integer> {
    List<CursoEntity> findAllByIdCurso(int idCurso);
    List<CursoEntity> findAllByIdDocente(DocenteEntity idDocente);
    CursoEntity findByIdCurso(int idCurso);
}
