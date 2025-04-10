package com.accession.io.message.responses;

import com.accession.io.message.models.JwtModel;

import java.time.LocalDateTime;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:06:05
 */
public record BasicSignInResponse(

        String sessionId,

        String subject,

        LocalDateTime issuedAt,

        LocalDateTime expiresAt,

        String accessToken
) {
    public static BasicSignInResponse of(JwtModel jwtModel) {
        return new BasicSignInResponse(
                jwtModel.sessionId(),
                jwtModel.subject(),
                jwtModel.issuedAt(),
                jwtModel.expiresAt(),
                jwtModel.accessToken()
        );
    }
}
