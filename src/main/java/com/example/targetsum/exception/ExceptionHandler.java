package com.example.targetsum.exception;

import com.example.targetsum.model.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object>  handleBadReqException(Exception ex){
        ResponseModel responseObj = new ResponseModel();
        responseObj.setMessage(ex.getMessage());
        ResponseEntity<Object> response = new ResponseEntity<>(responseObj,HttpStatus.BAD_REQUEST);
        return response;
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object>  handleNotFoundExceptionException(Exception ex){
        ResponseModel responseObj = new ResponseModel();
        responseObj.setMessage(ex.getMessage());
        ResponseEntity<Object> response = new ResponseEntity<>(responseObj,HttpStatus.NOT_FOUND);
        return response;
    }
}
