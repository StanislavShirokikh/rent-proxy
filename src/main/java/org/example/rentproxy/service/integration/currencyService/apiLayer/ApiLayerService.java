package org.example.rentproxy.service.integration.currencyService.apiLayer;

import lombok.RequiredArgsConstructor;
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


    @Override
    public ApiLayerResponse convertCurrency(String fromCurrency, String toCurrency, double amount) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("apikey", apiLayerServiceProperties.getExchangeApiKey());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String finalUrl = getFinalUrl(apiLayerServiceProperties.getExchangeApiUrl());
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
