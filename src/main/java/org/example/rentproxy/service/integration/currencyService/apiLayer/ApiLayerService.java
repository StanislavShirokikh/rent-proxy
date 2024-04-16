package org.example.rentproxy.service.integration.currencyService.apiLayer;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.service.integration.currencyService.CurrencyService;
import org.example.rentproxy.service.integration.currencyService.apiLayer.response.ApiLayerResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ApiLayerService implements CurrencyService {
    private final RestTemplate restTemplate;
    private final ApiLayerServiceProperties apiLayerServiceProperties;

    @Retryable(
            retryFor = {RuntimeException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 500))
    @Override
    public ApiLayerResponse convertCurrency(String fromCurrency, String toCurrency, String amount) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("apikey", apiLayerServiceProperties.getExchangeApiKey());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String finalUrl = getFinalUrl(apiLayerServiceProperties.getExchangeApiUrl());
        return restTemplate.exchange(finalUrl, HttpMethod.GET, entity, ApiLayerResponse.class,
                fromCurrency, toCurrency, amount).getBody();
    }

    private String getFinalUrl(String url) {
        return url + "/exchangerates_data/convert?from={from}&to={to}&amount={amount}";
    }
}
