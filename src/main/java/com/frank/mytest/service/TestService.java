package com.frank.mytest.service;

import com.frank.mytest.entity.Test;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {
    private final TestService1 testService1;
    private final TestService2 testService2;

    public void testJpa() {
        Test test = testService1.getTest();
        testService2.setName2(test);
    }

}
