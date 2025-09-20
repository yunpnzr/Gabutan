package com.yunpznr.gabutan.service.user;

import com.yunpznr.gabutan.model.user.get.GetUserResponse;
import com.yunpznr.gabutan.model.user.update.UpdateUserRequest;
import com.yunpznr.gabutan.model.user.update.UpdateUserResponse;

import java.security.Principal;
import java.util.UUID;

public interface UserService {
    GetUserResponse getUser(UUID id);
    UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest);
    void deleteUser(UUID id);
}
