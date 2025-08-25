package com.yunpznr.gabutan.service.auth;

import com.yunpznr.gabutan.entity.User;
import com.yunpznr.gabutan.model.user.login.LoginRequest;
import com.yunpznr.gabutan.model.user.login.LoginResponse;
import com.yunpznr.gabutan.model.user.register.RegisterRequest;
import com.yunpznr.gabutan.model.user.register.RegisterResponse;
import com.yunpznr.gabutan.repository.auth.AuthRepository;
import com.yunpznr.gabutan.repository.jwt.TokenRepository;
import com.yunpznr.gabutan.service.otp.OtpService;
import com.yunpznr.gabutan.utils.validation.CustomValidation;
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
    private TokenRepository tokenRepository;

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

        if (authRepository.existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email sudah digunakan");
        }

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

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        validator.validate(loginRequest);

        User user = authRepository.findByEmail(loginRequest.getEmail()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email atau password salah")
        );

        if (!BCrypt.checkpw(loginRequest.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email atau password salah");
        }

        return LoginResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }


    @Transactional
    @Override
    public void deleteUser(String email) {
        validator.validate(email);

        int result = authRepository.deleteByEmail(email);

        if (result == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User gagal untuk dihapus");
        }
    }
}
