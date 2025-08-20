package com.yunpznr.gabutan.service;

import com.yunpznr.gabutan.model.user.otp.OtpResponse;

import java.util.Optional;

public interface OtpService {
    void sendOtp(String email);
    OtpResponse resendOtp(String email);
    /*Optional<Boolean> verifyOtp(String email, String otp);*/
    OtpResponse verifyOtp(String email, String otp);
}
