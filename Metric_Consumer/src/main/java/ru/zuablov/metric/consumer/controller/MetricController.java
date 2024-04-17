package ru.zuablov.metric.consumer.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.zuablov.metric.consumer.dto.SimpleMetric;
import ru.zuablov.metric.consumer.exception.NoSuchMetricException;
import ru.zuablov.metric.consumer.service.MetricService;

import java.util.List;

import static ru.zuablov.metric.consumer.utils.StringUtils.APPLICATION_JSON;

@RestController
@RequiredArgsConstructor
@RequestMapping("/metrics")
public class MetricController {
    private final MetricService metricService;

    /**
     * Получить список метрик по имени, если не передать имя получим все метрики.
     *
     * @return List<SimpleMetric>
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешный ответ"),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере")
    })
    @GetMapping(value = "", produces = APPLICATION_JSON)
    public ResponseEntity<List<SimpleMetric>> getAllMetrics(@RequestParam(value = "name", required = false) String name) {
        return ResponseEntity.ok(metricService.findAllWithName(name));
    }

    /**
     * Получить метрику по id.
     *
     * @return SimpleMetric
     */
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешный ответ"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос"),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере")
    })
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON)
    public ResponseEntity<?> getMetricById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(metricService.findById(id));
        } catch (NoSuchMetricException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
