package org.example.rentproxy.exception.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.rentproxy.config.metrics.UnsuccessfulCountOfCurrencyServiceResponsesCounter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class CurrencyServiceExceptionHandler {
    private final UnsuccessfulCountOfCurrencyServiceResponsesCounter counter;

    @ExceptionHandler(HttpClientErrorException.TooManyRequests.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public ErrorMessageResponse handleApiRateLimitExceeded(HttpClientErrorException.TooManyRequests ex) {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse();
        errorMessageResponse.setMessage(ex.getResponseBodyAsString());
        counter.increment();
        log.error(errorMessageResponse.getMessage());
        return errorMessageResponse;
    }
}
