package ucb.edu.kajoybot.bo.databasekajoy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ucb.edu.kajoybot.bo.databasekajoy.domain.ContactEntity;
import ucb.edu.kajoybot.bo.databasekajoy.domain.KjUserEntity;

import java.util.Date;
import java.util.List;

public interface ContactRepository extends JpaRepository<ContactEntity, Integer> {

    ContactEntity findByContactId (int contactId);

    ContactEntity findByUserIdAndFirstNameAndSecondNameAndFirstSurnameAndSecondSurnameAndEmailAndBirthdate(KjUserEntity userId, String firstName, String secondName, String firstSurname, String secondSurname, String email, Date birthDate);

    List<ContactEntity> findByFirstNameOrSecondNameOrFirstSurnameOrSecondSurname(String firstName, String secondName, String firstSurname, String secondSurname);

    List<ContactEntity> findByFirstNameContainingOrSecondNameContainingOrFirstSurnameContainingOrSecondSurnameContaining(String firstName, String secondName, String firstSurname, String secondSurname);

}
