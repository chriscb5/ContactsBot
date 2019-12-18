package ucb.edu.kajoybot.bo.databasekajoy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.PhoneNumberEntity;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumberEntity, Integer> {

}
