package proj.task5.ProductExample.Service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proj.task5.Interface.StepPExampleExecable;
import proj.task5.ProductExample.Model.ProdExample;
import proj.task5.Repository.Tpp_productRepo;
import proj.task5.Repository.Tpp_product_registerRepo;
import proj.task5.model.Agreement;
import proj.task5.model.Tpp_product;
import proj.task5.model.Tpp_product_register;
import proj.task5.service.CreateAgreement;

import java.util.List;

@Data
@Service
@Qualifier("23_PE")
public class Step_23_PE implements StepPExampleExecable {
    @Autowired
    @Qualifier("OK_PE")
    StatusOK_PE statusOKPe;

    @Autowired
    @Qualifier("Agreement")
    private CreateAgreement createAgreement;


    @Autowired
    private Tpp_product_registerRepo tpp_product_registerRepo;

    @Autowired
    private Tpp_productRepo tpp_productRepo;

    private Tpp_product tppProduct;
    private List<Agreement> tppAgrLst;
    private List<Tpp_product_register> tppRegLst;

    @Transactional
    public  void create_records_agreement(ProdExample modelProdExample){
        tppProduct = tpp_productRepo.findById(modelProdExample.getInstanceId()).orElse(null);
        // Добавим записи в таблицу Agreement
        tppAgrLst= createAgreement.create_recs_table_agreement(modelProdExample, tppProduct);
        // будем выводить все agreement по product_id
        tppAgrLst = createAgreement.findAllAgreement(tppProduct);
        // Найлем записи в реестре платежей для формирования ответа
        tppRegLst = tpp_product_registerRepo.findByproductId(tppProduct.getId());

    }



    @Override
    public ResponseEntity<?> execute(ProdExample prodExample) {
        create_records_agreement(prodExample);
        //System.out.println("STEP_23"); // Оставлено Отладка
        return statusOKPe.createRequest(tppProduct.getId().toString(), tppRegLst,  tppAgrLst);
    }
}
