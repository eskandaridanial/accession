package com.accession.io.presentation.controllers;

import com.accession.io.application.usecases.GetAllUsersUseCase;
import com.accession.io.application.usecases.GetUserUseCase;
import com.accession.io.common.contracts.records.BaseResponse;
import com.accession.io.message.queries.GetAllUsersQuery;
import com.accession.io.message.queries.GetUserQuery;
import com.accession.io.message.responses.GetAllUsersResponse;
import com.accession.io.message.responses.GetUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 14:20:01
 */
@RestController
@RequestMapping("${server.path}/user")
@RequiredArgsConstructor
public class UserController {

    private final GetUserUseCase getUserUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<GetUserResponse>> getUser(@PathVariable String id) {
        return BaseResponse.of(getUserUseCase.execute(new GetUserQuery(id)));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<GetAllUsersResponse>> getAllUsers() {
        return BaseResponse.of(getAllUsersUseCase.execute(new GetAllUsersQuery()));
    }
}
