package com.digitalsystemdreamer.servicerefdata.consumer;

import com.digitalsystemdreamer.servicerefdata.dto.FacilityDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class FacilityConsumer {

    @KafkaListener(topics = "digitalsystemdreamer.refdata.facility",
            groupId = "facility-cg",
            containerFactory = "facilityKafkaListenerContainerFactory")
    public void listenFacilityEvents(@Payload FacilityDto message) {
        System.out.println("Received Message in group facility: " + message);
    }
}
