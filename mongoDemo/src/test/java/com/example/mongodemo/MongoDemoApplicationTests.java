package com.example.mongodemo;

import com.example.mongodemo.entity.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.regex.Pattern;

@SpringBootTest
class MongoDemoApplicationTests {

    @Autowired
    public MongoTemplate mongoTemplate;

    @Test
    void insert() {
        User user = User.builder()
                .name("frank")
                .age(21)
                .email("123@gmail.com")
                .build();
        User inserted = mongoTemplate.insert(user);
        System.out.println(inserted);
    }

    @Test
    void findAll() {
        List<User> users = mongoTemplate.findAll(User.class);
        System.out.println(users);
    }

    @Test
    void findById() {
        User user = mongoTemplate.findById("672dc4d3728a5408456851b8", User.class);
        System.out.println(user);
    }

    @Test
    void findByCondition() {
        Query query = new Query(
                Criteria.where("name")
                        .is("test")
                        .and("age")
                        .is(20)
        );
        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println(users);
    }

    // 模糊查詢
    @Test
    void findByRegex() {
        Query query = new Query(
            Criteria.where("email")
                    .regex(Pattern.compile("^.*gmail.*$", Pattern.CASE_INSENSITIVE))
        );
        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println(users);
    }

    // 分頁查詢
    @Test
    void findByPage() {
        int page = 1;
        int size = 1;
        Query query = new Query(
                Criteria.where("email")
                        .regex(Pattern.compile("^.*gmail.*$", Pattern.CASE_INSENSITIVE))
        );
        long count = mongoTemplate.count(query, User.class);
        List<User> users = mongoTemplate.find(
                query.skip((page - 1) * size).limit(size),
                User.class
        );
        System.out.println(count);
        System.out.println(users);
    }

    // 修改
    @Test
    void update() {
        String id = "672dc4d3728a5408456851b8";

        User user = mongoTemplate.findById(id, User.class);
        Query query = new Query(
                Criteria.where("_id").is(user.getId())
        );
        Update update = new Update();
        update.set("name", "frank1");
        update.set("age", 28);
        update.set("email", "456@email.com");
        UpdateResult updateResult = mongoTemplate.upsert(query, update, User.class);
        System.out.println(updateResult.getModifiedCount());
    }

    // 刪除
    @Test
    void delete() {
        Query query = new Query(Criteria.where("_id").is("672dc4d3728a5408456851b8"));
        DeleteResult deleteResult = mongoTemplate.remove(query, User.class);
        System.out.println(deleteResult.getDeletedCount());
    }
}
