package proj.task5.ProductExample.Service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import proj.task5.Interface.StepPExampleExecable;
import proj.task5.ProductExample.Model.ProdExample;
import proj.task5.Repository.Tpp_ref_product_classRepo;
import proj.task5.model.Tpp_ref_product_class;
import proj.task5.model.Tpp_ref_product_register_type;
//import proj.task5.service.StatusBadRequest;
//import proj.task5.service.StatusNotFound;
import proj.task5.service.CreateTppProductRegister;
import proj.task5.service.Utils;

import java.util.ArrayList;
import java.util.List;
// Найти связанные записи( если не нашли отправить статус NOT_FOUND Шаг 1.3 ТЗ)
@Data
@Service
@Qualifier("13_PE") //  Если понадобится определить несколько реализаций одного интерфейса
public class Step_13_PE implements StepPExampleExecable {
    @Autowired
    Tpp_ref_product_classRepo tpp_ref_product_classRepo;
    @Autowired
    CreateTppProductRegister createTppProductRegister;

    @Override
    public ResponseEntity<?> execute(ProdExample prodExample) {
        // Выбираем записи из tpp_ref_pproduct_class (чтобы затем найти все записи из  tpp_ref_product_register_type)
        List<Tpp_ref_product_register_type>   tppTypeLst =  createTppProductRegister.getLstType(prodExample.getProductCode());
        // Если не нашли вернем ответ
        if (tppTypeLst.size() == 0)
            return Utils.statusNotFound("Код продукта  " + prodExample.getProductCode() +
                    " не найден в каталоге продуктов tpp_ref_product_class");

        //System.out.println("Step_13_PE"); // Оставлено Отладка
        // Если все поля заполнены позволяем выполнять функционал далее
        return null;
    }
}
