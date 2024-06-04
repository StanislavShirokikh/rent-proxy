package org.example.rentproxy.service.integration.currencyService.apiLayer.response;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
@Builder
public class ApiLayerResponse {
    private String date;
    private ApiLayerRequest apiLayerRequest;
    private Double result;
    private Boolean success;
}
