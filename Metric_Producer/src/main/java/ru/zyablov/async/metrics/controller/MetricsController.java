package ru.zyablov.async.metrics.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zyablov.async.metrics.service.MetricProducerService;

import static ru.zyablov.async.metrics.utils.StringUtils.TOPIC_METRICS;

/**
 * Класс для отправки метрик в кафку
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class MetricsController {

    private final KafkaTemplate<Object, Object> template;
    private final MetricProducerService producerService;

    /**
     * Отправить метрики в кафку
     *
     * @return ResponseEntity
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешный ответ"),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере")
    })
    @PostMapping("/metrics")
    public ResponseEntity<?> sendMetrics() {
        producerService.getMetrics().forEach(metric -> template.send(TOPIC_METRICS, metric));
        return ResponseEntity.ok().build();
    }
}
