package com.yunpznr.gabutan.model.auth.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private UUID id;
    private String username;
    private String name;
    private String email;
    private String token;
    private Long expirationDate;
}
