package com.accession.io.infrastructure.repositories;

import com.accession.io.domain.values.UniqueId;
import com.accession.io.domain.entities.User;
import com.accession.io.domain.values.Email;
import io.sentry.spring.jakarta.tracing.SentrySpan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-04 14:16:01
 */
@SentrySpan(description = "UserRepository")
public interface UserRepository extends JpaRepository<User, UniqueId> {

    Optional<User> findByEmail(Email email);
}
