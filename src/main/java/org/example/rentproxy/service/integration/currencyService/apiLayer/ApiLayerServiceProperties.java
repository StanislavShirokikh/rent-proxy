package org.example.rentproxy.service.integration.currencyService.apiLayer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "exchange.api")
@Data
public class ApiLayerServiceProperties {
    private String url;
    private String path;
    private String params;
    private String key;
}
