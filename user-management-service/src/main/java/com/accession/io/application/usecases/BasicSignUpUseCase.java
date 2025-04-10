package com.accession.io.application.usecases;

import com.accession.io.application.services.CredentialService;
import com.accession.io.application.services.RealmService;
import com.accession.io.application.services.RoleService;
import com.accession.io.application.services.UserService;
import com.accession.io.common.contracts.interfaces.BaseUseCase;
import com.accession.io.domain.entities.BasicCredential;
import com.accession.io.domain.entities.Realm;
import com.accession.io.domain.entities.Role;
import com.accession.io.domain.entities.User;
import com.accession.io.domain.values.Email;
import com.accession.io.exception.ValidationException;
import com.accession.io.infrastructure.handlers.UserCreatedEventHandler;
import com.accession.io.message.commands.BasicSignUpCommand;
import com.accession.io.message.events.UserCreatedEvent;
import com.accession.io.message.responses.BasicSignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:27:45
 */
@Component
@RequiredArgsConstructor
public class BasicSignUpUseCase implements BaseUseCase<BasicSignUpCommand, BasicSignUpResponse> {

    private final UserService userService;
    private final RealmService realmService;
    private final RoleService roleService;
    private final CredentialService credentialService;

    private final UserCreatedEventHandler userCreatedEventHandler;

    @Override
    public BasicSignUpResponse execute(BasicSignUpCommand command) {
        try {
            User user = new User();
            user.setEmail(Email.of(command.email()));
            user.setIsVerified(false);

            Realm realm = realmService.findById(command.realmId());
            user.setRealm(realm);

            List<Role> roles = roleService.findAllByIds(command.roleIds());
            user.setRoles(roles);

            BasicCredential credential = credentialService.createBasicCredential(command.email(), command.password());
            user.add(credential);

            user = userService.save(user);

            UserCreatedEvent userCreatedEvent = UserCreatedEvent.of(user);
            userCreatedEventHandler.handle(userCreatedEvent);

            return BasicSignUpResponse.of(credential);
        } catch (ValidationException ex) {
            throw new ValidationException(ex.getReason());
        }
    }
}
