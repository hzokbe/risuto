package com.hzokbe.risuto.service.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hzokbe.risuto.exception.user.InvalidPasswordException;
import com.hzokbe.risuto.exception.user.InvalidUsernameException;
import com.hzokbe.risuto.exception.user.UserAlreadyRegisteredException;
import com.hzokbe.risuto.model.user.User;
import com.hzokbe.risuto.model.user.request.UserRequest;
import com.hzokbe.risuto.model.user.response.UserResponse;
import com.hzokbe.risuto.repository.user.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;

    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;

        this.encoder = encoder;
    }

    public UserResponse signUp(UserRequest request) {
        var username = request.getUsername();

        if (username == null) {
            throw new InvalidUsernameException("username must not be null");
        }

        username = username.trim();

        if (repository.existsByUsername(username)) {
            throw new UserAlreadyRegisteredException("user already registered");
        }

        if (username.isEmpty()) {
            throw new InvalidUsernameException("username must not be blank");
        }

        if (username.length() < 8) {
            throw new InvalidUsernameException("username must be at least 8 characters long");
        }

        if (!username.matches("^[a-zA-Z0-9_]+$")) {
            throw new InvalidUsernameException("username must contain only letters, numbers, and underscores");
        }

        var rawPassword = request.getRawPassword();

        if (rawPassword == null) {
            throw new InvalidPasswordException("password must not be null");
        }

        rawPassword = rawPassword.trim();

        if (rawPassword.isEmpty()) {
            throw new InvalidPasswordException("password must not be blank");
        }

        if (rawPassword.length() < 8) {
            throw new InvalidPasswordException("password must be at least 8 characters long");
        }

        var user = new User(username, encoder.encode(rawPassword));

        user = repository.save(user);

        return new UserResponse(user.getId(), username);
    }
}
