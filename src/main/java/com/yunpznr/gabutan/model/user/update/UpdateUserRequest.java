package com.yunpznr.gabutan.model.user.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest {
    private String username;
    private String name;
    private String email;
    private String password;
    private String gender;
}
