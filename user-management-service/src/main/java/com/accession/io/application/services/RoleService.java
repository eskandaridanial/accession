package com.accession.io.application.services;

import com.accession.io.domain.entities.Role;

import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-06 13:18:28
 */
public interface RoleService {

    Role save(Role role);

    Role findById(String id);

    List<Role> findAllByIds(List<String> roleIds);

    List<Role> findAll();
}
