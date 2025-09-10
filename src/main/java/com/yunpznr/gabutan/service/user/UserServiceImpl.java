package com.yunpznr.gabutan.service.user;

import com.yunpznr.gabutan.entity.User;
import com.yunpznr.gabutan.model.user.get.GetUserResponse;
import com.yunpznr.gabutan.model.user.update.UpdateUserRequest;
import com.yunpznr.gabutan.model.user.update.UpdateUserResponse;
import com.yunpznr.gabutan.repository.auth.UserRepository;
import com.yunpznr.gabutan.repository.jwt.TokenRepository;
import com.yunpznr.gabutan.utils.validation.CustomValidation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private CustomValidation validator;

    @Override
    public GetUserResponse getUser(String email) {
        validator.validate(email);

        User user = userRepository.findFirstByEmail(email).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User tidak ditemukan"));

        return GetUserResponse.builder()
                .id(user.getId())
                .username(user.getUsernameUser())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }

    @Transactional
    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest) {
        validator.validate(updateUserRequest);

        User user = userRepository.findFirstByEmail(updateUserRequest.getEmail()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User tidak ditemukan")
        );

        try {
            user.setName(updateUserRequest.getName());
            user.setUsername(updateUserRequest.getUsername());
            user.setPassword(updateUserRequest.getPassword());
            user.setGender(updateUserRequest.getGender());

            userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gagal tersimpan di database");
        }

        return UpdateUserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .username(user.getUsernameUser())
                .password(user.getPassword())
                .gender(user.getGender())
                .build();
    }

    @Transactional
    @Override
    public void deleteUser(String email) {
        validator.validate(email);

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User tidak ditemukan"));

        tokenRepository.deleteByUser(user);

        int result = userRepository.deleteByEmail(email);

        if (result == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User gagal untuk dihapus");
        }
    }
}
