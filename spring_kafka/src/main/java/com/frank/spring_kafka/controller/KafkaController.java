package com.frank.spring_kafka.controller;

import com.frank.spring_kafka.config.KafkaConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/produce")
    public ResponseEntity<?> produce(@RequestBody String request) {
        try {
            kafkaTemplate.send(KafkaConfig.TOPIC_TEST, request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("error!", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
