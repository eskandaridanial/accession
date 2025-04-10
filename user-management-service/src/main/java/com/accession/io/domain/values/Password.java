package com.accession.io.domain.values;

import com.accession.io.exception.ValidationException;
import com.accession.io.exception.reasons.ValidationReason;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.mindrot.jbcrypt.BCrypt;

import static com.accession.io.domain.formats.AttributeRegexFormat.PASSWORD_PATTERN;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-03 17:31:10
 */
@Value
@NoArgsConstructor(force = true)
public class Password {

    String hashedPassword;

    private Password(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public static Password of(String password) {
        validate(password);
        return new Password(hash(password));
    }

    public boolean matches(String password) {
        return BCrypt.checkpw(password, this.hashedPassword);
    }

    private static void validate(String password) {
        if (!PASSWORD_PATTERN.matcher(password).matches())
            throw new ValidationException(ValidationReason.invalidPassword());
    }

    private static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
