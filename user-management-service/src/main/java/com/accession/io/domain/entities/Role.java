package com.accession.io.domain.entities;

import com.accession.io.domain.entities.common.BaseEntity;
import com.accession.io.domain.values.Name;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-03 23:38:08
 */
@Data
@Entity
@Table(name = "role", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

    @Embedded
    private Name name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "roles_permissions", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions;
}
