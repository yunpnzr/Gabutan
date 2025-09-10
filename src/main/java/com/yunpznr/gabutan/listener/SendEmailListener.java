package com.yunpznr.gabutan.listener;

import com.yunpznr.gabutan.utils.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SendEmailListener {

    @Autowired
    private EmailSender emailSender;

    @EventListener
    public void emailListener(OnRegisteredEvent event) {
        emailSender.sendEmail(event.getUser().getOtp(), event.getUser().getEmail());
    }
}
