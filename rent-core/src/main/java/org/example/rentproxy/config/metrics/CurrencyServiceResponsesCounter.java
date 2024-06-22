package org.example.rentproxy.config.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

public abstract class CurrencyServiceResponsesCounter {
    private final Counter counter;

    public CurrencyServiceResponsesCounter(MeterRegistry registry, String name, String description) {
        counter = Counter.builder(name)
                .description(description)
                .register(registry);
    }

    public void increment() {
        counter.increment();
    }
}
