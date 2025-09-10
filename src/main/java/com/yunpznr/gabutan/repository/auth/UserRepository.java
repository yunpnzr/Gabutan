package com.yunpznr.gabutan.repository.auth;

import com.yunpznr.gabutan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    int deleteByEmail(String email);
    Optional<User> findFirstByEmail(String email);
}