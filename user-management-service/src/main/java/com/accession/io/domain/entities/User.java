package com.accession.io.domain.entities;

import com.accession.io.domain.entities.common.BaseCredential;
import com.accession.io.domain.entities.common.BaseEntity;
import com.accession.io.domain.values.Email;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-03 17:29:57
 */
@Data
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"email", "realm_id"}))
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @Embedded
    private Email email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<BaseCredential> credentials;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "realm_id", nullable = false)
    private Realm realm;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @Column
    private Boolean isVerified;

    public void add(BaseCredential credential) {
        if (this.credentials == null)
            this.credentials = new ArrayList<>();
        credentials.add(credential);
    }

    public Map<String, Object> claims() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", getId().getId());
        claims.put("email", email.getEmail());
        claims.put("realm_id", realm.getId().getId());
        List<String> roleIds = roles.stream()
                .map(role -> role.getId().getId())
                .toList();
        claims.put("role_ids", roleIds);
        return claims;
    }
}
