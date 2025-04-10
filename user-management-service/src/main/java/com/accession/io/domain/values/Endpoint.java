package com.accession.io.domain.values;

import com.accession.io.exception.ValidationException;
import com.accession.io.exception.reasons.ValidationReason;
import lombok.NoArgsConstructor;
import lombok.Value;

import static com.accession.io.domain.formats.AttributeRegexFormat.ENDPOINT_PATTERN;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-03 17:31:10
 */
@Value
@NoArgsConstructor(force = true)
public class Endpoint {

    String endpoint;

    private Endpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public static Endpoint of(String endpoint) {
        validate(endpoint);
        return new Endpoint(endpoint);
    }

    private static void validate(String endpoint) {
        if (!ENDPOINT_PATTERN.matcher(endpoint).matches())
            throw new ValidationException(ValidationReason.invalidEndpoint());
    }
}
