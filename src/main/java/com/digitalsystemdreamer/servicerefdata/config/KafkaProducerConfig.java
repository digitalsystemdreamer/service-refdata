package com.digitalsystemdreamer.servicerefdata.config;

import com.digitalsystemdreamer.servicerefdata.dto.FacilityDto;
import com.digitalsystemdreamer.servicerefdata.dto.MembershipDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value(value = "${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.producer.acks}")
    private String acks;

    @Value(value = "${spring.kafka.producer.properties.delivery.timeout.ms}")
    private String deliveryTimeout;

    @Value(value = "${spring.kafka.producer.properties.linger.ms}")
    private String linger;

    @Value(value = "${spring.kafka.producer.properties.request.timeout.ms}")
    private String requestTimeout;

    public ProducerFactory<Integer, FacilityDto> facilityProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);
        configProps.put(ProducerConfig.ACKS_CONFIG,acks);
        configProps.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG,deliveryTimeout);
        configProps.put(ProducerConfig.LINGER_MS_CONFIG,linger);
        configProps.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG,requestTimeout);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<Integer, FacilityDto> facilityKafkaTemplate() {
        return new KafkaTemplate<>(facilityProducerFactory());
    }


    public ProducerFactory<Integer, MembershipDto> membershipProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);
        configProps.put(ProducerConfig.ACKS_CONFIG,acks);
        configProps.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG,deliveryTimeout);
        configProps.put(ProducerConfig.LINGER_MS_CONFIG,linger);
        configProps.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG,requestTimeout);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<Integer, MembershipDto> membershipKafkaTemplate() {
        return new KafkaTemplate<>(membershipProducerFactory());
    }
}
