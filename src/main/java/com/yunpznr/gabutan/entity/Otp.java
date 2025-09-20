package com.yunpznr.gabutan.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//redis
@KeySpace("otp")
public class Otp {
    @Id
    private String email;
    @NotBlank(message = "OTP tidak boleh kosong")
    private String otp;
    @Builder.Default
    @TimeToLive
    private long lifeTime = 60;
}
