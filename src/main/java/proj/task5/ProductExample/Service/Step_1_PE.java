package proj.task5.ProductExample.Service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import proj.task5.Interface.StepPExampleExecable;
import proj.task5.ProductExample.Model.InstanceArrangement;
import proj.task5.ProductExample.Model.ProdExample;
import proj.task5.service.Utils;

import java.util.ArrayList;
import java.util.List;

// Проверка Request.Body на обязательность заполнения(Шаг 1 ТЗ)
@Service
@Qualifier("1_PE") //  Если понадобится определить несколько реализаций одного интерфейса
public class Step_1_PE implements StepPExampleExecable {
   // Накапливаем все ошибочные поля
   List<String> errLst = new ArrayList<>();
   // Проверяем  на null
  void checkValidValue(Object value, String name_param){
        if (value == null || value.toString().isEmpty())
          errLst.add("Имя обязательного параметра " + name_param + " : не зполнено значение ");
  }
   void checkValid(ProdExample prodExample){
       checkValidValue(prodExample.getProductType()     , "ProductType");
       checkValidValue(prodExample.getProductCode()     , "ProductCode");
       checkValidValue(prodExample.getRegisterType()    , "RegisterType");
       checkValidValue(prodExample.getMdmCode()         , "MdmCode");
       checkValidValue(prodExample.getContractNumber()  , "ContractNumber");
       checkValidValue(prodExample.getContractDate()    , "ContractDate");
       checkValidValue(prodExample.getPriority()        , "Priority");
       checkValidValue(prodExample.getContractId()      , "ContractId");
       checkValidValue(prodExample.getBranchCode()      , "BranchCode");
       checkValidValue(prodExample.getIsoCurrencyCode() , "IsoCurrencyCode");
       checkValidValue(prodExample.getUrgencyCode()     , "UrgencyCode");
       List<InstanceArrangement> argLst = prodExample.getInstanceArrangement();
       for (InstanceArrangement arg : argLst) {
           checkValidValue(arg.getNumber(), "Number");
           checkValidValue(arg.getOpeningDate(), "OpeningDate");
       }
   }


    @Override
    public ResponseEntity<?> execute(ProdExample prodExample) {
        // Шаг 1 Проверка на обязательность заполнения переданных значений
        errLst.clear();
        checkValid(prodExample);
        if (!errLst.isEmpty()) {
            return Utils.statusBadRequest(errLst.toString());
        }
       // System.out.println("Step_1_PE"); // Оставлено Отладка
        // Если все поля заполнены позволяем выполнять функционал далее
        return null;
    }
}
