package com.accession.io.message.values;

import de.huxhorn.sulky.ulid.ULID;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-04 14:57:31
 */
@Value
@NoArgsConstructor(force = true)
public class UniqueId {

    String id;

    private UniqueId(String id) {
        this.id = id;
    }

    public static UniqueId of() {
        String id = new ULID().nextULID();
        return new UniqueId(id);
    }

    public static UniqueId of(String id) {
        return new UniqueId(id);
    }
}
