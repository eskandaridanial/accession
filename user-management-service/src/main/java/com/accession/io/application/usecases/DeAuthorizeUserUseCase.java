package com.accession.io.application.usecases;

import com.accession.io.application.providers.JwtProvider;
import com.accession.io.common.contracts.interfaces.BaseUseCase;
import com.accession.io.infrastructure.providers.CacheProvider;
import com.accession.io.message.commands.DeAuthorizeUserCommand;
import com.accession.io.message.responses.DeAuthorizeUserResponse;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:27:45
 */
@Component
@RequiredArgsConstructor
public class DeAuthorizeUserUseCase implements BaseUseCase<DeAuthorizeUserCommand, DeAuthorizeUserResponse> {

    @Value("${jwt.token.revoke-prefix}")
    private String revokeList;

    private final JwtProvider jwtProvider;
    private final CacheProvider cacheProvider;

    @Override
    public DeAuthorizeUserResponse execute(DeAuthorizeUserCommand command) {
        Claims claims = jwtProvider.extract(command.accessToken());
        Boolean success = cacheProvider.put(revokeList + claims.getId(), Boolean.TRUE, claims.getExpiration());
        return DeAuthorizeUserResponse.of(success);
    }
}
