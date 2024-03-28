package com.frank.mytest.dto;

import lombok.Data;

@Data
public class TestResponse {

    private int id;
    private String title;
    private String body;
    private int userId;
}
