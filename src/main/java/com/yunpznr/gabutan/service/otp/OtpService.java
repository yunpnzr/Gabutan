package com.yunpznr.gabutan.service.otp;

import com.yunpznr.gabutan.model.user.otp.OtpResponse;

public interface OtpService {
    void sendOtp(String email);
    OtpResponse resendOtp(String email);
    /*Optional<Boolean> verifyOtp(String email, String otp);*/
    OtpResponse verifyOtp(String email, String otp);
}
