package com.yunpznr.gabutan.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SendEmailListener {

    @Autowired
    private JavaMailSender mailSender;

    @EventListener
    public void sendEmail(OnRegisteredEvent event) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(event.getUser().getEmail());
        message.setSubject("Kode Aktivasi");
        //message.setText("Kode aktivasimu adalah " + event.getUser().getToken());
        message.setText("Kode aktivasimu adalah " + event.getUser().getOtp());
        mailSender.send(message);
    }
}
