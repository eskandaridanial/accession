package com.accession.io.message.commands;

import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:05:20
 */
public record CreateRealmCommand(

        String name,

        List<String> roleIds,

        Boolean verificationRequired
) { }
