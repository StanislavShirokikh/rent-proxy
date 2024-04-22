package org.example.rentproxy.service.integration.currencyService.apiLayer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "apilayer.service")
@Data
public class ApiLayerServiceProperties {
    private String exchangeApiUrl;
    private String exchangeApiKey;
    private Integer maxAttempts;
    private Integer backOffPeriod;
}
