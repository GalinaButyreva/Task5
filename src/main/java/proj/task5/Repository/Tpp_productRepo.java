package proj.task5.Repository;

import org.springframework.data.repository.CrudRepository;
import proj.task5.model.Tpp_product;

public interface Tpp_productRepo extends CrudRepository<Tpp_product,  Long> {
    Tpp_product findFirstByNumber(String number);

}
