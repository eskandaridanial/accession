package com.accession.io.application.filters.global;

import io.sentry.BaggageHeader;
import io.sentry.Sentry;
import io.sentry.SentryTraceHeader;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-13 13:22:26
 */
@Component
public class B3PropagationFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange.mutate().request(r -> r.headers(headers -> {
            SentryTraceHeader trace = Sentry.getTraceparent();
            BaggageHeader baggage = Sentry.getBaggage();
            if (trace != null) headers.add(trace.getName(), trace.getValue());
            if (baggage != null) headers.add(baggage.getName(), baggage.getValue());
        })).build());
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
