package com.example.authservice.application.service;

import com.example.authservice.domain.port.in.LoginUseCase;
import com.example.authservice.domain.port.out.UserRepositoryPort;
import com.example.common.exception.BusinessException;
import com.example.common.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginService implements LoginUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginService(UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResult login(LoginCommand command) {
        var user = userRepositoryPort.findByUsername(command.username())
                .orElseThrow(() -> new BusinessException("Invalid username or password"));

        if (!passwordEncoder.matches(command.rawPassword(), user.getPasswordHash())) {
            throw new BusinessException("Invalid username or password");
        }

        String token = jwtUtil.generateToken(user.getUsername(), Map.of("role", user.getRole()));
        return new LoginResult(token, user.getUsername(), user.getRole());
    }
}
