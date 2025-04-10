package com.accession.io.message.responses;

import com.accession.io.domain.entities.Realm;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:06:05
 */
public record GetAllRealmsResponse(

        List<GetRealmResponse> realms
) {
    public static GetAllRealmsResponse of(List<Realm> realms) {
        return new GetAllRealmsResponse(realms.stream()
                .map(GetRealmResponse::of)
                .collect(Collectors.toList()));
    }
}
