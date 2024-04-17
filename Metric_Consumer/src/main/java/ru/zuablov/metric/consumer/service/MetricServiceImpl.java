package ru.zuablov.metric.consumer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zuablov.metric.consumer.dto.SimpleMetric;
import ru.zuablov.metric.consumer.entity.SimpleMetricEntity;
import ru.zuablov.metric.consumer.exception.NoSuchMetricException;
import ru.zuablov.metric.consumer.repository.MetricRepository;
import ru.zuablov.metric.consumer.repository.UserRepository;
import ru.zuablov.metric.consumer.utils.ConvertUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MetricServiceImpl implements MetricService {

    private final MetricRepository metricRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void save(SimpleMetric metric) {
        if (metric != null && metric.getUser() != null) {
            var userEntity = userRepository.findByName(metric.getUser().getName()).orElse(ConvertUtils.apiToDomain(metric.getUser()));
            metricRepository.save(SimpleMetricEntity.builder()
                    .value(metric.getValue())
                    .name(metric.getName())
                    .user(userEntity)
                    .build());
        }
    }

    @Override
    public SimpleMetric findById(Long id) throws NoSuchMetricException {
        return metricRepository.findById(id).map(ConvertUtils::domainToApi).orElseThrow(() ->
                new NoSuchMetricException("Не найдена метрика с Id = " + id)
        );
    }

    @Override
    public List<SimpleMetric> findAllWithName(String name) {
        if (name == null) {
            return metricRepository.findAll().stream().map(ConvertUtils::domainToApi).toList();
        }
        return findAllByName(name);
    }

    @Override
    public List<SimpleMetric> findAllByName(String name) {
        return metricRepository.findAllByNameIgnoreCase(name).stream().map(ConvertUtils::domainToApi).toList();
    }
}
