package com.accession.io.application.services.implementations;

import com.accession.io.application.services.UserService;
import com.accession.io.domain.entities.User;
import com.accession.io.domain.values.Email;
import com.accession.io.domain.values.UniqueId;
import com.accession.io.exception.AlreadyExistsException;
import com.accession.io.exception.RecordNotFoundException;
import com.accession.io.exception.reasons.AlreadyExistsReason;
import com.accession.io.exception.reasons.RecordNotFoundReason;
import com.accession.io.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-06 13:08:13
 */
@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new AlreadyExistsException(AlreadyExistsReason.userAlreadyExists());
        }
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(UniqueId.of(id))
                .orElseThrow(() -> new RecordNotFoundException(RecordNotFoundReason.userNotFoundById()));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(Email.of(email))
                .orElseThrow(() -> new RecordNotFoundException(RecordNotFoundReason.userNotFoundByEmail()));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
