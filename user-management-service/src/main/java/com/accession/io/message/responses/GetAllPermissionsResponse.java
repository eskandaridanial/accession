package com.accession.io.message.responses;

import com.accession.io.domain.entities.Permission;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:06:05
 */
public record GetAllPermissionsResponse(

        List<GetPermissionResponse> permissions
) {
    public static GetAllPermissionsResponse of(List<Permission> permissions) {
        return new GetAllPermissionsResponse(permissions.stream()
                .map(GetPermissionResponse::of)
                .collect(Collectors.toList()));
    }
}
