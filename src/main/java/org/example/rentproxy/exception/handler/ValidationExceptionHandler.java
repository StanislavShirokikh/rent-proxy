package org.example.rentproxy.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ValidationExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorMessageResponse> catchMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ErrorMessageResponse> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse();
            errorMessageResponse.setMessage(errorMessage);
            errors.add(errorMessageResponse);
        });

        return errors;
    }
}
