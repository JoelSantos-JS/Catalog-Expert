package com.joel.br.CatalogExpert.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<FieldMessage> userNotFoundExceptionResponseEntity(HttpServletRequest request, UserNotFoundException e) {
        FieldMessage fieldMessage = new FieldMessage();

        fieldMessage.setTimestamp(Instant.now());
        fieldMessage.setMessage(e.getMessage());
        fieldMessage.setPath(request.getRequestURI());
        fieldMessage.setStatus(HttpStatus.NOT_FOUND.toString());
        fieldMessage.setError("Not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(fieldMessage);
    }
}
