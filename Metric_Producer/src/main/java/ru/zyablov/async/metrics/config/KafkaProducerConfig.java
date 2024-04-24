package ru.zyablov.async.metrics.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static ru.zyablov.async.metrics.utils.StringUtils.TOPIC_METRICS;

@Configuration
public class KafkaProducerConfig {
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(TOPIC_METRICS)
                .partitions(1)
                .replicas(3)
                .build();
    }
}
