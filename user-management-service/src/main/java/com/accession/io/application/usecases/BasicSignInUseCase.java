package com.accession.io.application.usecases;

import com.accession.io.application.providers.JwtProvider;
import com.accession.io.application.services.CredentialService;
import com.accession.io.application.services.UserService;
import com.accession.io.common.contracts.interfaces.BaseUseCase;
import com.accession.io.domain.entities.BasicCredential;
import com.accession.io.domain.entities.User;
import com.accession.io.domain.values.SessionId;
import com.accession.io.exception.AuthenticationException;
import com.accession.io.exception.RecordNotFoundException;
import com.accession.io.exception.ValidationException;
import com.accession.io.exception.reasons.AuthenticationReason;
import com.accession.io.message.commands.BasicSignInCommand;
import com.accession.io.message.models.JwtModel;
import com.accession.io.message.responses.BasicSignInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:27:45
 */
@Component
@RequiredArgsConstructor
public class BasicSignInUseCase implements BaseUseCase<BasicSignInCommand, BasicSignInResponse> {

    private final UserService userService;
    private final CredentialService credentialService;

    private final JwtProvider jwtProvider;

    @Override
    public BasicSignInResponse execute(BasicSignInCommand command) {
        try {
            User user = userService.findByEmail(command.email());

            BasicCredential credential = credentialService.findByType(user.getCredentials(), BasicCredential.class);
            credential.setSessionId(SessionId.of());

            if (!credential.getPassword().matches(command.password()))
                throw new AuthenticationException(AuthenticationReason.incorrectCredentials());

            String accessToken = jwtProvider.issue(credential, user.claims());
            JwtModel jwtModel = jwtProvider.convert(accessToken);

            userService.save(user);

            return BasicSignInResponse.of(jwtModel);
        } catch (RecordNotFoundException ex) {
            throw new AuthenticationException(ex.getReason());
        } catch (ValidationException ex) {
            throw new ValidationException(ex.getReason());
        }
    }
}
