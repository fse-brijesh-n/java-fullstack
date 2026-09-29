package com.example.authservice.application.service;

import com.example.authservice.domain.model.User;
import com.example.authservice.domain.port.in.RegisterUserUseCase;
import com.example.authservice.domain.port.out.UserRepositoryPort;
import com.example.common.exception.BusinessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Use-case implementation: pure orchestration, no framework/web/persistence concerns
 * beyond calling the outbound port.
 */
@Service
public class RegisterUserService implements RegisterUserUseCase {

    private static final String DEFAULT_ROLE = "USER";

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserService(UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(RegisterCommand command) {
        if (userRepositoryPort.existsByUsername(command.username())) {
            throw new BusinessException("Username already taken: " + command.username());
        }

        var user = new com.example.authservice.domain.model.User(
                null,
                command.username(),
                passwordEncoder.encode(command.rawPassword()),
                DEFAULT_ROLE);

        var saved = userRepositoryPort.save(user);
        return new User(saved.getId(), saved.getUsername(), saved.getRole());
    }
}
