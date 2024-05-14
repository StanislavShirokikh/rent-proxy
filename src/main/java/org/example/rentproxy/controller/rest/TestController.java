package org.example.rentproxy.controller.rest;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.service.integration.currencyService.apiLayer.ApiLayerService;
import org.example.rentproxy.service.integration.currencyService.apiLayer.response.ApiLayerResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/convert")
@RequiredArgsConstructor
public class TestController {
    private final ApiLayerService apiLayerService;

    @GetMapping("/test")
    public ApiLayerResponse getApilayerResponse(@RequestParam String fromCurrency,
                                                @RequestParam String toCurrency,
                                                @RequestParam String amount) {
        return apiLayerService.convertCurrency(fromCurrency, toCurrency, amount);
    }
}
