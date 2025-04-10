package com.accession.io.message.responses;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:06:05
 */
public record DeAuthorizeUserResponse(

        Boolean success
) {
    public static DeAuthorizeUserResponse of(Boolean success) {
        return new DeAuthorizeUserResponse(success);
    }
}
