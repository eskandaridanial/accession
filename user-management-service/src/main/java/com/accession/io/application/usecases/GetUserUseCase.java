package com.accession.io.application.usecases;

import com.accession.io.application.services.UserService;
import com.accession.io.common.contracts.interfaces.BaseUseCase;
import com.accession.io.domain.entities.User;
import com.accession.io.message.queries.GetUserQuery;
import com.accession.io.message.responses.GetUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:27:45
 */
@Component
@RequiredArgsConstructor
public class GetUserUseCase implements BaseUseCase<GetUserQuery, GetUserResponse> {

    private final UserService userService;

    @Override
    public GetUserResponse execute(GetUserQuery query) {
        User user = userService.findById(query.id());
        return GetUserResponse.of(user);
    }
}
