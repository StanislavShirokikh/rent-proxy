package org.example.rentproxy.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.rentproxy.exception.ReservationRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ReservationRequestExceptionHandler {

    @ExceptionHandler(ReservationRequestException.class)
    public ResponseEntity<ErrorMessageResponse> catchReservationRequestNotFoundException() {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse();
        errorMessageResponse.setMessage("Reservation Request with this id not found");
        log.error(errorMessageResponse.getMessage());

        return new ResponseEntity<>(errorMessageResponse, HttpStatus.BAD_REQUEST);
    }
}
