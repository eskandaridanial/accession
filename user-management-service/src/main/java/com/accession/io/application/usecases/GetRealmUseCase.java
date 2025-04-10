package com.accession.io.application.usecases;

import com.accession.io.application.services.RealmService;
import com.accession.io.common.contracts.interfaces.BaseUseCase;
import com.accession.io.domain.entities.Realm;
import com.accession.io.message.queries.GetRealmQuery;
import com.accession.io.message.responses.GetRealmResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:27:45
 */
@Component
@RequiredArgsConstructor
public class GetRealmUseCase implements BaseUseCase<GetRealmQuery, GetRealmResponse> {

    private final RealmService realmService;

    @Override
    public GetRealmResponse execute(GetRealmQuery query) {
        Realm realm = realmService.findById(query.id());
        return GetRealmResponse.of(realm);
    }
}
