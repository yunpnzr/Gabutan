package com.yunpznr.gabutan.service;

import com.yunpznr.gabutan.entity.User;
import com.yunpznr.gabutan.model.user.register.RegisterRequest;
import com.yunpznr.gabutan.model.user.register.RegisterResponse;
import com.yunpznr.gabutan.repository.AuthRepository;
import com.yunpznr.gabutan.utils.CustomValidation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private OtpService otpService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private CustomValidation validator;

    @Transactional
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        User user = new User();

        user.setId(UUID.randomUUID());
        user.setUsername(registerRequest.getUsername());
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());

        String plainPassword = registerRequest.getPassword();
        String pwHash = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        user.setPassword(pwHash);
        user.setValidated(false);

        validator.validate(user);

        authRepository.save(user);

        if(!registerRequest.getName().isBlank() &&
                !registerRequest.getUsername().isBlank() &&
                !registerRequest.getEmail().isBlank() &&
                !registerRequest.getPassword().trim().isBlank()) {
            otpService.sendOtp(user.getEmail());
        }

        return RegisterResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .isValidated(user.isValidated())
                .build();
    }
}
