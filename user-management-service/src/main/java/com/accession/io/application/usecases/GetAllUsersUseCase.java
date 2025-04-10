package com.accession.io.application.usecases;

import com.accession.io.application.services.UserService;
import com.accession.io.common.contracts.interfaces.BaseUseCase;
import com.accession.io.domain.entities.User;
import com.accession.io.message.queries.GetAllUsersQuery;
import com.accession.io.message.responses.GetAllUsersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:27:45
 */
@Component
@RequiredArgsConstructor
public class GetAllUsersUseCase implements BaseUseCase<GetAllUsersQuery, GetAllUsersResponse> {

    private final UserService userService;

    @Override
    public GetAllUsersResponse execute(GetAllUsersQuery query) {
        List<User> users = userService.findAll();
        return GetAllUsersResponse.of(users);
    }
}
