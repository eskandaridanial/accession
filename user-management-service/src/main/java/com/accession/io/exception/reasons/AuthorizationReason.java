package com.accession.io.exception.reasons;

import com.accession.io.common.properties.ReasonProperties;
import com.accession.io.message.models.ReasonModel;
import lombok.Getter;

/**
 * @author: Danial Eskandari
 * @createdAt: 2025-01-08 11:39:53
 */
@Getter
public class AuthorizationReason {

    public static ReasonModel sessionRevoked() {
        return ReasonProperties.of("sessionRevoked");
    }

    public static ReasonModel userNotVerified() {
        return ReasonProperties.of("userNotVerified");
    }

    public static ReasonModel realmMismatch() {
        return ReasonProperties.of("realmMismatch");
    }

    public static ReasonModel roleMismatch() {
        return ReasonProperties.of("roleMismatch");
    }

    public static ReasonModel permissionMismatch() {
        return ReasonProperties.of("permissionMismatch");
    }

    public static ReasonModel userModelIsEmpty() {
        return ReasonProperties.of("userModelIsEmpty");
    }
}
