package com.frank.mytest.service;

import com.frank.mytest.entity.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;

@Service
@Transactional(readOnly = true)
public class ReplyService {

    @Autowired
    EntityManager em;

    public Reply findById(Integer id) {
        return em.find(Reply.class, id);
    }
}
