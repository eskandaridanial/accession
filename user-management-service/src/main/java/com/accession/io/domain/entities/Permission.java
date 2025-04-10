package com.accession.io.domain.entities;

import com.accession.io.domain.entities.common.BaseEntity;
import com.accession.io.domain.values.Endpoint;
import com.accession.io.domain.values.HttpMethod;
import com.accession.io.domain.values.Name;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-03 23:43:09
 */
@Data
@Entity
@Table(name = "permission", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseEntity {

    @Embedded
    private Name name;

    @Embedded
    private Endpoint endpoint;

    @Embedded
    private HttpMethod httpMethod;

    @Column
    private Boolean verificationRequired;
}
