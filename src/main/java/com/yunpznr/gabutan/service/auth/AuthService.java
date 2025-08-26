package com.yunpznr.gabutan.service.auth;

import com.yunpznr.gabutan.model.user.login.LoginRequest;
import com.yunpznr.gabutan.model.user.login.LoginResponse;
import com.yunpznr.gabutan.model.user.register.RegisterRequest;
import com.yunpznr.gabutan.model.user.register.RegisterResponse;
import com.yunpznr.gabutan.model.user.token.RefreshTokenResponse;

public interface AuthService {
    public RegisterResponse register(RegisterRequest registerRequest);
    public LoginResponse login(LoginRequest loginRequest);
    public RefreshTokenResponse refreshToken(String oldToken);
    public void deleteUser(String email);
}
