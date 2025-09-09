package com.yunpznr.gabutan.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserResponse {
    private UUID id;
    private String username;
    private String name;
    private String email;
    private String gender;
    private Instant createdAt;
}
