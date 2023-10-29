package com.example.exception;


import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HashMap<String,String> methodArgumentNotValidException(MethodArgumentNotValidException e){
        String msg = e.getMessage();
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        HashMap<String,String> map = new HashMap<>();
        for (ObjectError obj: allErrors) {
            String msg1 = obj.getDefaultMessage();
            String field = ((FieldError)obj).getField();
            map.put(field,msg1);
        }

        return map;
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handlenosuchelement(NoSuchElementException ex){
        return new ResponseEntity<>("No such value present",HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(JSONException.class)
    public String handlerJsonex(JSONException ex){
        return "Missing value";
    }


}
