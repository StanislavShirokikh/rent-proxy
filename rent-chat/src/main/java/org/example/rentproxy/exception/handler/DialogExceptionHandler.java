package org.example.rentproxy.exception.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.rentproxy.exception.ClosedDialogException;
import org.example.rentproxy.exception.DialogClosureNotAllowedException;
import org.example.rentproxy.exception.DialogDisabledException;
import org.example.rentproxy.exception.ErrorMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class DialogExceptionHandler {
    @ExceptionHandler(DialogDisabledException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageResponse catchDialogDisabledException() {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse();
        errorMessageResponse.setErrorMessage("This post is archived, you cannot write a message.");
        log.error(errorMessageResponse.getErrorMessage());

        return errorMessageResponse;
    }

    @ExceptionHandler(DialogClosureNotAllowedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageResponse catchDialogClosureNotAllowedException() {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse();
        errorMessageResponse.setErrorMessage("The dialog can only be closed by the owner of the post.");
        log.error(errorMessageResponse.getErrorMessage());

        return errorMessageResponse;
    }

    @ExceptionHandler(ClosedDialogException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageResponse catchClosedDialogException() {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse();
        errorMessageResponse.setErrorMessage("The dialog is closed. You can't write messages.");
        log.error(errorMessageResponse.getErrorMessage());

        return errorMessageResponse;
    }
}
