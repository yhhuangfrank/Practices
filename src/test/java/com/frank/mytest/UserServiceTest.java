package com.frank.mytest;

import com.frank.mytest.service.DeptService;
import com.frank.mytest.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;

    @Test
    public void test1() {
        userService.createUser(1, "Frank");
        userService.createUser(2, "Wendy");
    }

    @Test
    public void test2() {
        userService.testUser(1);
    }

    @Test
    public void test3() {
        userService.batchInsert();
    }

    @Test
    public void test4() {
        userService.createUserByEm(101, "aa");
    }

    @Test
    public void test5() {
        userService.insertTestByEm();
    }

    @Test
    public void test6() {
        int a = 1;
        System.out.println(1 + ++a); // 3
        a = 1;
        System.out.println(1 + a++); // 2
    }

    @Test
    public void test7() {
        userService.searchUser(5);
    }

}