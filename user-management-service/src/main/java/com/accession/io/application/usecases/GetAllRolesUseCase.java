package com.accession.io.application.usecases;

import com.accession.io.application.services.RoleService;
import com.accession.io.common.contracts.interfaces.BaseUseCase;
import com.accession.io.domain.entities.Role;
import com.accession.io.message.queries.GetAllRolesQuery;
import com.accession.io.message.responses.GetAllRolesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:27:45
 */
@Component
@RequiredArgsConstructor
public class GetAllRolesUseCase implements BaseUseCase<GetAllRolesQuery, GetAllRolesResponse> {

    private final RoleService roleService;

    @Override
    public GetAllRolesResponse execute(GetAllRolesQuery query) {
        List<Role> roles = roleService.findAll();
        return GetAllRolesResponse.of(roles);
    }
}
