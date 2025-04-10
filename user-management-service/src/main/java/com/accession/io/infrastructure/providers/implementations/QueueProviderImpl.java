package com.accession.io.infrastructure.providers.implementations;

import com.accession.io.infrastructure.providers.QueueProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-11 13:27:00
 */
@Component
@RequiredArgsConstructor
public class QueueProviderImpl implements QueueProvider {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(String exchange, String queue, Object message) {
        rabbitTemplate.convertAndSend(exchange, queue, message);
    }
}
