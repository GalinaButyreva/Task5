package proj.task5.Interface;

import org.springframework.http.ResponseEntity;
import proj.task5.ProductExample.Model.ProdExample;
import proj.task5.ProductRegistr.Model.ProdRegistr;

// Выполнение шагов обработки Экземпляр Продукта
public interface StepPExampleExecable {
    ResponseEntity<?> execute(ProdExample prodExample);
}
