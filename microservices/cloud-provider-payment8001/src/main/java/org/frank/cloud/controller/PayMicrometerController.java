package org.frank.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PayMicrometerController {

    /**
     * test Micrometer
     */
    @GetMapping("/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id) {
        return "Micrometer API called, inputId: " + id + UUID.randomUUID().toString().replace("-", "");
    }
}
