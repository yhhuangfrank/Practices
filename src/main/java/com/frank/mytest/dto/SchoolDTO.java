package com.frank.mytest.dto;

import com.frank.mytest.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDTO implements Serializable {
    private Integer id;
    private String schoolName;
    private List<Student> students;
}