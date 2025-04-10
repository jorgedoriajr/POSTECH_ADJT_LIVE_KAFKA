package com.fiap.str_producer.resources;

import com.fiap.str_producer.services.SpringProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/producer")
public class StringProducerResource {

    private final SpringProducerService producerService;

    public StringProducerResource(SpringProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody String message) {
        producerService.sendMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
        //return ResponseEntity.status(HttpStatus.CREATED).body("Message sent successfully");
        //return ResponseEntity.ok("Message sent successfully");
        //return "Message sent successfully";
    }

}