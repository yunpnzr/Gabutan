package com.yunpznr.gabutan.listener;

import com.yunpznr.gabutan.entity.Otp;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OnRegisteredEvent extends ApplicationEvent {

    private final Otp user;

    public OnRegisteredEvent(Otp user) {
        super(user);
        this.user = user;
    }
}
