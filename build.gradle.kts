import org.springframework.boot.gradle.tasks.run.BootRun

group = "ru.zyablov.async.metrics"
version = "0.0.1-SNAPSHOT"

plugins {
    java
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    group = rootProject.group
    version = rootProject.version

    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "java")

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
        implementation("org.springframework.kafka:spring-kafka")

        annotationProcessor("org.projectlombok:lombok")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.boot:spring-boot-testcontainers")
        testImplementation("org.springframework.kafka:spring-kafka-test")
        testImplementation("org.testcontainers:junit-jupiter")
        testImplementation("org.testcontainers:kafka")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}

tasks.withType<BootRun> {
    dependsOn(":Metric_Producer:bootRun", ":Metric_Consumer:bootRun")
}
