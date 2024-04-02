package proj.task5.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

// Вынесла (проще при последующем использовании)
public class Utils {
    public static ResponseEntity<?> statusNotFound(String message) {
        return ResponseEntity.status((HttpStatus.NOT_FOUND)).body(Map.of(HttpStatus.NOT_FOUND.value(), message));
    }
    public static ResponseEntity<?> statusBadRequest(String message) {
        return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body(Map.of(HttpStatus.BAD_REQUEST.value(), message));
    }
    public static ResponseEntity<?> statusError(String message) {
      return ResponseEntity
              .status((HttpStatus.INTERNAL_SERVER_ERROR))
              .body(Map.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), message));
    }

}
