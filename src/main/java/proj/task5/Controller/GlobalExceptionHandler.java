package proj.task5.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import proj.task5.service.Utils;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exception(Exception e){
       /* Можно было так, при отладке мешало
        StackTraceElement[] stackTraceElements =  e.getStackTrace();
        StringBuilder strOut = new StringBuilder();
        for (StackTraceElement stackTraceElement: stackTraceElements) {
            strOut.append(stackTraceElement.getClassName()).append(" : ").append(stackTraceElement.getMethodName());//.append("\n");
        }
        strOut.append(e.getMessage());
        */
        return Utils.statusError(e.getMessage());

    }
}
