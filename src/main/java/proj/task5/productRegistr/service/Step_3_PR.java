package proj.task5.productRegistr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import proj.task5.exceptions.NotFoundReqException;
import proj.task5.Interface.StepPRegisterExecable;
import proj.task5.productRegistr.model.ProdRegistr;
import proj.task5.entity.Tpp_ref_product_register_type;
import proj.task5.repository.Tpp_ref_product_register_typeRepo;


import java.util.List;

@Service
@Qualifier("3_PR") //  Если понадобится определить несколько реализаций одного интерфейса
public class Step_3_PR implements StepPRegisterExecable {
    @Autowired
    Tpp_ref_product_register_typeRepo tpp_ref_product_register_typeRepo;


   @Override
    public void execute(ProdRegistr modelRegister){
       //  Шаг 3 Наличие записи в tpp_ref_product_register_type
        List<Tpp_ref_product_register_type> tpp_type =  tpp_ref_product_register_typeRepo.findByValue(modelRegister.getRegistryTypeCode());
        if (tpp_type.isEmpty())
            throw new NotFoundReqException("Код продукта  " + modelRegister.getInstanceId() +
                                            " значение " + modelRegister.getRegistryTypeCode() +
                                            " не найдено в Каталоге продуктов tpp_ref_product_regisre_type" +
                                            " для данного типа Регистра" );
      // System.out.println("STEP_3_PR"); // Оставлено Отладка

    }


}
