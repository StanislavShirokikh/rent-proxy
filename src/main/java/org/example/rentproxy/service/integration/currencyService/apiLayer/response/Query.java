package org.example.rentproxy.service.integration.currencyService.apiLayer.response;

import lombok.Data;

@Data
public class Query {
    private Integer amount;
    private String from;
    private String to;
}
