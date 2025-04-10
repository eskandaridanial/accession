package com.accession.io.message.responses;

import com.accession.io.domain.values.Timestamps;
import com.accession.io.domain.entities.Permission;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:06:05
 */
public record CreatePermissionResponse (

        String id,

        String name,

        String endpoint,

        String httpMethod,

        Boolean verificationRequired,

        Timestamps timestamps
) {
    public static CreatePermissionResponse of(Permission permission) {
        return new CreatePermissionResponse(
                permission.getId().getId(),
                permission.getName().getName(),
                permission.getEndpoint().getEndpoint(),
                permission.getHttpMethod().getHttpMethod(),
                permission.getVerificationRequired(),
                permission.getTimestamps()
        );
    }
}
