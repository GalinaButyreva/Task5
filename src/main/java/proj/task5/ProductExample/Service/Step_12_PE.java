package proj.task5.ProductExample.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import proj.task5.Interface.StepPExampleExecable;
import proj.task5.ProductExample.Model.InstanceArrangement;
import proj.task5.ProductExample.Model.ProdExample;
import proj.task5.Repository.AgreementRepo;
import proj.task5.model.Agreement;
import proj.task5.service.Utils;

import java.util.List;

// Проверка таблицы ДС(agreement) на дубли(Шаг 1.2. ТЗ)
@Service
@Qualifier("12_PE") //  Если понадобится определить несколько реализаций одного интерфейса
public class Step_12_PE implements StepPExampleExecable {
    @Autowired
    AgreementRepo agreementRepo;

    @Override
    public ResponseEntity<?> execute(ProdExample prodExample) {
        // Проверка записей в таблице agreement со значением  number из  массива InstanceArrangement, если есть отправляем BadStatus
        List<InstanceArrangement>  agreeementLst = prodExample.getInstanceArrangement();
        for (InstanceArrangement agr: agreeementLst) {
            Agreement agreement = agreementRepo.findFirstByNumber(agr.getNumber());
            if (!(agreement == null))
                return Utils.statusBadRequest("Параметр № Дополнительного соглашения(сделки) Number " + agr.getNumber() +
                        " уже существует для ЭП с ИД " + agreement.getId());
        }
        //System.out.println("Step_12_PE"); // Оставлено Отладка
        // Если все поля заполнены позволяем выполнять функционал далее
        return null;
    }
}
