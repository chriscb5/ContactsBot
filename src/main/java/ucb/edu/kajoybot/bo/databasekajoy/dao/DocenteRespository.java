package ucb.edu.kajoybot.bo.databasekajoy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.DocenteEntity;

import java.util.List;

public interface DocenteRespository extends JpaRepository<DocenteEntity, Integer> {
    List<DocenteEntity> findAllByStatuss(int status);
    DocenteEntity findAllByNombre(String nombre);
    DocenteEntity findAllByIdDocente(int idOdcente);
}
