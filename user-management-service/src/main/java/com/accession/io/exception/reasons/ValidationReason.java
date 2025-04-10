package com.accession.io.exception.reasons;

import com.accession.io.common.properties.ReasonProperties;
import com.accession.io.message.models.ReasonModel;
import lombok.Getter;

/**
 * @author: Danial Eskandari
 * @createdAt: 2025-01-08 11:39:53
 */
@Getter
public class ValidationReason {

    public static ReasonModel invalidHttpMethod() {
        return ReasonProperties.of("invalidHttpMethod");
    }

    public static ReasonModel invalidEndpoint() {
        return ReasonProperties.of("invalidEndpoint");
    }

    public static ReasonModel invalidEmail() {
        return ReasonProperties.of("invalidEmail");
    }

    public static ReasonModel invalidPassword() {
        return ReasonProperties.of("invalidPassword");
    }

    public static ReasonModel invalidName() {
        return ReasonProperties.of("invalidName");
    }
}
