package org.example.rentproxy.service.integration.currencyService.apiLayer;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.config.metrics.CurrencyServiceResponseTimer;
import org.example.rentproxy.config.metrics.CurrencyServiceSuccessfulRequestCounter;
import org.example.rentproxy.config.metrics.CurrencyServiceUnsuccessfulRequestCounter;
import org.example.rentproxy.service.integration.currencyService.CurrencyService;
import org.example.rentproxy.service.integration.currencyService.apiLayer.response.ApiLayerResponse;
import org.example.rentproxy.service.integration.currencyService.apiLayer.response.RestTemplateRetryable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiLayerService implements CurrencyService {
    private final RestTemplateRetryable restTemplateRetryable;
    private final ApiLayerServiceProperties apiLayerServiceProperties;
    private final CurrencyServiceSuccessfulRequestCounter successfulRequestCounter;
    private final CurrencyServiceUnsuccessfulRequestCounter unsuccessfulRequestCounter;
    private final CurrencyServiceResponseTimer timer;

    @Override
    public ApiLayerResponse convertCurrency(String fromCurrency, String toCurrency, double amount) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("apikey", apiLayerServiceProperties.getExchangeApiKey());

        HttpEntity<String> entity = new HttpEntity<>(headers);
        String finalUrl = getFinalUrl(apiLayerServiceProperties.getExchangeApiUrl());

        long startTime = System.currentTimeMillis();
        ApiLayerResponse apiLayerResponse;
        try {
            apiLayerResponse = sendRequest(entity, finalUrl, fromCurrency, toCurrency, amount);
        } finally {
            long endTime = System.currentTimeMillis();
            timer.record(endTime - startTime);
        }

        if (apiLayerResponse.getSuccess()) {
            successfulRequestCounter.increment();
        } else {
            unsuccessfulRequestCounter.increment();
        }

        return apiLayerResponse;
    }

    private ApiLayerResponse sendRequest(HttpEntity<String> entity,
                                         String finalUrl,
                                         String fromCurrency,
                                         String toCurrency,
                                         double amount
    ) {
        return restTemplateRetryable.exchange(
                        finalUrl,
                        HttpMethod.GET,
                        entity,
                        ApiLayerResponse.class,
                        fromCurrency,
                        toCurrency,
                        String.valueOf(amount)
                )
                .getBody();
    }

    private String getFinalUrl(String url) {
        return url + "/exchangerates_data/convert?from={from}&to={to}&amount={amount}";
    }
}
