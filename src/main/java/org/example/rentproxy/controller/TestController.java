package org.example.rentproxy.controller;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.service.integration.currencyService.CurrencyService;
import org.example.rentproxy.service.integration.currencyService.apiLayer.response.ApiLayerResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final CurrencyService currencyService;
    @GetMapping("rent-proxy/convert")
    public ApiLayerResponse convertCurrency(@RequestParam String fromCurrency,
                                            @RequestParam String toCurrency,
                                            @RequestParam String amount) {
        return currencyService.convertCurrency(fromCurrency, toCurrency, amount);
    }
}
