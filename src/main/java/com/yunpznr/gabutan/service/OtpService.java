package com.yunpznr.gabutan.service;

public interface OtpService {
    void sendOtp(String email);
    void verifyOtp(String email, String otp);
}
