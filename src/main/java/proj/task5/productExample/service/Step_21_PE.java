package proj.task5.productExample.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import proj.task5.Interface.StepPExampleExecable;
import proj.task5.exceptions.NotFoundReqException;
import proj.task5.productExample.model.ProdExample;
import proj.task5.repository.Tpp_productRepo;
import proj.task5.entity.Tpp_product;


// Проверка таблицы ЭП(tpp_product) на наличие записи (шаг 2.1 ТЗ)
@Data
@Service
@Qualifier("21_PE") //  Если понадобится определить несколько реализаций одного интерфейса
public class Step_21_PE implements StepPExampleExecable {
    @Autowired
    private Tpp_productRepo tpp_productRepo;

    @Override
    public Object execute(ProdExample prodExample) {
        Tpp_product  tpp_product =tpp_productRepo.findById(prodExample.getInstanceId()).orElse(null);
        // Если не нашли возвращаем статус и сообщение
        if (tpp_product == null)
            throw new NotFoundReqException("Экземрпляр  продукта с параметром  " + prodExample.getInstanceId() +
                                        " не найден");

        //System.out.println("Step_21_PE"); // Оставлено Отладка
        // Если все поля заполнены позволяем выполнять функционал далее
        return null;
    }
}
