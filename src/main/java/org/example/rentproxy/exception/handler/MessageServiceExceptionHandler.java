package org.example.rentproxy.exception.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.rentproxy.exception.ChatDisabledException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class MessageServiceExceptionHandler {
    @ExceptionHandler(ChatDisabledException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageResponse catchReservationRequestBadRequestException() {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse();
        errorMessageResponse.setErrorMessage("This post is archived, you cannot write a message.");
        log.error(errorMessageResponse.getErrorMessage());

        return errorMessageResponse;
    }
}
