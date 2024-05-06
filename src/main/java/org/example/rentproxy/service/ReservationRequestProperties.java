package org.example.rentproxy.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "rent-proxy.reservation.request")
public class ReservationRequestProperties {
    private Long outdatedPeriod;
}
