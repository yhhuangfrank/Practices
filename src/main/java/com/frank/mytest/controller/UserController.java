package com.frank.mytest.controller;

import com.frank.mytest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public String test1(@RequestParam(value = "id", required = false) Integer id,
                        @RequestParam(value = "name", required = false) String name) {
//        System.out.println();
        userService.testUser(1);

        return "ok";
    }

    @PostMapping("/test")
    public String test1() {
//        System.out.println();
        userService.testUser(1);

        return "ok";
    }
}