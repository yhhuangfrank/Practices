package com.frank.mytest.test.designpattern.responsibilitychain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PurchaseRequest {

    private int type = 0; // 請求類型
    private float price = 0.0f;
    private int id = 0;
}
