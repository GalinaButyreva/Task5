package proj.task5.ProductExample.Service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import proj.task5.Interface.StepPExampleExecable;
import proj.task5.ProductExample.Model.ProdExample;
import proj.task5.service.Utils;


import java.util.ArrayList;
import java.util.List;

// Создание ЭП
@Data
@Service
public class Maker_PExample {
    // Класс для парсинга JSON
    ProdExample modelProdExample;

    // Если не заполнен InstanceId определяем логику выполнения
    List<StepPExampleExecable> stepLstNullInstance = new ArrayList<>();
    // Если заполнен InstanceId определяем логику выполнения
    List<StepPExampleExecable> stepLstNotNullInstance = new ArrayList<>();
    public Maker_PExample() {}

   // Внедряемые бины
    @Autowired
    public Maker_PExample(@Qualifier("1_PE")  Step_1_PE step_1
                        , @Qualifier("11_PE") Step_11_PE step_11
                        , @Qualifier("12_PE") Step_12_PE step_12
                        , @Qualifier("13_PE") Step_13_PE step_13
                        , @Qualifier("14_PE") Step_14_PE step_14
                        , @Qualifier("21_PE") Step_21_PE step_21
                        , @Qualifier("23_PE") Step_23_PE step_23
                        ) {
       // Если НЕ задан InstanceId
       // Проверка Request.Body на обязательность заполнения(Шаг 1 ТЗ)
       stepLstNullInstance.add(step_1);
       // Проверка таблицы ЭП(tpp_product) на дубли (Шаг 1.1. ТЗ)
       stepLstNullInstance.add(step_11);
       // Проверка таблицы ДС(agreement) на дубли(Шаг 1.2. ТЗ)
       stepLstNullInstance.add(step_12);
       // Найти связанные записи( если не нашли отправить статус NOT_FOUND Шаг 1.3 ТЗ)
       stepLstNullInstance.add(step_13);
       // Добавить строки в tpp_product и в tpp_product_registry(шаг 1.4 и 1.5)(одна транзакция)
       stepLstNullInstance.add(step_14);

       // Если Задан InstanceId
        // Проверка Request.Body на обязательность заполнения(Шаг 1 ТЗ)
        stepLstNotNullInstance.add(step_1);
        // Проверка таблицы ЭП(tpp_product) на наличие записи (шаг 2.1 ТЗ)
       stepLstNotNullInstance.add(step_21);
       // Проверка таблицы agreement на дубли
       stepLstNotNullInstance.add(step_12);
        // Добавляем записи в таблицу agreement
        stepLstNotNullInstance.add(step_23);

    }

    // Обработаем все шаги и добавим записи
    public ResponseEntity<?> execute(){
        List<StepPExampleExecable> listExecArr;
         // Если НЕ задан InstanceId
        if (modelProdExample.getInstanceId() == null)
            listExecArr = new ArrayList<>(stepLstNullInstance);
         else
            listExecArr = new ArrayList<>(stepLstNotNullInstance);
         //Выполняем  шаги последовательно
        for (StepPExampleExecable step : listExecArr) {
            ResponseEntity<?> resp = step.execute(modelProdExample);
            if (!(resp == null))
                return resp;
        }
        return Utils.statusError("Не предвидденная ошибка чтения/записи данных");
    }


}
