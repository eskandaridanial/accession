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

    public static ReasonModel expiredJwt() {
        return ReasonProperties.of("expiredJwt");
    }

    public static ReasonModel malformedJwt() {
        return ReasonProperties.of("malformedJwt");
    }

    public static ReasonModel incorrectCredentials() {
        return ReasonProperties.of("incorrectCredentials");
    }
}
