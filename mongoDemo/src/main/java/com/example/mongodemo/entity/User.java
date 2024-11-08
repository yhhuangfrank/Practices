package com.example.mongodemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("User")
public class User {

    @Id
    private String id;

    private String name;

    private Integer age;

    private String email;

    private String createDate;
}
