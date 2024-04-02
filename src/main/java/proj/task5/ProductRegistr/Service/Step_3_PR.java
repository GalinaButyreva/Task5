package proj.task5.ProductRegistr.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import proj.task5.Interface.StepPRegisterExecable;
import proj.task5.ProductRegistr.Model.ProdRegistr;
import proj.task5.model.Tpp_ref_product_register_type;
import proj.task5.Repository.Tpp_ref_product_register_typeRepo;
import proj.task5.service.Utils;


import java.util.List;

@Service
@Qualifier("3_PR") //  Если понадобится определить несколько реализаций одного интерфейса
public class Step_3_PR implements StepPRegisterExecable {
    @Autowired
    Tpp_ref_product_register_typeRepo tpp_ref_product_register_typeRepo;
    @Override
    public ResponseEntity<?> execute(ProdRegistr modelRegister) {
        // Проверяем, существуют ли записи в tpp_ref_product_register_type если нет вернем статус, иначе продолжим обработку
        List<Tpp_ref_product_register_type> tpp_type =  tpp_ref_product_register_typeRepo.findByValue(modelRegister.getRegistryTypeCode());
        if (tpp_type.isEmpty())
            return Utils.statusNotFound("Код продукта  " + modelRegister.getInstanceId() +
                    " не найдено в Каталоге продуктов tpp_ref_product_regisre_type" +
                    " для данного типа Регистра" );

        // System.out.println("STEP_3_PR"); // Оставлено Отладка
        return null;
    }
}
