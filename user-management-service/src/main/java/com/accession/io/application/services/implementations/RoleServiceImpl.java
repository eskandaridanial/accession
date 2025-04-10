package com.accession.io.application.services.implementations;

import com.accession.io.application.services.RoleService;
import com.accession.io.domain.entities.Role;
import com.accession.io.domain.values.UniqueId;
import com.accession.io.exception.AlreadyExistsException;
import com.accession.io.exception.RecordNotFoundException;
import com.accession.io.exception.reasons.AlreadyExistsReason;
import com.accession.io.exception.reasons.RecordNotFoundReason;
import com.accession.io.infrastructure.repositories.RoleRepository;
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
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        try {
            return roleRepository.save(role);
        } catch (DataIntegrityViolationException ex) {
            throw new AlreadyExistsException(AlreadyExistsReason.roleAlreadyExists());
        }
    }

    @Override
    public Role findById(String roleId) {
        return roleRepository.findById(UniqueId.of(roleId))
                .orElseThrow(() -> new RecordNotFoundException(RecordNotFoundReason.roleNotFoundById()));
    }

    @Override
    public List<Role> findAllByIds(List<String> roleIds) {
        Set<UniqueId> uniqueIds = roleIds.stream().map(UniqueId::of).collect(Collectors.toSet());
        List<Role> roles = roleRepository.findAllById(uniqueIds);
        if (roles.size() != roleIds.size())
            throw new RecordNotFoundException(RecordNotFoundReason.roleNotFoundByIds());
        return roles;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
