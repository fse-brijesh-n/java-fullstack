package com.example.authservice.domain.port.out;

import com.example.authservice.domain.model.User;

import java.util.Optional;

/**
 * Outbound port - what the domain needs from the outside world (persistence).
 * Implemented by adapter/out/persistence, injected into the application services.
 */
public interface UserRepositoryPort {

    User save(User user);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
