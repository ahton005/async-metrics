# Общее описание сервисов

Учебный проект для отработки навыков работы с Spring-Kafka. Выполнен в виде репозитория, содержащего два сервиса, а
также докер-файлов для поднятия необходимой инфраструктуры. Проект собран на gradle.

Репозиторий содержит следующие сервисы

* [Metric_Producer](Metric_Producer/README.md)
* [Metric_Consumer](Metric_Consumer/README.md)

## Архитектура

Сервис Metric_Producer отправляет метрики определенного формата в кластер кафки. Кластер состоит из 3-х брокеров. Данное
решение выполнено для демонстрации репликации кафки.
Сервис Metric_Consumer служит для принятия и последующей обработки сообщений продюсера.
Более подробное описание каждого сервисам приведено в README.md файлах.

## Тестирование

Создан файл [metric](rest/metric.http) для ручного тестирования.

## Запуск

Дополнительный вариант запуска - команда ```./gradlew bootRun``` в корне проекта.
