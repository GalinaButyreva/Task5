package proj.task5.Interface;

import org.springframework.http.ResponseEntity;
import proj.task5.ProductRegistr.Model.ProdRegistr;
// Выполнение шагов обработки Продуктовый Регистр
public interface StepPRegisterExecable {
    ResponseEntity<?> execute(ProdRegistr modelCrAccount);
}
