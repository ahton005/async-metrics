package ru.zyablov.async.metrics.service;

import ru.zyablov.async.metrics.dto.SimpleMetric;

import java.util.List;

/**
 * Класс для работы с метриками
 */
public interface MetricProducerService {

    /**
     * Метод для получения списка метрик
     *
     * @return List<SimpleMetric> Список метрик
     */
    List<SimpleMetric> getMetrics();
}
