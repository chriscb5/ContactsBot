package ucb.edu.kajoybot.bo.databasekajoy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.ContactEntity;
import ucb.edu.kajoybot.bo.databasekajoy.domain.PhoneNumberEntity;

import java.util.List;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumberEntity, Integer> {

    PhoneNumberEntity findByPhoneId(int phoneID);

    List<PhoneNumberEntity> findByContactId(ContactEntity contactID);
}
