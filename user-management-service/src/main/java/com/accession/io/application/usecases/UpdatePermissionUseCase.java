package com.accession.io.application.usecases;

import com.accession.io.application.services.PermissionService;
import com.accession.io.common.contracts.interfaces.BaseUseCase;
import com.accession.io.domain.entities.Permission;
import com.accession.io.domain.values.Endpoint;
import com.accession.io.domain.values.HttpMethod;
import com.accession.io.domain.values.Name;
import com.accession.io.message.commands.UpdatePermissionCommand;
import com.accession.io.message.responses.UpdatePermissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:27:45
 */
@Component
@RequiredArgsConstructor
public class UpdatePermissionUseCase implements BaseUseCase<UpdatePermissionCommand, UpdatePermissionResponse> {

    private final PermissionService permissionService;

    @Override
    public UpdatePermissionResponse execute(UpdatePermissionCommand command) {
        Permission permission = permissionService.findById(command.id());

        var finalPermission = permission;
        command.name().ifPresent(name -> finalPermission.setName(Name.of(name)));
        command.endpoint().ifPresent(endpoint -> finalPermission.setEndpoint(Endpoint.of(endpoint)));
        command.httpMethod().ifPresent(method -> finalPermission.setHttpMethod(HttpMethod.of(method)));
        command.verificationRequired().ifPresent(finalPermission::setVerificationRequired);

        permission = permissionService.save(finalPermission);

        return UpdatePermissionResponse.of(permission);
    }
}
