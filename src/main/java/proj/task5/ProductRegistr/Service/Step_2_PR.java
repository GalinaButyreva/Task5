package proj.task5.ProductRegistr.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import proj.task5.Interface.StepPRegisterExecable;
import proj.task5.ProductRegistr.Model.ProdRegistr;
import proj.task5.model.Tpp_product_register;
import proj.task5.Repository.Tpp_product_registerRepo;
import proj.task5.service.Utils;


import java.util.List;

@Service
@Qualifier("2_PR") //  Если понадобится определить несколько реализаций одного интерфейса
public class Step_2_PR implements StepPRegisterExecable {
    @Autowired
    Tpp_product_registerRepo tppProductRegisterRepo;

    private boolean foundNoRepeat(Long product_id, String type){
        List<Tpp_product_register> tpp_product_registers = (List<Tpp_product_register>) tppProductRegisterRepo.findByproductId(product_id);
        return tpp_product_registers.stream().noneMatch(x->x.getType().equals(type));
    }

    @Override
    public ResponseEntity<?> execute(ProdRegistr modelProduct){
        // Шаг 2  Проаерим, возможно в tpp_product_register уже есть записи с таким же product_id и type
        // Если есть, вернем ответ
        if (!foundNoRepeat(modelProduct.getInstanceId(), modelProduct.getRegistryTypeCode()))
            return Utils.statusBadRequest("Параметр registryTypeCode тип регистра " +
                    modelProduct.getRegistryTypeCode() +
                    " уже существует для ЭП  с ИД " + modelProduct.getInstanceId());
        // System.out.println("STEP_1_PR"); // Оставлено Отладка
        return null;
    }

}
