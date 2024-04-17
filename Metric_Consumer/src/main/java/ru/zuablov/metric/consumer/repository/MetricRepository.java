package ru.zuablov.metric.consumer.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.zuablov.metric.consumer.entity.SimpleMetricEntity;

import java.util.List;

@Repository
public interface MetricRepository extends ListCrudRepository<SimpleMetricEntity, Long> {
    List<SimpleMetricEntity> findAllByNameIgnoreCase(String name);
}
