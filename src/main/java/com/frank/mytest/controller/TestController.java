package com.frank.mytest.controller;

import com.frank.mytest.service.TextService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TestController {

    private final TextService textService;

    @PostMapping("/text")
    public ResponseEntity<String> testText() {
         textService.deleteAndInsertText(3, "Hello, world2!");
         return ResponseEntity.ok("Text updated successfully!");
    }

}
