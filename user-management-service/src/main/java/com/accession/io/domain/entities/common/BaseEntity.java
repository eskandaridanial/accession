package com.accession.io.domain.entities.common;

import com.accession.io.domain.values.Timestamps;
import com.accession.io.domain.values.UniqueId;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import lombok.Data;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-05 13:15:45
 */
@Data
@MappedSuperclass
public abstract class BaseEntity {

    @EmbeddedId
    private UniqueId id;

    @Embedded
    private Timestamps timestamps;

    protected BaseEntity() {
        this.id = UniqueId.of();
        this.timestamps = Timestamps.of();
    }

    @PreUpdate
    protected void onUpdate() {
        this.timestamps = this.timestamps.update();
    }
}
