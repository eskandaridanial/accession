package com.accession.io.application.usecases;

import com.accession.io.application.services.PermissionService;
import com.accession.io.common.contracts.interfaces.BaseUseCase;
import com.accession.io.domain.entities.Permission;
import com.accession.io.message.queries.GetAllPermissionsQuery;
import com.accession.io.message.responses.GetAllPermissionsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 13:27:45
 */
@Component
@RequiredArgsConstructor
public class GetAllPermissionsUseCase implements BaseUseCase<GetAllPermissionsQuery, GetAllPermissionsResponse> {

    private final PermissionService permissionService;

    @Override
    public GetAllPermissionsResponse execute(GetAllPermissionsQuery query) {
        List<Permission> permissions = permissionService.findAll();
        return GetAllPermissionsResponse.of(permissions);
    }
}
