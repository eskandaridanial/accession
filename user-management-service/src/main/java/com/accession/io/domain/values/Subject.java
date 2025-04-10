package com.accession.io.domain.values;

import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-04 14:57:31
 */
@Value
@NoArgsConstructor(force = true)
public class Subject {

    String subject;

    private Subject(String subject) {
        this.subject = subject;
    }

    public static Subject of(String subject) {
        return new Subject(subject);
    }
}
