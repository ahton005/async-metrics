package ru.zyablov.async.metrics.service;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.zyablov.async.metrics.dto.SimpleMetric;
import ru.zyablov.async.metrics.dto.User;

import java.math.BigDecimal;
import java.util.List;

import static ru.zyablov.async.metrics.utils.StringUtils.JVM_MEMORY_USED;
import static ru.zyablov.async.metrics.utils.StringUtils.JVM_THREADS_LIVE;

@Service
@RequiredArgsConstructor
public class DefaultMetricsProducer implements MetricProducerService {

    private final MeterRegistry meterRegistry;

    private static final List<String> METRICS = List.of(JVM_MEMORY_USED, JVM_THREADS_LIVE);

    public List<SimpleMetric> getMetrics() {
        var usr = getUserData(SecurityContextHolder.getContext().getAuthentication());
        return METRICS.stream().map(m -> SimpleMetric.builder()
                .value(BigDecimal.valueOf(meterRegistry.get(m).gauge().value()).toString())
                .name(m)
                .user(usr)
                .build()).toList();
    }

    private User getUserData(Authentication authentication) {
        String role = authentication.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("ROLE_ADMIN")) ? "ADMIN" : "USER";
        return new User(authentication.getName(), role);
    }
}
