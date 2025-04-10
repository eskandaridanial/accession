package com.accession.io.domain.entities.common;

import com.accession.io.domain.values.SessionId;
import com.accession.io.domain.values.Subject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(callSuper = true)
public abstract class BaseCredential extends BaseEntity {

    @Embedded
    private SessionId sessionId;

    @Embedded
    private Subject subject;
}