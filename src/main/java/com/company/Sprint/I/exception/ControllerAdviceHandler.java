package com.company.Sprint.I.exception;

import com.company.Sprint.I.dto.exception.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class ControllerAdviceHandler {

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ErrorDTO> notFoundExceptionHandler(NotFoundException e){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setException(e.getException());
        errorDTO.setStatus(e.getStatus().toString());
        errorDTO.setErrorMessage(e.getMessage());

        return ResponseEntity.status(e.getStatus()).body(errorDTO);
    }

    @ExceptionHandler(BadRequestException.class)
    ResponseEntity<ErrorDTO> badRequestException(BadRequestException b){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setException(b.getException());
        errorDTO.setStatus(b.getStatus().toString());
        errorDTO.setErrorMessage(b.getMessage());

        return ResponseEntity.status(b.getStatus()).body(errorDTO);
    }

}
