package com.accession.io.exception.reasons;

import com.accession.io.common.properties.ReasonProperties;
import com.accession.io.message.models.ReasonModel;
import lombok.Getter;

/**
 * @author: Danial Eskandari
 * @createdAt: 2025-01-08 11:39:53
 */
@Getter
public class AlreadyExistsReason {

    public static ReasonModel permissionAlreadyExists() {
        return ReasonProperties.of("permissionAlreadyExists");
    }

    public static ReasonModel realmAlreadyExists() {
        return ReasonProperties.of("realmAlreadyExists");
    }

    public static ReasonModel roleAlreadyExists() {
        return ReasonProperties.of("roleAlreadyExists");
    }

    public static ReasonModel userAlreadyExists() {
        return ReasonProperties.of("userAlreadyExists");
    }
}
