package proj.task5.ProductRegistr.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import proj.task5.Interface.CreateStatusAble;

@Data
@Component
@Qualifier("OK_PRegister") //  Если понадобится определить несколько реализаций одного интерфейса
public class StatusOK_PRegister implements CreateStatusAble {

    @Override
    public ResponseEntity<?> createRequest(String accountSch) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        ObjectNode objectNodeRoot = mapper.createObjectNode();

        objectNode.put("accountId", accountSch);
        objectNodeRoot.set("data", objectNode);
        return new ResponseEntity<>(objectNodeRoot, new HttpHeaders(), HttpStatus.CREATED);
    }


}
