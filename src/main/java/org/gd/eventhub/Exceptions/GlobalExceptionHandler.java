package org.gd.eventhub.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private Map<String,String> error(String message){

        Map<String,String> map = new HashMap<>();
        map.put("message", message);

        return map;
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handleMethodArgumentNotValid(MethodArgumentNotValidException e){
        Map<String,String> map = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            map.put(fieldName,error.getDefaultMessage());
        });
        return map;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> resourceNotFoundException(ResourceNotFoundException ex) {
        return error(ex.getMessage());}

    @ExceptionHandler(ForbiddenOperationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> duplicateResourceException(ForbiddenOperationException ex) {
        return error(ex.getMessage());
    }
}
