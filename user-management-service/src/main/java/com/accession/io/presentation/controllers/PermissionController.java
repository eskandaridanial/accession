package com.accession.io.presentation.controllers;

import com.accession.io.application.usecases.CreatePermissionUseCase;
import com.accession.io.application.usecases.GetAllPermissionsUseCase;
import com.accession.io.application.usecases.GetPermissionUseCase;
import com.accession.io.application.usecases.UpdatePermissionUseCase;
import com.accession.io.common.contracts.records.BaseResponse;
import com.accession.io.message.commands.CreatePermissionCommand;
import com.accession.io.message.commands.UpdatePermissionCommand;
import com.accession.io.message.queries.GetAllPermissionsQuery;
import com.accession.io.message.queries.GetPermissionQuery;
import com.accession.io.message.responses.CreatePermissionResponse;
import com.accession.io.message.responses.GetAllPermissionsResponse;
import com.accession.io.message.responses.GetPermissionResponse;
import com.accession.io.message.responses.UpdatePermissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 14:20:01
 */
@RestController
@RequestMapping("${server.path}/permission")
@RequiredArgsConstructor
public class PermissionController {

    private final CreatePermissionUseCase createPermissionUseCase;
    private final UpdatePermissionUseCase updatePermissionUseCase;
    private final GetPermissionUseCase getPermissionUseCase;
    private final GetAllPermissionsUseCase getAllPermissionsUseCase;

    @PostMapping
    public ResponseEntity<BaseResponse<CreatePermissionResponse>> createPermission(@RequestBody CreatePermissionCommand command) {
        return BaseResponse.of(createPermissionUseCase.execute(command));
    }

    @PatchMapping
    public ResponseEntity<BaseResponse<UpdatePermissionResponse>> updatePermission(@RequestBody UpdatePermissionCommand command) {
        return BaseResponse.of(updatePermissionUseCase.execute(command));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<GetPermissionResponse>> getPermission(@PathVariable String id) {
        return BaseResponse.of(getPermissionUseCase.execute(new GetPermissionQuery(id)));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<GetAllPermissionsResponse>> getAllPermissions() {
        return BaseResponse.of(getAllPermissionsUseCase.execute(new GetAllPermissionsQuery()));
    }
}
