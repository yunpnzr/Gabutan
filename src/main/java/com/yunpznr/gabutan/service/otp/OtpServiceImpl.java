package com.yunpznr.gabutan.service.otp;

import com.yunpznr.gabutan.entity.Otp;
import com.yunpznr.gabutan.entity.User;
import com.yunpznr.gabutan.listener.OnRegisteredEvent;
import com.yunpznr.gabutan.model.user.otp.OtpResponse;
import com.yunpznr.gabutan.repository.auth.AuthRepository;
import com.yunpznr.gabutan.repository.otp.OtpRepository;
import com.yunpznr.gabutan.utils.validation.CustomValidation;
import com.yunpznr.gabutan.utils.EmailSender;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OtpServiceImpl implements OtpService {

    private static final Logger log = LoggerFactory.getLogger(OtpServiceImpl.class);
    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private CustomValidation validation;

    @Autowired
    private EmailSender sender;

    //kirim email
    @Override
    public void sendOtp(String email) {
        String otp = String.format("%06d", (int) (Math.random() * 1000000));
        Otp build = Otp.builder()
                .email(email)
                .otp(otp)
                .lifeTime(10L)
                .build();
        otpRepository.save(build);

        eventPublisher.publishEvent(new OnRegisteredEvent(build));
    }

    //kirim ulang otp
    @Override
    public OtpResponse resendOtp(String email) {
        validation.validate(email);

        String otp = String.format("%06d", (int) (Math.random() * 1000000));
        Otp build = Otp.builder()
                .email(email)
                .otp(otp)
                .lifeTime(10L)
                .build();

        try {
            otpRepository.save(build);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gagal tersimpan di database");
        }

        try {
            sender.sendEmail(build.getOtp(), build.getEmail());
        } catch (Exception e) {
            log.info("Email gagal dikirim karena {} ", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OTP gagal dikirim");
        }

        return OtpResponse.builder()
                .email(build.getEmail())
                .build();
    }

    //verifikasi otp
    @Transactional
    @Override
    public OtpResponse verifyOtp(String email, String otp) {
        validation.validate(email);

        Otp savedOtp = otpRepository.findById(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "OTP tidak ditemukan"));

        if (!savedOtp.getOtp().equals(otp)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OTP tidak sesuai");
        }

        User emailStatus = authRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email tidak ditemukan..."));

        emailStatus.setValidated(true);

        try {
            authRepository.save(emailStatus);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OTP gagal terkirim");
        }

        return OtpResponse.builder()
                .email(savedOtp.getEmail())
                .build();
    }
}
