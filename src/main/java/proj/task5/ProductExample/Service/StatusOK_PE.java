package proj.task5.ProductExample.Service;


import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import proj.task5.model.Agreement;

import proj.task5.model.Tpp_product_register;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Qualifier("OK_PE")
@Data
// Структура для формирования ответа
public class StatusOK_PE   {
    String       instanseId;
    List<String> registerId = new ArrayList<>();
    List<String> supplementaryAgreementId = new ArrayList<>();

   public StatusOK_PE() {
    }


    public void SetAnswerStatusOK_PE(String instanseId, List<String> registerId, List<String> supplementaryAgreementId) {
        this.instanseId = instanseId;
        this.registerId = new ArrayList<>(registerId);
        this.supplementaryAgreementId = new ArrayList<>(supplementaryAgreementId);

    }

    public ResponseEntity<?> createRequest(String instanseId, List<Tpp_product_register> tppRegLst, List<Agreement> tppAgrLst) {
       this.instanseId = instanseId;
       if (!(tppRegLst.isEmpty()))
           this.registerId = tppRegLst.stream().map(x->x.getId().toString()).collect(Collectors.toList());
        if (!(tppAgrLst.isEmpty()))
            this.supplementaryAgreementId  =  tppAgrLst.stream().map(x->x.getId().toString()).collect(Collectors.toList());
        // Сформируем ответ
       return createRequest();
    }

    // Запишем ответ в поля класса для передачи ответа
    public ResponseEntity<?> createRequest() {
        Map<String, StatusOK_PE> mp = new HashMap<>();
        mp.put("data", this );
        return new ResponseEntity<>(mp, new HttpHeaders(), HttpStatus.CREATED);
    }


}
