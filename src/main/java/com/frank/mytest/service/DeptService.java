package com.frank.mytest.service;

import com.frank.mytest.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;

@Service
public class DeptService {

    @Autowired
    private EntityManager em;

    public Dept findById(Integer id) {
        return em.find(Dept.class, id);
    }
}
