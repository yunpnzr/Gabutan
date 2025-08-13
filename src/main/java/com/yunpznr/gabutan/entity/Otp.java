package com.yunpznr.gabutan.entity;

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
@KeySpace("otp")
public class Otp {
    @Id
    private String email;
    private String otp;
    @TimeToLive(unit = TimeUnit.MINUTES)
    private long lifeTime = 10;
}
