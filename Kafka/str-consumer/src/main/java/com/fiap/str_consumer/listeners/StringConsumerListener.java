package com.fiap.str_consumer.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class StringConsumerListener {

    private static final Logger log = LoggerFactory.getLogger(StringConsumerListener.class);

    @KafkaListener(groupId = "group-1", topics = "str-topic", containerFactory = "strContainerFactory")
    public void consume(String message) {
        log.info("Received message: {}", message);
    }

}