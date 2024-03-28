package com.frank.mytest.rpc;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RpcController {

    @PostMapping("/ui-builder/write")
    public ResponseEntity<RpcResponse> testWrite() {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setControlId(123);
        rpcResponse.setDeviceId(123);
        rpcResponse.setValue(1000);
        rpcResponse.setErrMsg(null);
        rpcResponse.setAgentId("123");
        rpcResponse.setDeviceName("123");
        rpcResponse.setPointName("pointName1");

        return ResponseEntity.ok().body(rpcResponse);
    }

}
