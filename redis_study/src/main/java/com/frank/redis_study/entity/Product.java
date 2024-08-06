package com.frank.redis_study.entity;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "活動商品")
@Builder
public class Product {

    // 產品 ID
    private long productId;

    // 產品名稱
    private String name;

    // 產品價格
    private Integer price;

    // 產品 描述
    private String description;
}
