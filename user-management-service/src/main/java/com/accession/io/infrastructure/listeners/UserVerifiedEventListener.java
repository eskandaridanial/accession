package com.accession.io.infrastructure.listeners;

import com.accession.io.application.services.UserService;
import com.accession.io.domain.entities.User;
import com.accession.io.message.events.UserVerifiedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-15 21:12:44
 */
@Component
@RequiredArgsConstructor
public class UserVerifiedEventListener {

    private final UserService userService;

    @RabbitListener(queues = "${events.user.verified.queue}")
    public void listen(UserVerifiedEvent event) {
        User user = userService.findById(event.userId());
        user.setIsVerified(true);
        userService.save(user);
    }
}
