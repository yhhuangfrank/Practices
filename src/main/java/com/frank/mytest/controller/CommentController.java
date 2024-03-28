package com.frank.mytest.controller;

import com.frank.mytest.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @DeleteMapping("/deleteTest")
    public ResponseEntity<String> deleteTest() {
        commentService.cascadeDelete(commentService.findAll());
        return ResponseEntity.ok("delete success");
    }

    @DeleteMapping("/deleteTest2")
    public ResponseEntity<String> deleteTest2() {
        commentService.deleteByIds(Arrays.asList(6,7,8));
        return ResponseEntity.ok("delete success");
    }

}
