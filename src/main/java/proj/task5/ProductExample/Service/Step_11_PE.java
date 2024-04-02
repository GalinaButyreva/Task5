package proj.task5.ProductExample.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import proj.task5.Interface.StepPExampleExecable;
import proj.task5.ProductExample.Model.ProdExample;
import proj.task5.Repository.Tpp_productRepo;
import proj.task5.model.Tpp_product;
import proj.task5.service.Utils;

// Проверка таблицы ЭП(tpp_product) на дубли (Шаг 1.1. ТЗ)
@Service
@Qualifier("11_PE") //  Если понадобится определить несколько реализаций одного интерфейса
public class Step_11_PE implements StepPExampleExecable {
    @Autowired
    Tpp_productRepo tpp_productRepo;
    @Override
    public ResponseEntity<?> execute(ProdExample prodExample) {
        // Проверка наличия записи в таблице tpp_product со значением number (если есть отправляем BadStatus )
        Tpp_product tpp_product = tpp_productRepo.findFirstByNumber(prodExample.getContractNumber());
        if (!(tpp_product == null))
            return Utils.statusBadRequest("Параметр ContractNumber № договора " + prodExample.getContractNumber() +
                    " уже существует для ЭП с ИД " + tpp_product.getId());
        //System.out.println("Step_11_PE"); // Оставлено Отладка
        // Если все поля заполнены позволяем выполнять функционал далее
        return null;
    }
}
