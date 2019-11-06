package ucb.edu.kajoybot.bo.databasekajoy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.EstudianteEntity;

import java.util.List;

public interface EstudianteRespository extends JpaRepository<EstudianteEntity,Integer> {


    List<EstudianteEntity> findAllByStatuss(int status);
}
