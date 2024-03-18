package org.example.rentproxy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WebCurrencyService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;


    public Double convertCurrency(String fromCurrency, String toCurrency, String amount) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        String apiKey = "bKUYerh75z8yYwIRqUp6BpMRjc2eM5TH";
        headers.add("apikey", apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String apiUrlConvert = "https://api.apilayer.com/exchangerates_data/convert?to={to}&from={from}&amount={amount}";
        ResponseEntity<String> response = restTemplate.exchange(apiUrlConvert, HttpMethod.GET, entity, String.class,
                toCurrency, fromCurrency, amount);

        JsonNode jsonNode = objectMapper.readTree(response.getBody());

        return jsonNode.get("result").asDouble();
    }

    @PostConstruct
    public void init() throws JsonProcessingException {
        System.out.println(convertCurrency("USD", "RUB", "100"));
    }
}
