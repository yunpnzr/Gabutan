package com.yunpznr.gabutan.model.user.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserResponse {
    private UUID id;
    private String username;
    private String name;
    private String email;
    private String password;
    private boolean isValidated;
    private String gender;
}
