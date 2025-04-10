package com.accession.io.presentation.controllers;

import com.accession.io.application.usecases.CreateRealmUseCase;
import com.accession.io.application.usecases.GetAllRealmsUseCase;
import com.accession.io.application.usecases.GetRealmUseCase;
import com.accession.io.application.usecases.UpdateRealmUseCase;
import com.accession.io.common.contracts.records.BaseResponse;
import com.accession.io.message.commands.CreateRealmCommand;
import com.accession.io.message.commands.UpdateRealmCommand;
import com.accession.io.message.queries.GetAllRealmsQuery;
import com.accession.io.message.queries.GetRealmQuery;
import com.accession.io.message.responses.CreateRealmResponse;
import com.accession.io.message.responses.GetAllRealmsResponse;
import com.accession.io.message.responses.GetRealmResponse;
import com.accession.io.message.responses.UpdateRealmResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 14:20:01
 */
@RestController
@RequestMapping("${server.path}/realm")
@RequiredArgsConstructor
public class RealmController {

    private final CreateRealmUseCase createRealmUseCase;
    private final UpdateRealmUseCase updateRealmUseCase;
    private final GetRealmUseCase getRealmUseCase;
    private final GetAllRealmsUseCase getAllRealmsUseCase;

    @PostMapping
    public ResponseEntity<BaseResponse<CreateRealmResponse>> createRealm(@RequestBody CreateRealmCommand command) {
        return BaseResponse.of(createRealmUseCase.execute(command));
    }

    @PatchMapping
    public ResponseEntity<BaseResponse<UpdateRealmResponse>> updateRealm(@RequestBody UpdateRealmCommand command) {
        return BaseResponse.of(updateRealmUseCase.execute(command));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<GetRealmResponse>> getRealm(@PathVariable String id) {
        return BaseResponse.of(getRealmUseCase.execute(new GetRealmQuery(id)));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<GetAllRealmsResponse>> getAllRealms() {
        return BaseResponse.of(getAllRealmsUseCase.execute(new GetAllRealmsQuery()));
    }
}
