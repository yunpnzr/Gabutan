package com.yunpznr.gabutan.repository.auth;

import com.yunpznr.gabutan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    int deleteUserById(UUID id);
    Optional<User> findFirstByEmail(String email);
    Optional<User> findFirstById(UUID id);
}