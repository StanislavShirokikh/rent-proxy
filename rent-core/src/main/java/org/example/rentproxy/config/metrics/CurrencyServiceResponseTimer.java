package org.example.rentproxy.config.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CurrencyServiceResponseTimer {
    private final Timer timer;

    public CurrencyServiceResponseTimer(MeterRegistry registry) {
        timer = Timer.builder("currency.service.response.timer")
                .description("Подсчет времени обращения к внешнему сервису")
                .register(registry);
    }

    public void record(long time) {
        timer.record(time, TimeUnit.MILLISECONDS);
    }
}
