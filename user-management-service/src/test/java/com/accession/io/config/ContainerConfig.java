package com.accession.io.config;

import com.redis.testcontainers.RedisContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.containers.wait.strategy.Wait;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-12-29 12:56:40
 */
@Slf4j
@TestConfiguration(proxyBeanMethods = false)
public class ContainerConfig {

    @Bean
    @Scope("singleton")
    @ServiceConnection
    public PostgreSQLContainer<?> postgresContainer(@Value("${test.container.postgres.image}") String image){
        return new PostgreSQLContainer<>(image)
                .withExposedPorts(5432)
                .withUsername("postgres")
                .withPassword("postgres")
                .withDatabaseName("user-management-service")
                .waitingFor(Wait.forListeningPort());
    }

    @Bean
    @Scope("singleton")
    @ServiceConnection
    public RabbitMQContainer rabbitContainer(@Value("${test.container.rabbit.image}") String image){
        return new RabbitMQContainer(image)
                .withExposedPorts(5672)
                .waitingFor(Wait.forListeningPort());
    }

    @Bean
    @Scope("singleton")
    @ServiceConnection
    public RedisContainer redisContainer(@Value("${test.container.redis.image}") String image){
        return new RedisContainer(image)
                .withExposedPorts(6379)
                .waitingFor(Wait.forListeningPort());
    }
}
