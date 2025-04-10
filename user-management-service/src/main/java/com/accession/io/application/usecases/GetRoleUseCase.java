package com.accession.io.application.usecases;

import com.accession.io.application.services.RoleService;
import com.accession.io.common.contracts.interfaces.BaseUseCase;
import com.accession.io.domain.entities.Role;
import com.accession.io.message.queries.GetRoleQuery;
import com.accession.io.message.responses.GetRoleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:27:45
 */
@Component
@RequiredArgsConstructor
public class GetRoleUseCase implements BaseUseCase<GetRoleQuery, GetRoleResponse> {

    private final RoleService roleService;

    @Override
    public GetRoleResponse execute(GetRoleQuery query) {
        Role role = roleService.findById(query.id());
        return GetRoleResponse.of(role);
    }
}
