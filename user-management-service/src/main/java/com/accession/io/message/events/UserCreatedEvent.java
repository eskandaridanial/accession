package com.accession.io.message.events;

import com.accession.io.domain.entities.User;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-15 20:59:29
 */
public record UserCreatedEvent(

        String userId,

        String email
) {
    public static UserCreatedEvent of(User user) {
        return new UserCreatedEvent(user.getId().getId(), user.getEmail().getEmail());
    }
}