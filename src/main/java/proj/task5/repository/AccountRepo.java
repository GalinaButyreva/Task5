package proj.task5.repository;

import org.springframework.data.repository.CrudRepository;
import proj.task5.entity.Account;

public interface AccountRepo extends CrudRepository<Account, Long> {
}
