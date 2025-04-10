package com.accession.io.application.usecases;

import com.accession.io.application.services.RealmService;
import com.accession.io.common.contracts.interfaces.BaseUseCase;
import com.accession.io.domain.entities.Realm;
import com.accession.io.message.queries.GetAllRealmsQuery;
import com.accession.io.message.responses.GetAllRealmsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:27:45
 */
@Component
@RequiredArgsConstructor
public class GetAllRealmsUseCase implements BaseUseCase<GetAllRealmsQuery, GetAllRealmsResponse> {

    private final RealmService realmService;

    @Override
    public GetAllRealmsResponse execute(GetAllRealmsQuery query) {
        List<Realm> realms = realmService.findAll();
        return GetAllRealmsResponse.of(realms);
    }
}
