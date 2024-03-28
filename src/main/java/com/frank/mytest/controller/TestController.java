package com.frank.mytest.controller;

import com.frank.mytest.entity.Comment;
import com.frank.mytest.entity.Reply;
import com.frank.mytest.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TestController {

    private final CommentService commentService;
    private final ReplyService replyService;
    private final HttpService1 httpService1;
    private final HttpService2 httpService2;
    private final TestService testService;

    @PostMapping("/jpatest")
    public ResponseEntity<String> test() {
        Comment comment = commentService.findById(10);
        List<Reply> replies = comment.getReplies();
        log.info("users : " + replies);
        for (Reply reply : replies) {
            reply.setContent("test");
        }
        for (Reply reply : replies) {
            System.out.println(replyService.findById(reply.getId()));
        }
        return ResponseEntity.ok("ok");
    }


    @GetMapping("/webclient")
    public void testWebClient() throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                httpService1.callApi();
            }, String.valueOf(i)).start();
        }

        TimeUnit.SECONDS.sleep(1);

        for (int i = 11; i <= 20; i++) {
            new Thread(() -> {
                httpService2.callApi();
            }, String.valueOf(i)).start();
        }
    }

    @GetMapping("/testException")
    public ResponseEntity<String> testException() {
        int i = 1 / 0;
        return ResponseEntity.ok("success");
    }

    @GetMapping("/testJpa")
    public ResponseEntity<?> testJpa() {
        testService.testJpa();
        return ResponseEntity.ok().build();
    }
}
