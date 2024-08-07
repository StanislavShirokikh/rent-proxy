package org.example.rentproxy.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.example.rentproxy.exception.ReservationRequestBadRequestException;
import org.example.rentproxy.exception.ReservationRequestNotFoundException;

@ControllerAdvice
@Slf4j
public class ReservationRequestExceptionHandler {

    @ExceptionHandler(ReservationRequestBadRequestException.class)
    public ResponseEntity<ErrorMessageResponse> catchReservationRequestBadRequestException() {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse();
        errorMessageResponse.setErrorMessage("Reservation Request with this id already exist");
        log.error(errorMessageResponse.getErrorMessage());

        return new ResponseEntity<>(errorMessageResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReservationRequestNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> catchReservationRequestNotFoundException() {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse();
        errorMessageResponse.setErrorMessage("Reservation Request with this id not found");
        log.error(errorMessageResponse.getErrorMessage());

        return new ResponseEntity<>(errorMessageResponse, HttpStatus.BAD_REQUEST);
    }
}
