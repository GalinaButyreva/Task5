package proj.task5.Interface;

import org.springframework.http.ResponseEntity;

// Для формирования отправляемых статусов
// Можно было бы и убрать(уже все протестировала)
public interface CreateStatusAble {
    ResponseEntity<?> createRequest(String message);
}
