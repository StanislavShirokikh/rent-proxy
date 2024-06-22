package org.example.rentproxy.config.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class CurrencyServiceSuccessfulRequestCounter extends CurrencyServiceResponsesCounter {
    public CurrencyServiceSuccessfulRequestCounter(MeterRegistry registry) {
        super(registry, "currency.service.successful.request.count",
                "Счетчик для подсчета количества успешных обращений к внешнему сервису Apilayer");
    }
}
