package ucb.edu.kajoybot.bo.databasekajoy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.KjUserEntity;

public interface KjUserRepository extends JpaRepository<KjUserEntity,Integer> {
    KjUserEntity findByBotUserId(String botid_user);
}
