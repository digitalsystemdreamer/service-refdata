package com.digitalsystemdreamer.servicerefdata.producer;

import com.digitalsystemdreamer.servicerefdata.dto.FacilityDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class FacilityProducer {
    @Autowired
    private KafkaTemplate<Integer, FacilityDto> kafkaTemplate;

    public void sendMessage(FacilityDto facilityDto) {
        CompletableFuture<SendResult<Integer, FacilityDto>> future = kafkaTemplate.send("topic-facility", facilityDto.getFacilityId(), facilityDto);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Message successfully sent {}", facilityDto.getFacilityId());
            } else {
                log.error("DLQ setup for FacilityDto");
                log.error("Message sending failed", ex);
                ex.printStackTrace();
            }
        });
    }
}
