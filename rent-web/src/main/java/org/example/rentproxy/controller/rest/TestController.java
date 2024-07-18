package org.example.rentproxy.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.example.rentproxy.service.integration.currencyService.CurrencyService;
import org.example.rentproxy.service.integration.currencyService.apiLayer.response.ApiLayerResponse;

@RestController
@RequestMapping("/convert")
@RequiredArgsConstructor
public class TestController {
    private final CurrencyService currencyService;

    @GetMapping("/test")
    public ApiLayerResponse getApilayerResponse(@RequestParam String fromCurrency,
                                                @RequestParam String toCurrency,
                                                @RequestParam Double amount) {
        return currencyService.convertCurrency(fromCurrency, toCurrency, amount);
    }
}
