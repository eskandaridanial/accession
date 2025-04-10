package com.accession.io.message.commands;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-10 17:08:25
 */
public record DeAuthorizeUserCommand(

        String accessToken
) {
    public static DeAuthorizeUserCommand of(String accessToken) {
        return new DeAuthorizeUserCommand(accessToken);
    }
}
