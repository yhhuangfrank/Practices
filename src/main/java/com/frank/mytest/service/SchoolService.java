package com.frank.mytest.service;

import com.frank.mytest.converter.SchoolConverter;
import com.frank.mytest.entity.School;
import com.frank.mytest.dto.SchoolDTO;
import com.frank.mytest.entity.Student;
import com.frank.mytest.respository.SchoolRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final EntityManager entityManager;
    private final SchoolConverter schoolConverter;
    private final ModelMapper modelMapper = new ModelMapper();
    public School findById(Integer id) {
        Optional<School> schoolOptional = schoolRepository.findById(id);
        schoolOptional.ifPresent(school -> {
//            System.out.println(school);
            SchoolDTO dto = modelMapper.map(school, SchoolDTO.class);
            System.out.println(dto);
        });
        return schoolOptional.orElse(null);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void saveEntity(School school) {
        schoolRepository.save(school);
    }

    public void findByEntityManager(Integer id) {
        String sql = "SELECT s FROM School s LEFT JOIN FETCH s.students WHERE s.id = :id";
        TypedQuery<School> query = entityManager.createQuery(sql, School.class);
        query.setParameter("id", id);
        School school = query.getSingleResult();
        System.out.println(school);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void updateSchool(Integer id) {
        School school = schoolRepository.findById(id).orElseThrow();
        SchoolDTO schoolDTO = new SchoolDTO();
        Student s1 = Student.builder().studentName("aaa").isRegistered(false).schoolId(1).build();
        Student s2 = Student.builder().studentName("bbb").isRegistered(false).schoolId(1).build();
        Student s3 = Student.builder().studentName("ccc").isRegistered(false).schoolId(1).build();
        schoolDTO.setStudents(List.of(s1,s2,s3));
        schoolDTO.setSchoolName("ggg");
        System.out.println(schoolDTO);
        School newEntity = schoolConverter.toEntity(schoolDTO);
        newEntity.setId(school.getId());
        schoolRepository.save(newEntity);
    }
}
