package com.accession.io.application.filters;

import com.accession.io.exception.AuthenticationException;
import com.accession.io.exception.reasons.AuthenticationReason;
import com.accession.io.infrastructure.adapters.AuthorizeUserAdapter;
import com.accession.io.message.commands.AuthorizeUserCommand;
import com.accession.io.message.responses.AuthorizeUserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-13 13:22:26
 */
@Component
public class AuthorizeUserFilter extends AbstractGatewayFilterFactory<AuthorizeUserFilter.Config> {

    @Value("${authorization.headers.token-name}")
    private String authorizationHeaderName;

    @Value("${authorization.headers.realm-name}")
    private String realmHeaderName;

    @Value("${authorization.headers.user-name}")
    private String userHeaderName;

    private final AuthorizeUserAdapter authorizeUserAdapter;

    public static class Config { }

    public AuthorizeUserFilter(AuthorizeUserAdapter authorizeUserAdapter) {
        super(Config.class);
        this.authorizeUserAdapter = authorizeUserAdapter;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String accessToken = exchange.getRequest().getHeaders().getFirst(this.authorizationHeaderName);
            if (ObjectUtils.isEmpty(accessToken))
                throw new AuthenticationException(AuthenticationReason.accessTokenIsEmpty());

            String realmId = exchange.getRequest().getHeaders().getFirst(this.realmHeaderName);
            if (ObjectUtils.isEmpty(realmId))
                throw new AuthenticationException(AuthenticationReason.realmIdIsEmpty());

            String endpoint = exchange.getRequest().getURI().getRawPath();
            String httpMethod = exchange.getRequest().getMethod().name();
            AuthorizeUserCommand authorizeUserCommand = AuthorizeUserCommand.of(realmId, endpoint, httpMethod, accessToken);
            AuthorizeUserResponse response = authorizeUserAdapter.execute(authorizeUserCommand).result();
            String userId = response.userId();
            return chain.filter(exchange.mutate().request(r -> r.headers(headers -> headers.add(this.userHeaderName, userId))).build());
        };
    }
}
