package com.accounts.api.exception;


import com.accounts.api.dto.ErrorResponse;
import com.accounts.api.exception.types.CustomerAlreadyExistException;
import com.accounts.api.exception.types.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception exception,
                                                               WebRequest webRequest) {
        return new ResponseEntity<>(ErrorResponse.builder().
                errorMessage(exception.getMessage())
                .apiPath(webRequest.getDescription(false))
                .errorTime(LocalDateTime.now())
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                         WebRequest webRequest) {
        return new ResponseEntity<>(ErrorResponse.builder().
                errorMessage(exception.getMessage())
                .apiPath(webRequest.getDescription(false))
                .errorTime(LocalDateTime.now())
                .errorCode(HttpStatus.NOT_FOUND)
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleCustomerAlreadyExistsException(CustomerAlreadyExistException exception,
                                                                              WebRequest webRequest) {
        return new ResponseEntity<>(ErrorResponse.builder().
                errorMessage(exception.getMessage())
                .apiPath(webRequest.getDescription(false))
                .errorTime(LocalDateTime.now())
                .errorCode(HttpStatus.BAD_REQUEST)
                .build(), HttpStatus.BAD_REQUEST);
    }
}
