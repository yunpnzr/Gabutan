package com.yunpznr.gabutan.service.user;

import com.yunpznr.gabutan.model.user.get.GetUserResponse;
import com.yunpznr.gabutan.model.user.update.UpdateUserRequest;
import com.yunpznr.gabutan.model.user.update.UpdateUserResponse;

public interface UserService {
    GetUserResponse getUser(String email);
    UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest);
    void deleteUser(String email);
}
