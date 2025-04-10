package com.accession.io.domain.values;

import com.accession.io.exception.ValidationException;
import com.accession.io.exception.reasons.ValidationReason;
import lombok.NoArgsConstructor;
import lombok.Value;

import static com.accession.io.domain.formats.AttributeRegexFormat.HTTP_METHOD_PATTERN;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-03 17:31:10
 */
@Value
@NoArgsConstructor(force = true)
public class HttpMethod {

    String httpMethod;

    private HttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public static HttpMethod of(String httpMethod) {
        validate(httpMethod);
        return new HttpMethod(httpMethod);
    }

    private static void validate(String httpMethod) {
        if (!HTTP_METHOD_PATTERN.matcher(httpMethod).matches())
            throw new ValidationException(ValidationReason.invalidHttpMethod());
    }
}
