package proj.task5.Repository;

import org.springframework.data.repository.CrudRepository;
import proj.task5.model.Account_pool;

public interface Account_poolRepo extends CrudRepository<Account_pool, Long> {
    //  Слишком длинно Можно было делать как  Query (оставила, чтобы убедиться, что так тоже можно)
    Account_pool findFirstByBranchCodeAndCurrencyCodeAndMdmCodeAndPriorityCodeAndRegistryTypeCode(String branchCode
                                                                                                    , String CurrencyCode
                                                                                                    , String MdmCode
                                                                                                    , String PriorityCode
                                                                                                    , String registryTypeCode);
}
