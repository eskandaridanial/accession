package com.accession.io.message.commands;

import java.util.List;
import java.util.Optional;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:05:20
 */
public record UpdateRoleCommand(

        String id,

        Optional<String> name,

        Optional<List<String>> permissionIds
) { }
