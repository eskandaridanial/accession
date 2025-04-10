package com.accession.io.presentation.controllers;

import com.accession.io.application.usecases.BasicSignInUseCase;
import com.accession.io.application.usecases.BasicSignUpUseCase;
import com.accession.io.common.contracts.records.BaseResponse;
import com.accession.io.message.commands.BasicSignInCommand;
import com.accession.io.message.commands.BasicSignUpCommand;
import com.accession.io.message.responses.BasicSignInResponse;
import com.accession.io.message.responses.BasicSignUpResponse;
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
@RequestMapping("${server.path}/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {

    private final BasicSignUpUseCase basicSignUpUseCase;
    private final BasicSignInUseCase basicSignInUseCase;

    @PostMapping("/basic/sign-up")
    public ResponseEntity<BaseResponse<BasicSignUpResponse>> basicSignUp(@RequestBody BasicSignUpCommand command) {
        return BaseResponse.of(basicSignUpUseCase.execute(command));
    }

    @PostMapping("/basic/sign-in")
    public ResponseEntity<BaseResponse<BasicSignInResponse>> basicSignIn(@RequestBody BasicSignInCommand command) {
        return BaseResponse.of(basicSignInUseCase.execute(command));
    }
}
