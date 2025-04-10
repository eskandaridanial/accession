package com.accession.io.presentation.controllers;

import com.accession.io.application.usecases.AuthorizeUserUseCase;
import com.accession.io.application.usecases.DeAuthorizeUserUseCase;
import com.accession.io.common.contracts.records.BaseResponse;
import com.accession.io.message.commands.AuthorizeUserCommand;
import com.accession.io.message.commands.DeAuthorizeUserCommand;
import com.accession.io.message.responses.AuthorizeUserResponse;
import com.accession.io.message.responses.DeAuthorizeUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 14:20:01
 */
@RestController
@RequestMapping("${server.path}")
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthorizeUserUseCase authorizeUserUseCase;
    private final DeAuthorizeUserUseCase deAuthorizeUserUseCase;

    @PostMapping("/authorize")
    public ResponseEntity<BaseResponse<AuthorizeUserResponse>> authorizeUser(@RequestBody AuthorizeUserCommand command) {
        return BaseResponse.of(authorizeUserUseCase.execute(command));
    }

    @PostMapping("/de-authorize")
    public ResponseEntity<BaseResponse<DeAuthorizeUserResponse>> deAuthorizeUser(@RequestBody DeAuthorizeUserCommand command) {
        return BaseResponse.of(deAuthorizeUserUseCase.execute(command));
    }
}
