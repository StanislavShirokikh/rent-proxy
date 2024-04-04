package org.example.rentproxy.service.integration.currencyService.apiLayer.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiLayerRequest {
    private Integer amount;
    private String from;
    private String to;
}
