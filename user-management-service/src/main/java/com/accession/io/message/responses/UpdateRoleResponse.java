package com.accession.io.message.responses;

import com.accession.io.domain.values.Timestamps;
import com.accession.io.domain.entities.Role;

import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:06:05
 */
public record UpdateRoleResponse(

        String id,

        String name,

        List<GetPermissionResponse> permissions,

        Timestamps timestamps
) {
    public static UpdateRoleResponse of(Role role) {
        return new UpdateRoleResponse(
                role.getId().getId(),
                role.getName().getName(),
                GetAllPermissionsResponse.of(role.getPermissions()).permissions(),
                role.getTimestamps());
    }
}
