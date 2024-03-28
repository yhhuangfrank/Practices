package com.frank.mytest.service;

import com.frank.mytest.entity.User;
import com.frank.mytest.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    public void createUser(int id, String name) {
        System.out.println("before saving...");
        User newUser = userRepository.save(new User(id, name));
        System.out.println("after saving");

        System.out.println(newUser);
    }

    @Transactional
    public void createUserByEm(int id, String name) {
        System.out.println("before call save");
        User u = new User(id, name);
        em.persist(u);
        em.flush(); // 強制將為儲存的變動更新到DB， 此處執行 insert
        System.out.println("after call save");
        u.setName("bb");
        System.out.println("user is " + u);
        em.refresh(u); // 確保目前的 entity 物件與 DB 資料是一致的
        System.out.println("user is " + u);
    }

    public void testUser(int id) {
//        User user = userRepository.findById(id).get();
        User user = new User();
        System.out.println("before set...");
        user.setId(2);
        user.setName("Jack");
        userRepository.save(user);
        System.out.println("after set...");

//        userRepository.flush();
    }

    public void batchInsert() {
        userRepository.deleteAllInBatch();
        List<Long> m1 = new ArrayList<>();
        List<Long> m2 = new ArrayList<>();
        for (int k = 0; k < 10; k++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 1000; i ++) {
                User u = new User();
                u.setId(i);
                userRepository.save(u);
            }
            long end = System.currentTimeMillis();
            m1.add(end - start);

            userRepository.deleteAllInBatch();

            start = System.currentTimeMillis();
            ArrayList<User> list  = new ArrayList<>();
            for (int j = 0; j < 1000; j++) {
                User u = new User();
                u.setId(j);
                list.add(u);
            }
            userRepository.saveAll(list);
            end = System.currentTimeMillis();
            m2.add(end - start);
        }

        Long r1 = m1.stream().reduce(0L, Long::sum);
        Long r2 = m2.stream().reduce(0L, Long::sum);
        System.out.println(m1);
        System.out.println(m2);
        System.out.println("avg of m1 is " + r1 / m1.size());
        System.out.println("avg of m2 is " + r2 / m2.size());
    }

    @Transactional
    public void insertTestByEm() {
        userRepository.deleteAllInBatch();
        ArrayList<Long> list1 = new ArrayList<>();
        ArrayList<Long> list2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userRepository.deleteAllInBatch();

            System.out.println("=============== Current loop : " + i);
            long start = System.currentTimeMillis();
            insertOneByOne();
            long end = System.currentTimeMillis();
            list1.add(end - start);

            userRepository.deleteAllInBatch();

            start = System.currentTimeMillis();
            insertInBatch();
            end = System.currentTimeMillis();
            list2.add(end - start);
        }

        Long r1 = list1.stream().reduce(0L, Long::sum);
        Long r2 = list2.stream().reduce(0L, Long::sum);
        System.out.println(list1);
        System.out.println(list2);
        System.out.println("avg of list1 is " + r1 / list1.size());
        System.out.println("avg of list2 is " + r2 / list2.size());
    }

    private void insertInBatch() {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            User user = new User();
            user.setId(i);
            users.add(user);
        }

        for (User user : users) {
            em.persist(user);
//            em.flush();
//            em.refresh(user);
        }
        em.flush();
        em.clear();
    }

    private void insertOneByOne() {
        for (int i = 0; i < 1000; i++) {
            User user = new User();
            user.setId(i);
            em.persist(user);
            em.flush();
//            em.refresh(user);
        }
        em.clear();
    }

    public void searchUser(int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
//            System.out.println(user.getDept());;
        }
        System.out.println(user);
    }

    public User findById(Integer id) {
        return em.find(User.class, id);
    }

}
