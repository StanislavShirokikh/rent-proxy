package org.example.rentproxy.service.integration.currencyService.apiLayer.response;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
@Builder
public class ApiLayerRequest {
    private Integer amount;
    private String from;
    private String to;
}
