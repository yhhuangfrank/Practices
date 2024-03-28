package com.frank.mytest.converter;

import com.frank.mytest.entity.School;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SchoolConverterTest {

    @Autowired
    SchoolConverter converter;

    @Test
    public void testConvert() {
        School school = new School();
        school.setId(100);
        System.out.println(converter.toDTO(school));
    }
}