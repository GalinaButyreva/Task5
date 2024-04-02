package proj.task5.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proj.task5.ProductExample.Model.ProdExample;
import proj.task5.ProductExample.Service.Maker_PExample;
import proj.task5.ProductExample.Service.StatusOK_PE;
import proj.task5.ProductRegistr.Model.ProdRegistr;
import proj.task5.ProductRegistr.Service.Maker_PRegister;

@RestController
@RequestMapping
public class RController {
    @Autowired
    Maker_PRegister createProdReg;
    @Autowired
    Maker_PExample createProdExampl;

    // Продуктовый регистр
    @PostMapping("/corporate-settlement-account/create")
    public ResponseEntity<?> corporateSettlementAccount(@RequestBody ProdRegistr modelRegister)
    {
        createProdReg.setModelRegistr(modelRegister);
        return  createProdReg.execute();
    }
    //Экземпляр продуктов
    @PostMapping("/corporate-settlement-instance/create")
    public ResponseEntity<?> corporateSettlementInstance(@RequestBody ProdExample modelProdExample) {
        createProdExampl.setModelProdExample(modelProdExample);
        return  createProdExampl.execute();


    }
}
