package com.example.authservice.adapter.out.persistence;

import com.example.authservice.domain.model.User;
import com.example.authservice.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Outbound adapter: implements the domain's UserRepositoryPort using Spring Data JPA,
 * translating between the JPA entity and the framework-free domain model.
 */
@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository jpaRepository;

    public UserRepositoryAdapter(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = new UserJpaEntity(user.getId(), user.getUsername(), user.getPasswordHash(), user.getRole());
        UserJpaEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaRepository.findByUsername(username).map(this::toDomain);
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaRepository.existsByUsername(username);
    }

    private User toDomain(UserJpaEntity entity) {
        return new User(entity.getId(), entity.getUsername(), entity.getPasswordHash(), entity.getRole());
    }
}
