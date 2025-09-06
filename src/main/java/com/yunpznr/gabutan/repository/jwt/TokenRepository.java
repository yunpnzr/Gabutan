package com.yunpznr.gabutan.repository.jwt;

import com.yunpznr.gabutan.entity.Token;
import com.yunpznr.gabutan.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {
    Optional<Token> findByToken(String token);
    Optional<Token> findByUser(User user);
}