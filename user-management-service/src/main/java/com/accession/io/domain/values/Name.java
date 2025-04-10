package com.accession.io.domain.values;

import com.accession.io.exception.ValidationException;
import com.accession.io.exception.reasons.ValidationReason;
import lombok.NoArgsConstructor;
import lombok.Value;

import static com.accession.io.domain.formats.AttributeRegexFormat.NAME_PATTERN;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-03 17:31:10
 */
@Value
@NoArgsConstructor(force = true)
public class Name {

    String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name of(String name) {
        validate(name);
        return new Name(name);
    }

    private static void validate(String name) {
        if (!NAME_PATTERN.matcher(name).matches())
            throw new ValidationException(ValidationReason.invalidName());
    }
}
