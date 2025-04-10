package com.accession.io.infrastructure.repositories;

import com.accession.io.domain.values.UniqueId;
import com.accession.io.domain.entities.Permission;
import io.sentry.spring.jakarta.tracing.SentrySpan;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 14:16:01
 */
@SentrySpan(description = "PermissionRepository")
public interface PermissionRepository extends JpaRepository<Permission, UniqueId> { }
