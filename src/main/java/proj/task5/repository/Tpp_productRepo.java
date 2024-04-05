package proj.task5.repository;

import org.springframework.data.repository.CrudRepository;
import proj.task5.entity.Tpp_product;

public interface Tpp_productRepo extends CrudRepository<Tpp_product,  Long> {
    Tpp_product findFirstByNumber(String number);

}
