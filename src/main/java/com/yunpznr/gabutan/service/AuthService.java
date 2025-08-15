package com.yunpznr.gabutan.service;

import com.yunpznr.gabutan.model.user.register.RegisterRequest;
import com.yunpznr.gabutan.model.user.register.RegisterResponse;

public interface AuthService {
    public RegisterResponse register(RegisterRequest registerRequest);
}
