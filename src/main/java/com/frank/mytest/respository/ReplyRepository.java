package com.frank.mytest.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository<Reply> extends JpaRepository<Reply, Integer> {
}