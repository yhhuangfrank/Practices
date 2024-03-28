package com.frank.mytest.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SchoolServiceTest {

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private JpaService jpaService;

    @Test
    public void findById() {
        schoolService.findById(1);
    }

    @Test
    public void jpaTest() {
        jpaService.jpaTest();
    }

    @Test
    void findByEntityManager() {
        schoolService.findByEntityManager(1);
    }

    @Test
    void updateSchool() {
        schoolService.updateSchool(1);
    }
}