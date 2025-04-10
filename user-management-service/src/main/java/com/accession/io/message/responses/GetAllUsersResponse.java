package com.accession.io.message.responses;

import com.accession.io.domain.entities.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:06:05
 */
public record GetAllUsersResponse(

        List<GetUserResponse> users
) {
    public static GetAllUsersResponse of(List<User> users) {
        return new GetAllUsersResponse(users.stream()
                .map(GetUserResponse::of)
                .collect(Collectors.toList()));
    }
}
