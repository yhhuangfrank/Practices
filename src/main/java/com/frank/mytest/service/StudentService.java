package com.frank.mytest.service;

import com.frank.mytest.entity.School;
import com.frank.mytest.entity.Student;
import com.frank.mytest.respository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class StudentService {
    private final StudentRepository studentRepository;

    public void createStudents(School school) {
        ArrayList<Student> students;
        Student s1 = Student.builder().studentName("ABC").isRegistered(false).schoolId(1).build();
        Student s2 = Student.builder().studentName("DEF").isRegistered(false).schoolId(1).build();
        Student s3 = Student.builder().studentName("GHI").isRegistered(false).schoolId(1).build();
        students = new ArrayList<>(Arrays.asList(s1, s2, s3));
        studentRepository.saveAll(students);
//        school.setStudents(students);
    }

}
