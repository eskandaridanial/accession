package com.accession.io.message.responses;

import org.springframework.http.HttpStatus;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:06:05
 */
public record AuthorizeUserResponse(

        String userId,

        Boolean success,

        HttpStatus status
) {
    public static AuthorizeUserResponse of(String userId, Boolean success, HttpStatus status) {
        return new AuthorizeUserResponse(userId, success, status);
    }
}
