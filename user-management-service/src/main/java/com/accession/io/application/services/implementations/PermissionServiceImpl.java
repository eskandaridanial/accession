package com.accession.io.application.services.implementations;

import com.accession.io.application.services.PermissionService;
import com.accession.io.domain.entities.Permission;
import com.accession.io.domain.values.UniqueId;
import com.accession.io.exception.AlreadyExistsException;
import com.accession.io.exception.RecordNotFoundException;
import com.accession.io.exception.reasons.AlreadyExistsReason;
import com.accession.io.exception.reasons.RecordNotFoundReason;
import com.accession.io.infrastructure.repositories.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-06 13:08:13
 */
@Component
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public Permission save(Permission permission) {
        try {
            return permissionRepository.save(permission);
        } catch (DataIntegrityViolationException ex) {
            throw new AlreadyExistsException(AlreadyExistsReason.permissionAlreadyExists());
        }
    }

    @Override
    public Permission findById(String permissionId) {
        return permissionRepository.findById(UniqueId.of(permissionId))
                .orElseThrow(() -> new RecordNotFoundException(RecordNotFoundReason.permissionNotFoundById()));
    }

    @Override
    public List<Permission> findAllByIds(List<String> permissionIds) {
        Set<UniqueId> uniqueIds = permissionIds.stream().map(UniqueId::of).collect(Collectors.toSet());
        List<Permission> permissions = permissionRepository.findAllById(uniqueIds);
        if (permissions.size() != permissionIds.size())
            throw new RecordNotFoundException(RecordNotFoundReason.permissionNotFoundByIds());
        return permissions;
    }

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }
}
