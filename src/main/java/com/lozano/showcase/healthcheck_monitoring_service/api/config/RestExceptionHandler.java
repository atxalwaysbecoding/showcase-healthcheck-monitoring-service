package com.lozano.showcase.healthcheck_monitoring_service.api.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception ex){
        return new ResponseEntity<>("An exception occurred ex: "+ ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
