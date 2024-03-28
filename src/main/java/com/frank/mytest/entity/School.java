package com.frank.mytest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "school")
//@NamedQuery(name = "School.findStudents", query = "SELECT s FROM School s LEFT JOIN FETCH s.students WHERE s.id = :id")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id", nullable = false)
    private Integer id;

    @Column(name = "school_name", nullable = false, length = 20)
    private String schoolName;

    @OneToMany
    @JoinColumn(name = "school_id", referencedColumnName = "school_id", insertable = false, updatable = false)
    @ToString.Exclude
    private List<Student> students;

}