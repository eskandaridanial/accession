package com.accession.io.message.commands;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-10 17:08:25
 */
public record AuthorizeUserCommand(

        String realmId,

        String endpoint,

        String httpMethod,

        String accessToken
) {
    public static AuthorizeUserCommand of(String realmId, String endpoint, String httpMethod, String accessToken) {
        return new AuthorizeUserCommand(realmId, endpoint, httpMethod, accessToken);
    }
}
