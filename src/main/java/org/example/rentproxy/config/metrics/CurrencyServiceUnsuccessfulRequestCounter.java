package org.example.rentproxy.config.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class CurrencyServiceUnsuccessfulRequestCounter extends CurrencyServiceResponsesCounter {
    public CurrencyServiceUnsuccessfulRequestCounter(MeterRegistry registry) {
        super(registry, "currency.service.unsuccessful.request.count",
                "Счетчик для подсчета количества неуспешных обращений к внешнему сервису Apilayer");
    }
}
