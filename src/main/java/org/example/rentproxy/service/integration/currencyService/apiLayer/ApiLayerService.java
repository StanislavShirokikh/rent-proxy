package org.example.rentproxy.service.integration.currencyService.apiLayer;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.rentproxy.service.integration.currencyService.CurrencyService;
import org.example.rentproxy.service.integration.currencyService.apiLayer.response.ApiLayerResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ApiLayerService implements CurrencyService {
    private final RestTemplate restTemplate;
    private final ApiLayerServiceProperties apiLayerServiceProperties;

    @Override
    public ApiLayerResponse convertCurrency(String fromCurrency, String toCurrency, String amount) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("apikey", apiLayerServiceProperties.getKey());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String apiUrlConvert = apiLayerServiceProperties.getUrl() + apiLayerServiceProperties.getPath() +
                apiLayerServiceProperties.getParams();

        return restTemplate.exchange(apiUrlConvert, HttpMethod.GET, entity, ApiLayerResponse.class,
                toCurrency, fromCurrency, amount).getBody();
    }
}
