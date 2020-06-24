package br.rifas.controller;

import br.rifas.controller.util.ResponseError;
import br.rifas.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@ControllerAdvice
public class ExceptionsHandlers {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ResponseError> erro(ApiException e, HttpServletRequest request){
        ResponseError err = new ResponseError();
        err.setErros(Arrays.asList(e.getMessage()));
        err.setStatus(e.getStatus());
        err.setTimestamp(e.getTimestamp().getTime());
        return ResponseEntity.status(e.getStatus()).body(err);
    }
}
