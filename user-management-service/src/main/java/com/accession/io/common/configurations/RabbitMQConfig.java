package com.accession.io.common.configurations;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-15 20:53:44
 */
@Configuration
public class RabbitMQConfig {

    @Value("${events.user.created.exchange}")
    private String userCreatedExchangeName;

    @Value("${events.user.verified.queue}")
    private String userVerifiedQueue;

    @Value("${events.user.created.cms.queue}")
    private String userCreatedCmsQueue;

    @Value("${events.user.created.ups.queue}")
    private String userCreatedUpsQueue;

    @Bean
    public FanoutExchange userCreatedExchange() {
        return new FanoutExchange(this.userCreatedExchangeName);
    }

    @Bean
    public Queue userVerifiedQueue() {
        return new Queue(this.userVerifiedQueue, true);
    }

    @Bean
    public Queue userCreatedCmsQueue() {
        return new Queue(this.userCreatedCmsQueue, true);
    }

    @Bean
    public Queue userCreatedUpsQueue() {
        return new Queue(this.userCreatedUpsQueue, true);
    }

    @Bean
    public Binding userCreatedCmsBinding(FanoutExchange userCreatedExchange, Queue userCreatedCmsQueue) {
        return BindingBuilder.bind(userCreatedCmsQueue).to(userCreatedExchange);
    }

    @Bean
    public Binding userCreatedUpsBinding(FanoutExchange userCreatedExchange, Queue userCreatedUpsQueue) {
        return BindingBuilder.bind(userCreatedUpsQueue).to(userCreatedExchange);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}
