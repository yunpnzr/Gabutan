package com.yunpznr.gabutan.service;

import com.yunpznr.gabutan.entity.Otp;
import com.yunpznr.gabutan.entity.User;
import com.yunpznr.gabutan.listener.OnRegisteredEvent;
import com.yunpznr.gabutan.model.user.otp.OtpResponse;
import com.yunpznr.gabutan.repository.AuthRepository;
import com.yunpznr.gabutan.repository.OtpRepository;
import com.yunpznr.gabutan.utils.CustomValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private CustomValidation validation;

    //kirim email
    @Override
    public void sendOtp(String email) {
        String otp = String.format("%06d", (int) (Math.random() * 1000000));
        Otp build = Otp.builder().email(email).otp(otp).build();
        otpRepository.save(build);

        //kirim email
        eventPublisher.publishEvent(new OnRegisteredEvent(build));
    }

    //verifikasi otp
    @Override
    public OtpResponse verifyOtp(String email, String otp) {
        validation.validate(email);

        Otp savedOtp = otpRepository.findById(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "OTP tidak ditemukan"));

        if (!savedOtp.getOtp().equals(otp)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OTP tidak sesuai");
        }

        User emailStatus = authRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email tidak ditemukan"));

        emailStatus.setValidated(true);
        authRepository.save(emailStatus);

        return OtpResponse.builder()
                .email(savedOtp.getEmail())
                .build();
    }

    //verifikasi otp
    /*@Override
    public Optional<Boolean> verifyOtp(String email, String otp) {
        validation.validate(email);

        return otpRepository.findById(email).map(savedOtp -> {
            boolean isValid = savedOtp.getOtp().equals(otp);
            if (isValid) {
                authRepository.findById(email).ifPresent(user -> user.setValidated(true));
                otpRepository.delete(savedOtp);
            }
            return isValid;
        });
    }*/
}
