package com.frank.mytest.service;

import com.frank.mytest.entity.Comment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Test
    public void testCascadeInsert() {
        commentService.cascadeInsert();
    }

    @Test
    public void testCascadeDelete() {
        commentService.cascadeDeleteById(524);
    }

    @Test
    @Transactional
    public void testCascadeSelect() {
        Comment comment = commentService.cascadeSelectById(416).orElse(null);
        if (comment != null && !CollectionUtils.isEmpty(comment.getReplies())) {
            log.info(comment.getReplies().toString());
        } else {
            log.warn("there are no replies in this comment");
        }
    }

    @Test
    public void testBatchInsert() {
        ArrayList<Comment> comments = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Comment comment = new Comment();
            comment.setContent("" + i);
            comments.add(comment);
        }
        commentService.batchInsert(comments);
    }

    @Test
    @Transactional
    public void testDeleteInsert() {
        List<Comment> commentList = commentService.findAll();
        commentService.deleteByList(commentList);
    }

    @Test
    public void testFlushInSaveAndFind() {
        commentService.testFlushInSaveAndFind();
    }

    @Test
    @Transactional
    public void cascadeDeleteTest() {
//        commentService.cascadeDelete(commentService.findByIds(Arrays.asList(416,417)));
        commentService.cascadeDelete(commentService.findByContent("%2%"));
    }

}