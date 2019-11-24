package ucb.edu.kajoybot.bo.databasekajoy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.KjEstudianteUserEntity;

import java.util.List;

public interface KjEstudianteUserRepository extends JpaRepository<KjEstudianteUserEntity,Integer> {
    KjEstudianteUserEntity findByBotUserId(String botid_user);
}
