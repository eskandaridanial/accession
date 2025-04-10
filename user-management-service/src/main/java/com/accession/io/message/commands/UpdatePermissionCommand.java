package com.accession.io.message.commands;

import java.util.Optional;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:05:20
 */
public record UpdatePermissionCommand(

        String id,

        Optional<String> name,

        Optional<String> endpoint,

        Optional<String> httpMethod,

        Optional<Boolean> verificationRequired
) { }
