dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.flywaydb:flyway-core")

    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.testcontainers:postgresql")
}
