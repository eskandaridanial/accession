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
@Table(name = "realm", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@EqualsAndHashCode(callSuper = true)
public class Realm extends BaseEntity {

    @Embedded
    private Name name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> roles;
}
