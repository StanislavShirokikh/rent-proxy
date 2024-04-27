package org.example.rentproxy.service.integration.currencyService.apiLayer.response;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component

@RequiredArgsConstructor
public class RestTemplateRetryable extends RestTemplate {
    private final RetryTemplate retryTemplate;

    @Override
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
                                          Class<T> responseType, Object... uriVariables) throws RestClientException {
        return this.retryTemplate.execute(retryContext -> super.exchange(url, method, requestEntity, responseType, uriVariables));
    }
}
