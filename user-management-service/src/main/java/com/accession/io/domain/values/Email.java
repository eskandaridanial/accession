package com.accession.io.domain.values;

import com.accession.io.exception.ValidationException;
import com.accession.io.exception.reasons.ValidationReason;
import lombok.NoArgsConstructor;
import lombok.Value;

import static com.accession.io.domain.formats.AttributeRegexFormat.EMAIL_PATTERN;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-03 17:31:10
 */
@Value
@NoArgsConstructor(force = true)
public class Email {

    String email;

    private Email(String email) {
        this.email = email;
    }

    public static Email of(String email) {
        validate(email);
        return new Email(email);
    }

    private static void validate(String email) {
        if (!EMAIL_PATTERN.matcher(email).matches())
            throw new ValidationException(ValidationReason.invalidEmail());
    }
}
