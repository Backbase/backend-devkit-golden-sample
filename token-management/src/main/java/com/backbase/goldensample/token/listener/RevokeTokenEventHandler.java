package com.backbase.goldensample.token.listener;

import com.backbase.buildingblocks.backend.communication.event.EnvelopedEvent;
import com.backbase.buildingblocks.backend.communication.event.handler.EventHandler;
import com.backbase.goldensample.token.event.spec.v1.RevokeTokenEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RevokeTokenEventHandler implements EventHandler<RevokeTokenEvent> {

    public void handle(EnvelopedEvent<RevokeTokenEvent> envelopedEvent) {
        var event = envelopedEvent.getEvent();
        log.info("Received Token `{}` for revoke", event.getToken());
        //TODO do something with event here ...
    }
}
