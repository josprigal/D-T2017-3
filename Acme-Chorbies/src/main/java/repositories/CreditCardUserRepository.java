package repositories;

import domain.Actor;
import domain.CreditCardUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import security.UserAccount;

@Repository
public interface CreditCardUserRepository extends JpaRepository<CreditCardUser, Integer> {


}
