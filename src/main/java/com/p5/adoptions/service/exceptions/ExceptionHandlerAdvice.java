package com.p5.adoptions.service.exceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ApiError(HttpStatus.EXPECTATION_FAILED, "Payload broken", ex.getMessage()),status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ApiError onConstraintValidationException(ConstraintViolationException ex){
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, "Arguments validation failed");

        for (ConstraintViolation violation :ex.getConstraintViolations()){
            error.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage(), String.valueOf(violation.getInvalidValue())));
        }

        return error;
    }



    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ApiError(HttpStatus.CONFLICT, ex.getMessage()),status);
    }

    @ExceptionHandler(ShelterLocationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ApiError onShelterLocationException (ShelterLocationException ex){
        ApiError error = new ApiError(HttpStatus.EXPECTATION_FAILED, ex.getMessage());

        return error;
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    ApiError onValidationException (ValidationException ex){
       return ex.getApiError();

    }
}
