package org.example.rentproxy.service.integration.currencyService.apiLayer;

import org.example.rentproxy.service.integration.currencyService.CurrencyService;
import org.example.rentproxy.service.integration.currencyService.apiLayer.response.ApiLayerRequest;
import org.example.rentproxy.service.integration.currencyService.apiLayer.response.ApiLayerResponse;
import org.example.rentproxy.service.integration.currencyService.apiLayer.response.RestTemplateRetryable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ApiLayerServiceTest {
    @Autowired
    private CurrencyService currencyService;
    @MockBean
    private RestTemplateRetryable restTemplateRetryable;

    @Test
    public void convertTest() {
        createMockApiLayerResponse();

        ApiLayerResponse apiLayerResponse = currencyService.convertCurrency("rub", "usd", "1000");

        assertEquals("2024-03-28", apiLayerResponse.getDate());
        assertEquals("rub", apiLayerResponse.getApiLayerRequest().getFrom());
        assertEquals("usd", apiLayerResponse.getApiLayerRequest().getTo());
        assertEquals(1000, apiLayerResponse.getApiLayerRequest().getAmount());
        assertEquals(100000.0, apiLayerResponse.getResult());
        assertEquals(true, apiLayerResponse.getSuccess());
    }

    private void createMockApiLayerResponse() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("apikey", "bKUYerh75z8yYwIRqUp6BpMRjc2eM5TH");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ApiLayerResponse apiLayerResponse = ApiLayerResponse.builder()
                .date("2024-03-28")
                .apiLayerRequest(
                        ApiLayerRequest.builder()
                                .from("rub")
                                .to("usd")
                                .amount(1000)
                                .build()
                )
                .result(100000.0)
                .success(true)
                .build();

        when(restTemplateRetryable.exchange(
                anyString(),
                eq(HttpMethod.GET),
                eq(entity),
                eq(ApiLayerResponse.class),
                eq("rub"),
                eq("usd"),
                eq("1000")))
                .thenReturn(new ResponseEntity<>(apiLayerResponse, HttpStatus.OK));
    }
}