package com.accession.io.application.usecases;

import com.accession.io.application.services.PermissionService;
import com.accession.io.application.services.RoleService;
import com.accession.io.common.contracts.interfaces.BaseUseCase;
import com.accession.io.domain.entities.Permission;
import com.accession.io.domain.entities.Role;
import com.accession.io.domain.values.Name;
import com.accession.io.message.commands.CreateRoleCommand;
import com.accession.io.message.responses.CreateRoleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:27:45
 */
@Component
@RequiredArgsConstructor
public class CreateRoleUseCase implements BaseUseCase<CreateRoleCommand, CreateRoleResponse> {

    private final PermissionService permissionService;
    private final RoleService roleService;

    @Override
    public CreateRoleResponse execute(CreateRoleCommand command) {
        Role role = new Role();
        role.setName(Name.of(command.name()));
        List<Permission> permissions = permissionService.findAllByIds(command.permissionIds());
        role.setPermissions(permissions);
        role = roleService.save(role);
        return CreateRoleResponse.of(role);
    }
}
