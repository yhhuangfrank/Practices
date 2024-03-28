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
public class TestService2 {

    @Transactional(rollbackFor = {Exception.class})
    public void setName2(Test test) {
        if (test != null) {
            log.info("not null");
            test.setName2("Jack");
        }
    }

}
