package com.accession.io.message.responses;

import com.accession.io.domain.values.Timestamps;
import com.accession.io.domain.entities.Realm;

import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:06:05
 */
public record GetRealmResponse(

        String id,

        String name,

        List<GetRoleResponse> roles,

        Timestamps timestamps
) {
    public static GetRealmResponse of(Realm realm) {
        return new GetRealmResponse(
                realm.getId().getId(),
                realm.getName().getName(),
                GetAllRolesResponse.of(realm.getRoles()).roles(),
                realm.getTimestamps()
        );
    }
}
