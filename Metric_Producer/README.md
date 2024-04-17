# Сервис отправки метрик в кафку

## Общее описание

Сервис содержит следующие методы

* POST /metrics - метод служит для отправки метрик в кафку.
  Накрыт авторизацией для аутентификации пользователя. Идея заключается в том, чтобы давать возможность отравлять
  метрики администратору и не давать определенному пользователю.

## Описание Kafka

Для демонстрации репликации кафка, разработан докер-файл, который поднимает кластер кафки из трех брокеров, одного
контейнера zookeeper, а также контейнер с UI для просмотра топиков, сообщений, "живых" брокеров и тд(доступен на порту
9000
хостовой машины), файл находится в Metric_Producer/docker-compose.yaml.

## OpenApi

/swagger-ui/index.html - страница графической оболочки OpenApi

## Авторизация

Создана простейшая система авторизации In-memory. С тремя пользователями(два админа, один простой пользователь). Для
демонстрации того, что только админ может отправить метрики в кафку, причем отправляется также логин и роль пользователя
который инициировал отправку метрик.
Логопасы
login - admin password - admin
login - admin1 password - admin1
login - user password - user

## Описание метрик

В рамках данного проекта используются две метрики

* JVM_MEMORY_USED - используемая память JVM
* JVM_THREADS_LIVE - количество "живых" потоков

## Запуск

Сервис запускается командой ./gradlew :Metric_Producer:bootRun из корневой директории(\async-metrics\), предварительно
необходимо развернуть кластер кафки в докере.