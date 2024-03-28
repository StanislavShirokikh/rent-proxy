package org.example.rentproxy.service.integration.currencyService.apiLayer;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ApiLayerServiceProperties {
    @Value("${exchange.api.url}")
    private String url;
    @Value("${exchange.api.path}")
    private String path;
    @Value("${exchange.api.params}")
    private String params;
    @Value("${exchange.api.key}")
    private String key;
}
