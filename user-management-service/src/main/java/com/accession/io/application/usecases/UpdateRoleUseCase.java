package com.accession.io.application.usecases;

import com.accession.io.application.services.PermissionService;
import com.accession.io.application.services.RoleService;
import com.accession.io.common.contracts.interfaces.BaseUseCase;
import com.accession.io.domain.entities.Role;
import com.accession.io.domain.values.Name;
import com.accession.io.message.commands.UpdateRoleCommand;
import com.accession.io.message.responses.UpdateRoleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:27:45
 */
@Component
@RequiredArgsConstructor
public class UpdateRoleUseCase implements BaseUseCase<UpdateRoleCommand, UpdateRoleResponse> {

    private final PermissionService permissionService;
    private final RoleService roleService;

    @Override
    public UpdateRoleResponse execute(UpdateRoleCommand command) {
        Role role = roleService.findById(command.id());

        var finalRole = role;
        command.name().ifPresent(name -> finalRole.setName(Name.of(name)));
        command.permissionIds().ifPresent(permissionIds -> finalRole.setPermissions(permissionService.findAllByIds(permissionIds)));

        role = roleService.save(finalRole);

        return UpdateRoleResponse.of(role);
    }
}
