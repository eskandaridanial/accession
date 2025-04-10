package com.accession.io.message.responses;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:06:05
 */
public record AuthorizeUserResponse(

        String userId,

        Boolean success
) {
    public static AuthorizeUserResponse of(String userId, Boolean success) {
        return new AuthorizeUserResponse(userId, success);
    }
}
