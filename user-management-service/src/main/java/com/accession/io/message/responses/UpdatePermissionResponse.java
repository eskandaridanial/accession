package com.accession.io.message.responses;

import com.accession.io.domain.values.Timestamps;
import com.accession.io.domain.entities.Permission;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:06:05
 */
public record UpdatePermissionResponse(

        String id,

        String name,

        String endpoint,

        String httpMethod,

        Boolean verificationRequired,

        Timestamps timestamps
) {
    public static UpdatePermissionResponse of(Permission permission) {
        return new UpdatePermissionResponse(
                permission.getId().getId(),
                permission.getName().getName(),
                permission.getEndpoint().getEndpoint(),
                permission.getHttpMethod().getHttpMethod(),
                permission.getVerificationRequired(),
                permission.getTimestamps()
        );
    }
}
