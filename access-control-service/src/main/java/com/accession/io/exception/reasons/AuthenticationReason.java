package com.accession.io.exception.reasons;

import com.accession.io.common.properties.ReasonProperties;
import com.accession.io.message.models.ReasonModel;
import lombok.Getter;

/**
 * @author: Danial Eskandari
 * @createdAt: 2025-01-08 11:39:53
 */
@Getter
public class AuthenticationReason {

    public static ReasonModel accessTokenIsEmpty() {
        return ReasonProperties.of("accessTokenIsEmpty");
    }

    public static ReasonModel realmIdIsEmpty() {
        return ReasonProperties.of("realmIdIsEmpty");
    }
}
