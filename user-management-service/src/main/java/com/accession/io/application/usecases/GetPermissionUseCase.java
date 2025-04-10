package com.accession.io.application.usecases;

import com.accession.io.application.services.PermissionService;
import com.accession.io.common.contracts.interfaces.BaseUseCase;
import com.accession.io.domain.entities.Permission;
import com.accession.io.message.queries.GetPermissionQuery;
import com.accession.io.message.responses.GetPermissionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:27:45
 */
@Component
@RequiredArgsConstructor
public class GetPermissionUseCase implements BaseUseCase<GetPermissionQuery, GetPermissionResponse> {

    private final PermissionService permissionService;

    @Override
    public GetPermissionResponse execute(GetPermissionQuery query) {
        Permission permission = permissionService.findById(query.id());
        return GetPermissionResponse.of(permission);
    }
}
