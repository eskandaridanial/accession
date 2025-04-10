package com.accession.io.transformers.permission;

import com.accession.io.message.commands.CreatePermissionCommand;
import io.cucumber.java.DataTableType;

import java.util.Map;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-12-30 19:00:02
 */
public class CreatePermissionCommandTransformer {

    @DataTableType
    public CreatePermissionCommand transform(Map<String, String> entry) {
        return new CreatePermissionCommand(
                entry.get("name"),
                entry.get("endpoint"),
                entry.get("httpMethod"),
                Boolean.parseBoolean(entry.get("verificationRequired"))
        );
    }
}
