package com.yunpznr.gabutan.service.auth;

import com.yunpznr.gabutan.model.user.GetUserResponse;
import com.yunpznr.gabutan.model.user.login.LoginRequest;
import com.yunpznr.gabutan.model.user.login.LoginResponse;
import com.yunpznr.gabutan.model.user.register.RegisterRequest;
import com.yunpznr.gabutan.model.user.register.RegisterResponse;
import com.yunpznr.gabutan.model.user.token.RefreshTokenResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest) throws InterruptedException;
    RefreshTokenResponse refreshToken(String oldToken);
    GetUserResponse getUser(String email);
    void deleteUser(String email);
}
