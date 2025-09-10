package com.yunpznr.gabutan.service.auth;

import com.yunpznr.gabutan.model.user.get.GetUserResponse;
import com.yunpznr.gabutan.model.auth.login.LoginRequest;
import com.yunpznr.gabutan.model.auth.login.LoginResponse;
import com.yunpznr.gabutan.model.auth.register.RegisterRequest;
import com.yunpznr.gabutan.model.auth.register.RegisterResponse;
import com.yunpznr.gabutan.model.auth.token.RefreshTokenResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest) throws InterruptedException;
    RefreshTokenResponse refreshToken(String oldToken);
}
