package com.frank.mytest.service;

import com.frank.mytest.entity.School;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaService {
    private final SchoolService schoolService;
    private final StudentService studentService;

    public void jpaTest() {
        School school = schoolService.findById(1);
        if (school != null) {
            studentService.createStudents(school);
            school.setSchoolName("B");
            schoolService.saveEntity(school);
        }
    }

}
