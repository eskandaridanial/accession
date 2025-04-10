package com.accession.io.application.services;

import com.accession.io.domain.entities.Permission;

import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-06 13:18:28
 */
public interface PermissionService {

    Permission save(Permission permission);

    Permission findById(String permissionId);

    List<Permission> findAllByIds(List<String> permissionIds);

    List<Permission> findAll();
}
