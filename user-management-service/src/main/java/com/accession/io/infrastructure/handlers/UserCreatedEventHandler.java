package com.accession.io.infrastructure.handlers;

import com.accession.io.common.contracts.interfaces.BaseEventHandler;
import com.accession.io.infrastructure.providers.QueueProvider;
import com.accession.io.message.events.UserCreatedEvent;
import io.sentry.spring.jakarta.tracing.SentrySpan;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-15 21:00:48
 */
@Component
@RequiredArgsConstructor
@SentrySpan(description = "UserCreatedEventHandler")
public class UserCreatedEventHandler implements BaseEventHandler<UserCreatedEvent> {

    @Value("${events.user.created.exchange}")
    private String exchange;

    private final QueueProvider queueProvider;

    @Override
    public void handle(UserCreatedEvent event) {
        queueProvider.publish(exchange, "", event);
    }
}
