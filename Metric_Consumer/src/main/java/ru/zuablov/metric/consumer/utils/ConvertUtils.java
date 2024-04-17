package ru.zuablov.metric.consumer.utils;

import ru.zuablov.metric.consumer.dto.SimpleMetric;
import ru.zuablov.metric.consumer.dto.User;
import ru.zuablov.metric.consumer.entity.SimpleMetricEntity;
import ru.zuablov.metric.consumer.entity.UserEntity;

public class ConvertUtils {

    private ConvertUtils() {
    }

    /**
     * Преобразовать метрику в объект транспорта.
     *
     * @return SimpleMetric
     */
    public static SimpleMetric domainToApi(SimpleMetricEntity simpleMetricEntity) {
        if (simpleMetricEntity == null) return SimpleMetric.builder().build();
        return SimpleMetric.builder()
                .id(simpleMetricEntity.getId())
                .name(simpleMetricEntity.getName())
                .value(simpleMetricEntity.getValue())
                .user(domainToApi(simpleMetricEntity.getUser()))
                .build();
    }

    /**
     * Преобразовать метрику в объект БД.
     *
     * @return SimpleMetricEntity
     */
    public static SimpleMetricEntity apiToDomain(SimpleMetric simpleMetric) {
        if (simpleMetric == null) return SimpleMetricEntity.builder().build();
        return SimpleMetricEntity.builder()
                .id(simpleMetric.getId())
                .name(simpleMetric.getName())
                .value(simpleMetric.getValue())
                .user(apiToDomain(simpleMetric.getUser()))
                .build();
    }

    /**
     * Преобразовать пользователя в объект транспорта.
     *
     * @return User
     */
    public static User domainToApi(UserEntity userEntity) {
        if (userEntity == null) return User.builder().build();
        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .role(userEntity.getRole())
                .build();
    }

    /**
     * Преобразовать пользователя в объект БД.
     *
     * @return UserEntity
     */
    public static UserEntity apiToDomain(User user) {
        if (user == null) return UserEntity.builder().build();
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }
}
