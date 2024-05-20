package com.digitalsystemdreamer.servicerefdata.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class FacilityConsumer {

    @KafkaListener(topics = "digitalsystemdreamer.refdata.facility", groupId = "facility")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group facility: " + message);
    }
}
