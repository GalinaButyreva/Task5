package proj.task5.Repository;

import org.springframework.data.repository.CrudRepository;
import proj.task5.model.Tpp_ref_account_type;

public interface Tpp_ref_account_typeRepo extends CrudRepository<Tpp_ref_account_type, Long> {
 public  Tpp_ref_account_type findByValue(String value);
}
