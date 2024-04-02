package proj.task5.ProductExample.Service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proj.task5.Interface.StepPExampleExecable;
import proj.task5.ProductExample.Model.ProdExample;
import proj.task5.Repository.AgreementRepo;
import proj.task5.Repository.Tpp_productRepo;
import proj.task5.Repository.Tpp_product_registerRepo;
import proj.task5.model.Agreement;
import proj.task5.model.Tpp_product;
import proj.task5.model.Tpp_product_register;
import proj.task5.service.CreateAgreement;
import proj.task5.service.CreateTppProduct;
import proj.task5.service.CreateTppProductRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@Qualifier("14_PE")
public class Step_14_PE implements StepPExampleExecable {
    // ЭП

    @Autowired
    @Qualifier("TppProduct")
    private CreateTppProduct createTppProduct;
    @Autowired
    @Qualifier("TppProductRegister")
    private CreateTppProductRegister createTppProductRegister;


    @Autowired
    private Tpp_productRepo tpp_productRepo;
    @Autowired
    private Tpp_product_registerRepo tpp_product_registerRepo;

    @Autowired
    StatusOK_PE statusOKPE;


    Tpp_product tpp_product;
    List<Agreement> agrList;
    List<Tpp_product_register> tpp_product_registers;

    @Transactional
    public  void create_records_tpp(ProdExample modelProdExample){
        // создаем запись в Tpp_product
        tpp_product = createTppProduct.create_rec_table(modelProdExample);
        // Добавим записи в таблицу Tpp_product_register
        tpp_product_registers=  createTppProductRegister.create_recs_table_product_register(modelProdExample, tpp_product);
        // Добавим записи в таблицу Agreement (в  ТЗ не указано добавлять пока пропускаю)
        //createAgreement.create_recs_table_agreement(modelProdExample, tpp_product);

        agrList = tpp_product.getAgreementList();

    }


    @Override
    public ResponseEntity<?> execute(ProdExample prodExample) {
        // создадим записи в Tpp_product, Tpp_product_register
        create_records_tpp(prodExample);
        // Заполним и отошлем ответ
        List<String> registerIdLst = new ArrayList<>();
        List<String> agreementsIdLst = new ArrayList<>();
        String instanseId = tpp_product.getId().toString();
        if (!(tpp_product_registers.isEmpty()))
           registerIdLst = tpp_product_registers.stream().map(x->x.getId().toString()).collect(Collectors.toList());
        if (!(agrList.isEmpty()))
            agreementsIdLst =  agrList.stream().map(x->x.getId().toString()).collect(Collectors.toList());
        // Сформируем ответ
        statusOKPE.SetAnswerStatusOK_PE(instanseId, registerIdLst, agreementsIdLst);
        //System.out.println("Step_14_PE"); // Оставлено Отладка
        return statusOKPE.createRequest();

    }
}
