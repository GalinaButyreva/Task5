package proj.task5.ProductRegistr.Service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import proj.task5.Interface.StepPRegisterExecable;
import proj.task5.ProductRegistr.Model.ProdRegistr;
import proj.task5.model.Tpp_product_register;
import proj.task5.service.CreateTppProductRegister;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class Maker_PRegister {
    List<StepPRegisterExecable> stepLst = new ArrayList<>();
    ProdRegistr modelRegistr;
    @Autowired
    @Qualifier("TppProductRegister")
    CreateTppProductRegister createTppProductRegister;
    @Autowired
    @Qualifier("OK_PRegister")
    StatusOK_PRegister statusOkProdRegister;
    public Maker_PRegister() {}
   @Autowired
    public Maker_PRegister(@Qualifier("1_PR")  Step_1_PR step_1
                        , @Qualifier("2_PR") Step_2_PR step_2
                        , @Qualifier("3_PR") Step_3_PR step_3
                        ) {
        stepLst.add(step_1);
        stepLst.add(step_2);
        stepLst.add(step_3);
    }

    public ResponseEntity<?> execute(){
        for (StepPRegisterExecable step : stepLst) {
            ResponseEntity<?> resp = step.execute(modelRegistr);
            if (!(resp == null))
                return resp;

        }
        // если добрались, то делаем запись в регистр
        Tpp_product_register tpp_product_register = createTppProductRegister.create_rec_table(modelRegistr);
        // возвращаем статус
        return statusOkProdRegister.createRequest(tpp_product_register.getId().toString());

    }

}
