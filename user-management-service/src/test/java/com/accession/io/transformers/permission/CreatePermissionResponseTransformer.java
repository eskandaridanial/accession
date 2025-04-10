package com.accession.io.transformers.permission;

import com.accession.io.message.responses.CreatePermissionResponse;
import io.cucumber.java.DataTableType;

import java.util.Map;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-12-30 19:00:02
 */
public class CreatePermissionResponseTransformer {

    @DataTableType
    public CreatePermissionResponse transform(Map<String, String> entry) {
        return new CreatePermissionResponse(
                null,
                entry.get("name"),
                entry.get("endpoint"),
                entry.get("httpMethod"),
                Boolean.parseBoolean(entry.get("verificationRequired")),
                null
        );
    }
}
