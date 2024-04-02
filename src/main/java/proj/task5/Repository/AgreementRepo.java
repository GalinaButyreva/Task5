package proj.task5.Repository;

import org.springframework.data.repository.CrudRepository;
import proj.task5.model.Agreement;

import java.util.List;

public interface AgreementRepo extends CrudRepository<Agreement, Long> {
    Agreement findFirstByNumber(String number);
    List<Agreement> findByProductId(Integer product_id);
}
