package proj.task5.Repository;

import org.springframework.data.repository.CrudRepository;
import proj.task5.model.Tpp_ref_product_register_type;

import java.util.List;

public interface Tpp_ref_product_register_typeRepo extends CrudRepository<Tpp_ref_product_register_type, Long> {
    List<Tpp_ref_product_register_type> findByValue(String value);
}
