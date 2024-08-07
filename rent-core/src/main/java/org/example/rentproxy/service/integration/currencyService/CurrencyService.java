package org.example.rentproxy.service.integration.currencyService;

import org.example.rentproxy.service.integration.currencyService.apiLayer.response.ApiLayerResponse;
import org.example.rentproxy.service.integration.currencyService.apiLayer.response.ApiLayerResponse;
import org.example.rentproxy.service.integration.currencyService.apiLayer.response.ApiLayerResponse;

public interface CurrencyService {
    ApiLayerResponse convertCurrency(String fromCurrency, String toCurrency, double amount);
}
