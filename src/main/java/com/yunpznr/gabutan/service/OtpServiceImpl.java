package com.yunpznr.gabutan.service;

import com.yunpznr.gabutan.entity.Otp;
import com.yunpznr.gabutan.listener.OnRegisteredEvent;
import com.yunpznr.gabutan.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void sendOtp(String email) {
        String otp = String.format("%06d", (int) (Math.random() * 1000000));
        Otp build = Otp.builder().email(email).otp(otp).build();
        otpRepository.save(build);

        //kirim email
        eventPublisher.publishEvent(new OnRegisteredEvent(build));
    }

    @Override
    public void verifyOtp(String email, String otp) {

    }
}
