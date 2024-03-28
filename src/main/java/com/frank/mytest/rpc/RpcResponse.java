package com.frank.mytest.rpc;

import lombok.Data;

@Data
public class RpcResponse {

    private String deviceName;


    private String pointName;


    private Integer value;


    private Integer deviceId;


    private String agentId;


    private Integer controlId; // 此為 device_tag_control table 的 PK


    private String errMsg;
}
