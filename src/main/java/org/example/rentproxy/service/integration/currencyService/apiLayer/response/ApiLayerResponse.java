package org.example.rentproxy.service.integration.currencyService.apiLayer.response;

import lombok.Data;

@Data
public class ApiLayerResponse {
    private String date;
    private Query query;
    private Double result;
    private Boolean success;
}
