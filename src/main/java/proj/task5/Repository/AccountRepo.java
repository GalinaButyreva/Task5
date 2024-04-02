package proj.task5.Repository;

import org.springframework.data.repository.CrudRepository;
import proj.task5.model.Account;

public interface AccountRepo extends CrudRepository<Account, Long> {
}
