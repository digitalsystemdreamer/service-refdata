package com.digitalsystemdreamer.servicerefdata.producer;

import com.digitalsystemdreamer.servicerefdata.dto.FacilityDto;
import com.digitalsystemdreamer.servicerefdata.dto.MembershipDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class MembershipProducer {
    @Autowired
    private KafkaTemplate<Integer, MembershipDto> kafkaTemplate;

    public void sendMessage(MembershipDto membershipDto) {
        CompletableFuture<SendResult<Integer, MembershipDto>> future = kafkaTemplate.send("topic-membership", membershipDto.getMembershipId(), membershipDto);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Message successfully sent {}", membershipDto.getMembershipId());
            } else {
                log.error("DLQ setup for FacilityDto");
                log.error("Message sending failed", ex);
            }
        });
    }
}
