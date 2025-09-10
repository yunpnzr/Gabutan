package com.yunpznr.gabutan.model.auth.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {
    private UUID id;
    private String username;
    private String name;
    private String email;
    private String password;
    private boolean isValidated;
    private String gender;
}
