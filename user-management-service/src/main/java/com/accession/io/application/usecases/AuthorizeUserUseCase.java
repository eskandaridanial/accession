package com.accession.io.application.usecases;

import com.accession.io.application.providers.JwtProvider;
import com.accession.io.application.services.RealmService;
import com.accession.io.application.services.RoleService;
import com.accession.io.application.services.UserService;
import com.accession.io.common.contracts.interfaces.BaseUseCase;
import com.accession.io.domain.entities.Realm;
import com.accession.io.domain.entities.Role;
import com.accession.io.domain.entities.User;
import com.accession.io.exception.AuthenticationException;
import com.accession.io.exception.AuthorizationException;
import com.accession.io.exception.RecordNotFoundException;
import com.accession.io.exception.reasons.AuthenticationReason;
import com.accession.io.exception.reasons.AuthorizationReason;
import com.accession.io.infrastructure.providers.CacheProvider;
import com.accession.io.message.commands.AuthorizeUserCommand;
import com.accession.io.message.responses.AuthorizeUserResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriTemplate;

import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:27:45
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorizeUserUseCase implements BaseUseCase<AuthorizeUserCommand, AuthorizeUserResponse> {

    @Value("${jwt.token.revoke-prefix}")
    private String revokeList;

    private final UserService userService;
    private final RealmService realmService;
    private final RoleService roleService;

    private final JwtProvider jwtProvider;
    private final CacheProvider cacheProvider;

    @Override
    public AuthorizeUserResponse execute(AuthorizeUserCommand command) {
        try {
            Claims claims = jwtProvider.extract(command.accessToken());

            Boolean isRevoked = cacheProvider.exists(revokeList + claims.getId());
            if (isRevoked)
                throw new AuthorizationException(AuthorizationReason.sessionRevoked());

            String realmId = claims.get("realm_id", String.class);
            Realm realm = realmService.findById(command.realmId());
            if (!realmId.equals(command.realmId()))
                throw new AuthorizationException(AuthorizationReason.realmMismatch());

            List<String> roleIds = claims.get("role_ids", List.class);
            List<Role> roles = roleService.findAllByIds(roleIds);
            realm.getRoles().stream()
                    .filter(role -> roleIds.contains(role.getId().getId()))
                    .findFirst()
                    .orElseThrow(() -> new AuthorizationException(AuthorizationReason.roleMismatch()));

            String userId = claims.get("user_id", String.class);
            User user = userService.findById(userId);

            roles.stream()
                    .flatMap(role -> role.getPermissions().stream())
                    .filter(p -> new UriTemplate(p.getEndpoint().getEndpoint()).matches(command.endpoint())
                            && p.getHttpMethod().getHttpMethod().equals(command.httpMethod()))
                    .peek(p -> {
                        if (p.getVerificationRequired() && !user.getIsVerified())
                            throw new AuthorizationException(AuthorizationReason.userNotVerified());
                    })
                    .findFirst()
                    .orElseThrow(() -> new AuthorizationException(AuthorizationReason.permissionMismatch()));

            return AuthorizeUserResponse.of(userId,true);
        } catch (MalformedJwtException ex) {
            throw new AuthenticationException(AuthenticationReason.expiredJwt());
        } catch (ExpiredJwtException ex) {
            throw new AuthenticationException(AuthenticationReason.malformedJwt());
        } catch (RecordNotFoundException ex) {
            throw new AuthorizationException(ex.getReason());
        }
    }
}
