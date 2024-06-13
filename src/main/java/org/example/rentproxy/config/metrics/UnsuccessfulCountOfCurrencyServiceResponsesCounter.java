package org.example.rentproxy.config.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class UnsuccessfulCountOfCurrencyServiceResponsesCounter {
    private final Counter counter;

    public UnsuccessfulCountOfCurrencyServiceResponsesCounter(MeterRegistry registry) {
        counter = Counter.builder("UnsuccessfulRequestsOfCurrencyService ")
                .description("Подсчет количества неуспешных обращений к внешнему сервису Apilayer")
                .register(registry);
    }

    public void increment() {
        counter.increment();
    }
}
