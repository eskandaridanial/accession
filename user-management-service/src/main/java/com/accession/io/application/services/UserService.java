package com.accession.io.application.services;

import com.accession.io.domain.entities.User;

import java.util.List;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-06 13:18:28
 */
public interface UserService {

    User save(User user);

    User findById(String id);

    User findByEmail(String email);

    List<User> findAll();
}
