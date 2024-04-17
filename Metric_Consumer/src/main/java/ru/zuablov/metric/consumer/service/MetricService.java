package ru.zuablov.metric.consumer.service;

import ru.zuablov.metric.consumer.dto.SimpleMetric;
import ru.zuablov.metric.consumer.exception.NoSuchMetricException;

import java.util.List;

/**
 * Сервис для работы с метриками
 */
public interface MetricService {
    /**
     * Сохранить метрику
     *
     * @param metric Метрика
     */
    void save(SimpleMetric metric);

    /**
     * Получить метрику по идентификатору
     *
     * @param id Id метрики
     */
    SimpleMetric findById(Long id) throws NoSuchMetricException;

    /**
     * Получить все метрики
     */
    List<SimpleMetric> findAllWithName(String name);

    /**
     * Получить все метрики с определенным именем
     */
    List<SimpleMetric> findAllByName(String name);
}
