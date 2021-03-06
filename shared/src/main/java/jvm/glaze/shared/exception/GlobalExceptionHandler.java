package jvm.glaze.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionDetails> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(this.getDetails(ex));
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionDetails> handleResourceAlreadyExists(ResourceAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(this.getDetails(ex));
    }

    private <T extends RuntimeException> ExceptionDetails getDetails(T ex) {
        return new ExceptionDetails(ex.getMessage(), ex.getClass().getSimpleName(), LocalDateTime.now());
    }

    record ExceptionDetails(String message, String exception, LocalDateTime timeStamp){}

}
