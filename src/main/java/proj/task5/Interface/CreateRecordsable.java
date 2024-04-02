package proj.task5.Interface;

import proj.task5.model.Tpp_product;

import java.util.List;

public interface CreateRecordsable {
    // Добавляем одну запись в таблицу
    <T, K>  T  create_rec_table(K model);
    // Добавляем несколько  записей в таблицу
    <T, K> List<T> create_recs_table(K model); //, Tpp_product tpp_product);

}
