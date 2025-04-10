package com.accession.io.common.configurations;

import io.sentry.BaggageHeader;
import io.sentry.Sentry;
import io.sentry.SentryTraceHeader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-13 14:26:50
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            SentryTraceHeader trace = Sentry.getTraceparent();
            if (trace != null) request.getHeaders().add(trace.getName(), trace.getValue());
            BaggageHeader baggage = Sentry.getBaggage();
            if (baggage != null) request.getHeaders().add(baggage.getName(), baggage.getValue());
            return execution.execute(request, body);
        });
        return restTemplate;
    }
}
