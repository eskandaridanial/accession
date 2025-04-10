package com.accession.io.application.filters;

import com.accession.io.infrastructure.adapters.DeAuthorizeUserAdapter;
import com.accession.io.message.commands.DeAuthorizeUserCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-13 13:22:26
 */
@Component
public class DeAuthorizeUserFilter extends AbstractGatewayFilterFactory<DeAuthorizeUserFilter.Config> {

    @Value("${de-authorization.headers.token-name}")
    private String deAuthorizationHeaderName;

    private final DeAuthorizeUserAdapter deAuthorizeUserAdapter;

    public static class Config { }

    public DeAuthorizeUserFilter(DeAuthorizeUserAdapter deAuthorizeUserAdapter) {
        super(Config.class);
        this.deAuthorizeUserAdapter = deAuthorizeUserAdapter;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String accessToken = exchange.getRequest().getHeaders().getFirst(this.deAuthorizationHeaderName);
            if (ObjectUtils.isEmpty(accessToken)) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            DeAuthorizeUserCommand deAuthorizeUserCommand = DeAuthorizeUserCommand.of(accessToken);
            deAuthorizeUserAdapter.execute(deAuthorizeUserCommand);

            return chain.filter(exchange);
        };
    }
}
