package proj.task5.Repository;

import org.springframework.data.repository.CrudRepository;
import proj.task5.model.Tpp_product_register;

import java.util.List;

public interface Tpp_product_registerRepo extends CrudRepository<Tpp_product_register, Long> {
 //   @Query(value = "select t from tpp_product_register t where t.product_id = ?1 and t.type = ?2", nativeQuery = true)
 //   public List<Tpp_product_register> findPr(Integer product_id, String type);
    public List<Tpp_product_register> findByproductId(Long productId);
}
