package com.accession.io.message.commands;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:05:20
 */
public record CreatePermissionCommand (

        String name,

        String endpoint,

        String httpMethod,

        Boolean verificationRequired
) { }
