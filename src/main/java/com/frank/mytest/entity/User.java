package com.frank.mytest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

//    @ManyToOne
//    @JoinColumn(name = "dept_id", referencedColumnName = "id", insertable = false, updatable = false)
//    private Dept dept;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
