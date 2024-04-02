package proj.task5.ProductRegistr.Service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import proj.task5.Interface.StepPRegisterExecable;
import proj.task5.ProductRegistr.Model.ProdRegistr;
import proj.task5.service.Utils;

@Service
@Qualifier("1_PR") //  Если понадобится определить несколько реализаций одного интерфейса
public class Step_1_PR implements StepPRegisterExecable {


   @Override
    public ResponseEntity<?> execute(ProdRegistr modelCrAccount){
        // Шаг 1 Проверка на обязательность
        if (modelCrAccount.getInstanceId() == null)
            return Utils.statusBadRequest("Идентификатор экземпляра продукта <InstanceId>  не заполнен");

      // System.out.println("STEP_1_PR"); // Оставлено Отладка
       return  null;
        }

    }
