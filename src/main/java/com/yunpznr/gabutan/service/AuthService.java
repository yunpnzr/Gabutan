package com.yunpznr.gabutan.service;

import com.yunpznr.gabutan.model.user.RegisterRequest;
import com.yunpznr.gabutan.model.user.RegisterResponse;

public interface AuthService {
    public RegisterResponse register(RegisterRequest registerRequest);
}
