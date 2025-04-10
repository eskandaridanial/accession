package com.accession.io.message.commands;

import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-10 17:08:25
 */
public record BasicSignUpCommand(

        String email,

        String password,

        String realmId,

        List<String> roleIds
) { }
