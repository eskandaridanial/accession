package com.accession.io.application.usecases;

import com.accession.io.application.services.PermissionService;
import com.accession.io.common.contracts.interfaces.BaseUseCase;
import com.accession.io.domain.entities.Permission;
import com.accession.io.domain.values.Endpoint;
import com.accession.io.domain.values.HttpMethod;
import com.accession.io.domain.values.Name;
import com.accession.io.message.commands.CreatePermissionCommand;
import com.accession.io.message.responses.CreatePermissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:27:45
 */
@Component
@RequiredArgsConstructor
public class CreatePermissionUseCase implements BaseUseCase<CreatePermissionCommand, CreatePermissionResponse> {

    private final PermissionService permissionService;

    @Override
    public CreatePermissionResponse execute(CreatePermissionCommand command) {
        Permission permission = new Permission();
        permission.setName(Name.of(command.name()));
        permission.setEndpoint(Endpoint.of(command.endpoint()));
        permission.setHttpMethod(HttpMethod.of(command.httpMethod()));
        permission.setVerificationRequired(command.verificationRequired());
        permission = permissionService.save(permission);
        return CreatePermissionResponse.of(permission);
    }
}
