package com.digitalsystemdreamer.servicerefdata.consumer;

import com.digitalsystemdreamer.servicerefdata.dto.FacilityDto;
import com.digitalsystemdreamer.servicerefdata.dto.MembershipDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MembershipConsumer {

    @KafkaListener(topics = "topic-membership",
            groupId = "membership-cg",
            containerFactory ="membershipKafkaListenerContainerFactory")
    public void listenMembershipEvents(@Payload MembershipDto message) {
        System.out.println("Received Message in consumer group membership-cg: " + message);
    }
}
