package com.accession.io.message.responses;

import com.accession.io.domain.entities.Role;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:06:05
 */
public record GetAllRolesResponse(

        List<GetRoleResponse> roles
) {
    public static GetAllRolesResponse of(List<Role> roles) {
        return new GetAllRolesResponse(roles.stream()
                .map(GetRoleResponse::of)
                .collect(Collectors.toList()));
    }
}
