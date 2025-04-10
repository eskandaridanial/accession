package com.accession.io.message.responses;

import com.accession.io.domain.values.Timestamps;
import com.accession.io.domain.entities.BasicCredential;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:06:05
 */
public record BasicSignUpResponse(

        String id,

        String subject,

        Timestamps timestamps
) {
    public static BasicSignUpResponse of(BasicCredential credential) {
        return new BasicSignUpResponse(
                credential.getId().getId(),
                credential.getSubject().getSubject(),
                credential.getTimestamps()
        );
    }
}
