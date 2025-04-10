package com.accession.io.message.models;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
* @author: Danial Eskandari
* @createdAt: 2024-11-11 13:46:04
*/
public record JwtModel(

        String sessionId,

        String subject,

        LocalDateTime issuedAt,

        LocalDateTime expiresAt,

        String accessToken
) {
    public JwtModel(String sessionId, String subject, Date issuedAt, Date expiresAt, String accessToken) {
        this(
                sessionId,
                subject,
                issuedAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                expiresAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                accessToken
        );
    }
}