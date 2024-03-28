package com.frank.mytest.service;

import com.frank.mytest.entity.Comment;
import com.frank.mytest.entity.Reply;
import com.frank.mytest.respository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CommentService {

    @Autowired
    private EntityManager em;

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public void cascadeInsert() {
        for (int i = 1; i <= 5; i++) {
            Comment comment = new Comment().setContent("comment - " + i);
            List<Reply> replies = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Reply reply = new Reply().setContent("reply - " + j).setComment(comment);
                replies.add(reply);
            }
            comment.setReplies(replies);
            commentRepository.save(comment);
        }
    }

    @Transactional
    public void cascadeDeleteById(int id) {
        commentRepository.deleteById(id);
    }

    public Optional<Comment> cascadeSelectById(int id) {
        return commentRepository.findById(id);
    }

    @Transactional
    public void batchInsert(List<Comment> comments) {
        commentRepository.saveAll(comments);
    }

    public List<Comment> findAll(){
        return em.createNamedQuery("Comment.findAll", Comment.class).getResultList();
    }

    @Transactional
    public void deleteByList(List<Comment> comments) {
        for (Comment c : comments) {
//            em.remove(em.merge(c));
            em.remove(c);
        }
        em.flush();
    }

    @Transactional
    public void cascadeDelete(List<Comment> comments) {
        comments.forEach(comment -> {
            System.out.println("current comment is : " + comment);
            if (comment.getReplies() != null && !CollectionUtils.isEmpty(comment.getReplies())) {
                comment.getReplies().forEach(reply -> {
                    System.out.println("current reply is : " + reply);
                    em.remove(reply);
                });
            }
            em.remove(comment);
        });
//        em.flush();
    }

    @Transactional
    public void testFlushInSaveAndFind() {
        Comment comment = new Comment().setContent("aa");
        System.out.println("before persist...");
        em.persist(comment);
        System.out.println("after persist...");
        TypedQuery<Comment> query = em.createQuery("SELECT c FROM Comment c WHERE c.id = :id", Comment.class);
        query.setParameter("id", comment.getId());
//        int a = 10 / 0; // 模擬異常發生
        Comment DBcomment = query.getSingleResult();
    }

    public List<Comment> findByIds(List<Integer> idList) {
        return em.createNamedQuery("Comment.findByIds", Comment.class).setParameter("ids", idList).getResultList();
    }

    public List<Comment> findByContent(String content) {
        return em.createNamedQuery("Comment.findByContent", Comment.class).setParameter("content", content).getResultList();
    }

    @Transactional
    public void deleteByIds(List<Integer> ids) {
        em.createNamedQuery("Comment.DeleteByIds").setParameter("ids", ids).executeUpdate();
    }

    public Comment findById(Integer id) {
        return em.find(Comment.class, id);
    }
}
