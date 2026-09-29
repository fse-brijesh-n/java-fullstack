package com.example.authservice.application.service;

import com.example.authservice.domain.model.User;
import com.example.authservice.domain.port.in.RegisterUserUseCase;
import com.example.authservice.domain.port.out.UserRepositoryPort;
import com.example.common.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for the application service with the outbound port mocked - this is the
 * hexagonal-architecture pattern in action: no Spring context, no database, no web layer.
 */
@ExtendWith(MockitoExtension.class)
class RegisterUserServiceTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @Mock
    private PasswordEncoder passwordEncoder;

    private RegisterUserService registerUserService;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        registerUserService = new RegisterUserService(userRepositoryPort, passwordEncoder);
    }

    @Test
    void registersNewUserWithEncodedPasswordAndDefaultRole() {
        when(userRepositoryPort.existsByUsername("alice")).thenReturn(false);
        when(passwordEncoder.encode("secret1")).thenReturn("encoded-secret1");
        when(userRepositoryPort.save(any(User.class))).thenAnswer(invocation -> {
            User u = invocation.getArgument(0);
            return new User(1L, u.getUsername(), u.getPasswordHash(), u.getRole());
        });

        RegisterUserUseCase.User result = registerUserService.register(
                new RegisterUserUseCase.RegisterCommand("alice", "secret1"));

        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.username()).isEqualTo("alice");
        assertThat(result.role()).isEqualTo("USER");
        verify(passwordEncoder).encode("secret1");
    }

    @Test
    void rejectsDuplicateUsername() {
        when(userRepositoryPort.existsByUsername("alice")).thenReturn(true);

        assertThatThrownBy(() -> registerUserService.register(
                new RegisterUserUseCase.RegisterCommand("alice", "secret1")))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("alice");

        verify(userRepositoryPort, never()).save(any());
        verify(passwordEncoder, never()).encode(anyString());
    }
}
