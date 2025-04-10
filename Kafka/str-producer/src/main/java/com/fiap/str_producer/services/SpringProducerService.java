package com.fiap.str_producer.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SpringProducerService {

    private static final Logger log = LoggerFactory.getLogger(SpringProducerService.class);
    public final KafkaTemplate<String, String> kafkaTemplate;

    public SpringProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        Thread.startVirtualThread(() -> {
            kafkaTemplate.send("str-topic", message)
                    .thenAccept(result -> {
                        int partition = result.getRecordMetadata().partition();
                        long offset = result.getRecordMetadata().offset();
                        log.info("Message {} sent successfully to partition {} at offset {}", message, partition, offset);
                    })
                    .exceptionally(error -> {
                        log.error("Failed to send message", error);
                        return null;
                    });
        });
    }

}