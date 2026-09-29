package com.example.authservice.application.service;

import com.example.authservice.domain.model.User;
import com.example.authservice.domain.port.in.LoginUseCase;
import com.example.authservice.domain.port.out.UserRepositoryPort;
import com.example.common.exception.BusinessException;
import com.example.common.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @Mock
    private PasswordEncoder passwordEncoder;

    private LoginService loginService;

    @BeforeEach
    void setUp() {
        // Real JwtUtil (not mocked) - the point of the test is to prove a usable token
        // comes out the other end, matching what auth-service actually issues.
        JwtUtil jwtUtil = new JwtUtil("learning-project-demo-secret-key-change-me", 60_000);
        loginService = new LoginService(userRepositoryPort, passwordEncoder, jwtUtil);
    }

    @Test
    void issuesTokenForValidCredentials() {
        User storedUser = new User(1L, "alice", "hashed-secret1", "USER");
        when(userRepositoryPort.findByUsername("alice")).thenReturn(Optional.of(storedUser));
        when(passwordEncoder.matches("secret1", "hashed-secret1")).thenReturn(true);

        LoginUseCase.LoginResult result = loginService.login(
                new LoginUseCase.LoginCommand("alice", "secret1"));

        assertThat(result.username()).isEqualTo("alice");
        assertThat(result.role()).isEqualTo("USER");
        assertThat(result.token()).isNotBlank();
    }

    @Test
    void rejectsUnknownUsername() {
        when(userRepositoryPort.findByUsername("ghost")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> loginService.login(new LoginUseCase.LoginCommand("ghost", "whatever")))
                .isInstanceOf(BusinessException.class);
    }

    @Test
    void rejectsWrongPassword() {
        User storedUser = new User(1L, "alice", "hashed-secret1", "USER");
        when(userRepositoryPort.findByUsername("alice")).thenReturn(Optional.of(storedUser));
        when(passwordEncoder.matches("wrong-password", "hashed-secret1")).thenReturn(false);

        assertThatThrownBy(() -> loginService.login(new LoginUseCase.LoginCommand("alice", "wrong-password")))
                .isInstanceOf(BusinessException.class);
    }
}
