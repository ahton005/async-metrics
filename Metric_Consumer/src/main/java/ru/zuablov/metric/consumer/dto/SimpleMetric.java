package ru.zuablov.metric.consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleMetric {
    private Long id;
    private String name;
    private String value;
    private User user;
}
