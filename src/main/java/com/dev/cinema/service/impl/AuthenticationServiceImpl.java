package com.dev.cinema.service.impl;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.exceptions.RegistrationException;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    public boolean isPasswordValid(User user, String password) {
        if (user.getPassword().equals(HashUtil.hashPassword(password, user.getSalt()))) {
            return true;
        }

        return false;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> userOptional = userService.findByEmail(email);
        User user = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            if (user == null || !isPasswordValid(user, password)) {
                throw new AuthenticationException("Incorrect login or password");
            }
        }

        return user;
    }

    @Override
    public User register(String email, String password) throws RegistrationException {
        if (email.isEmpty()) {
            throw new RegistrationException("Login can't be empty");
        }

        if (password.isEmpty()) {
            throw new RegistrationException("Password can't be empty");
        }

        Optional<User> userOptional = userService.findByEmail(email);;
        if (userOptional.isPresent()) {
            throw new RegistrationException("Login is already taken");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        User userFromDb = userService.add(user);
        shoppingCartService.registerNewShoppingCart(userFromDb);

        return userFromDb;
    }
}
