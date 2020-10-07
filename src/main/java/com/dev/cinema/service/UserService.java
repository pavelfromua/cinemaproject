package com.dev.cinema.service;

import com.dev.cinema.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);

    User findById(Long id);

    List<User> getAll();
}
