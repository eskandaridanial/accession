package com.accession.io.exception.reasons;

import com.accession.io.common.properties.ReasonProperties;
import com.accession.io.message.models.ReasonModel;
import lombok.Getter;

/**
 * @author: Danial Eskandari
 * @createdAt: 2025-01-08 11:39:53
 */
@Getter
public class RecordNotFoundReason {

    public static ReasonModel userNotFoundById() {
        return ReasonProperties.of("userNotFoundById");
    }

    public static ReasonModel userNotFoundByEmail() {
        return ReasonProperties.of("userNotFoundByEmail");
    }

    public static ReasonModel permissionNotFoundById() {
        return ReasonProperties.of("permissionNotFoundById");
    }

    public static ReasonModel permissionNotFoundByIds() {
        return ReasonProperties.of("permissionNotFoundByIds");
    }

    public static ReasonModel roleNotFoundById() {
        return ReasonProperties.of("roleNotFoundById");
    }

    public static ReasonModel roleNotFoundByIds() {
        return ReasonProperties.of("roleNotFoundByIds");
    }

    public static ReasonModel realmNotFoundById() {
        return ReasonProperties.of("realmNotFoundById");
    }

    public static ReasonModel credentialNotFoundByType() {
        return ReasonProperties.of("credentialNotFoundByType");
    }
}
