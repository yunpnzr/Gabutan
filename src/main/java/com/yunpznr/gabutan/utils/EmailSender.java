package com.yunpznr.gabutan.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String otp, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Kode Aktivasi");
        message.setText("Kode aktivasimu adalah " + otp);

        message.setTo(email);
        mailSender.send(message);
    }
}
