package com.accession.io.message.responses;

import com.accession.io.domain.values.Timestamps;
import com.accession.io.domain.entities.User;

import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:06:05
 */
public record GetUserResponse(

        String id,

        String email,

        GetRealmResponse realm,

        List<GetRoleResponse> roleIds,

        Timestamps timestamps
) {
    public static GetUserResponse of(User user) {
        return new GetUserResponse(
                user.getId().getId(),
                user.getEmail().getEmail(),
                GetRealmResponse.of(user.getRealm()),
                GetAllRolesResponse.of(user.getRoles()).roles(),
                user.getTimestamps()
        );
    }
}
