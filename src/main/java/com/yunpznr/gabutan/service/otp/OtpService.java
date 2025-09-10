package com.yunpznr.gabutan.service.otp;

import com.yunpznr.gabutan.model.auth.otp.OtpResponse;

public interface OtpService {
    void sendOtp(String email);
    OtpResponse resendOtp(String email);
    OtpResponse verifyOtp(String email, String otp);
}