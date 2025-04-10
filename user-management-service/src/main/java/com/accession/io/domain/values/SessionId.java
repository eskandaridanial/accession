package com.accession.io.domain.values;

import de.huxhorn.sulky.ulid.ULID;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-04 14:57:31
 */
@Value
@NoArgsConstructor(force = true)
public class SessionId {

    String sessionId;

    private SessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public static SessionId of() {
        String sessionId = new ULID().nextULID();
        return new SessionId(sessionId);
    }
}
