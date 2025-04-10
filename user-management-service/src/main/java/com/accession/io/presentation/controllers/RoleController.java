package com.accession.io.presentation.controllers;

import com.accession.io.application.usecases.CreateRoleUseCase;
import com.accession.io.application.usecases.GetAllRolesUseCase;
import com.accession.io.application.usecases.GetRoleUseCase;
import com.accession.io.application.usecases.UpdateRoleUseCase;
import com.accession.io.common.contracts.records.BaseResponse;
import com.accession.io.message.commands.CreateRoleCommand;
import com.accession.io.message.commands.UpdateRoleCommand;
import com.accession.io.message.queries.GetAllRolesQuery;
import com.accession.io.message.queries.GetRoleQuery;
import com.accession.io.message.responses.CreateRoleResponse;
import com.accession.io.message.responses.GetAllRolesResponse;
import com.accession.io.message.responses.GetRoleResponse;
import com.accession.io.message.responses.UpdateRoleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 14:20:01
 */
@RestController
@RequestMapping("${server.path}/role")
@RequiredArgsConstructor
public class RoleController {

    private final CreateRoleUseCase createRoleUseCase;
    private final UpdateRoleUseCase updateRoleUseCase;
    private final GetAllRolesUseCase getAllRolesUseCase;
    private final GetRoleUseCase getRoleUseCase;

    @PostMapping
    public ResponseEntity<BaseResponse<CreateRoleResponse>> createRole(@RequestBody CreateRoleCommand command) {
        return BaseResponse.of(createRoleUseCase.execute(command));
    }

    @PatchMapping
    public ResponseEntity<BaseResponse<UpdateRoleResponse>> updateRole(@RequestBody UpdateRoleCommand command) {
        return BaseResponse.of(updateRoleUseCase.execute(command));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<GetRoleResponse>> getRole(@PathVariable String id) {
        return BaseResponse.of(getRoleUseCase.execute(new GetRoleQuery(id)));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<GetAllRolesResponse>> getAllRoles() {
        return BaseResponse.of(getAllRolesUseCase.execute(new GetAllRolesQuery()));
    }
}
