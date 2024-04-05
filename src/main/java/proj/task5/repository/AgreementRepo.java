package proj.task5.repository;

import org.springframework.data.repository.CrudRepository;
import proj.task5.entity.Agreement;

import java.util.List;

public interface AgreementRepo extends CrudRepository<Agreement, Long> {
    Agreement findFirstByNumber(String number);
    List<Agreement> findByProductId(Integer product_id);
}
