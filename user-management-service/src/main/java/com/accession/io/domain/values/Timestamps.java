package com.accession.io.domain.values;

import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-04 15:04:28
 */
@Value
@NoArgsConstructor(force = true)
public class Timestamps {

    LocalDateTime createdAt;
    LocalDateTime modifiedAt;

    private Timestamps(LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static Timestamps of() {
        LocalDateTime now = LocalDateTime.now();
        return new Timestamps(now, now);
    }

    public static Timestamps of(LocalDateTime createdDate, LocalDateTime modifiedDate) {
        return new Timestamps(createdDate, modifiedDate);
    }

    public Timestamps update() {
        return new Timestamps(this.createdAt, LocalDateTime.now());
    }
}