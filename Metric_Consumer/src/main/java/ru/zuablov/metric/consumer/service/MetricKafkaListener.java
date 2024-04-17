package ru.zuablov.metric.consumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.zuablov.metric.consumer.dto.SimpleMetric;

import static ru.zuablov.metric.consumer.utils.StringUtils.TOPIC_METRICS;

@KafkaListener(topics = TOPIC_METRICS)
@Service
@Slf4j
@RequiredArgsConstructor
public class MetricKafkaListener {
    private final MetricService metricService;

    @KafkaHandler
    public void getMetrics(SimpleMetric metric) {
        metricService.save(metric);
        log.info("Received metric: {}", metric);
    }
}
