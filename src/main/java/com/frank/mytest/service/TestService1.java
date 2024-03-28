package com.frank.mytest.service;

import com.frank.mytest.entity.Test;
import com.frank.mytest.respository.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestService1 {
    private final TestRepository testRepository;


    @Transactional(rollbackFor = {Exception.class})
    public Test getTest() {
        Test newTest = new Test();
        Optional<Test> testOptional = testRepository.findById(1);
        testOptional.ifPresent(test -> {
            log.info("have found...");
            test.setName1("Wendy");
            newTest.setName1(testOptional.get().getName1());
        });
//        return testOptional.orElse(null);
        return newTest;
    }

}
