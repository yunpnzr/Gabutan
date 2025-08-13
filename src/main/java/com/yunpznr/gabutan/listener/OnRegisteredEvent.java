package com.yunpznr.gabutan.listener;

import com.yunpznr.gabutan.entity.Otp;
import com.yunpznr.gabutan.entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Getter
public class OnRegisteredEvent extends ApplicationEvent {

    private final Otp user;

    public OnRegisteredEvent(Otp user) {
        super(user);
        this.user = user;
    }
}
