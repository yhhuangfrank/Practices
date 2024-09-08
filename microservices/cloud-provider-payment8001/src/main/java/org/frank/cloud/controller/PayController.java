package org.frank.cloud.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.frank.cloud.dto.PayDTO;
import org.frank.cloud.entity.Pay;
import org.frank.cloud.enums.ReturnCodeEnum;
import org.frank.cloud.response.ResultData;
import org.frank.cloud.service.PayService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/pay")
@Tag(name = "Payment module", description = "Pay CRUD")
public class PayController {

    private final PayService payService;

    private ModelMapper mapper = new ModelMapper();

    @PostMapping("/add")
    @Operation(summary = "新增", description = "新增支付流水紀錄方法")
    public ResultData<Pay> addPay(@RequestBody Pay pay) {
        log.info("request Object: {}", pay);
        return ResultData.success(payService.save(pay));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "刪除", description = "刪除支付流水紀錄方法")
    public ResultData<String> delPay(@PathVariable("id") Integer id) {
        payService.delete(id);
        return ResultData.success("delete success!");
    }

    @PutMapping("/update")
    @Operation(summary = "修改", description = "修改支付流水紀錄方法")
    public ResultData<Pay> updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = mapper.map(payDTO, Pay.class);
        return ResultData.success(payService.save(pay));
    }

    @GetMapping("/{id}")
    @Operation(summary = "查詢", description = "查詢單一支付流水紀錄方法")
    public ResultData<Pay> findById(@PathVariable("id") Integer id) {
        if (id <= 0) {
            throw new RuntimeException("id cannot less than 0");
        }
        // TEST - trigger time out
//        try {
//            TimeUnit.SECONDS.sleep(30);
//            return ResultData.success(payService.getById(id));
//        } catch (InterruptedException e) {
//            log.error("error !", e);
//            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
//        }
        return ResultData.success(payService.getById(id));
    }

    @GetMapping
    @Operation(summary = "查詢", description = "查詢所有支付流水紀錄方法")
    public ResultData<List<Pay>> findAll() {
        return ResultData.success(payService.getAll());
    }

    // 測試 Consul 上結合 bootstrap.yaml 的 key-value 配置
    @Value("${server.port}")
    private int port;

    @GetMapping("/config/info")
    public String getInfoOfConsul(@Value("${frank.info}") String info) {
        return info + ", port: " + port;
    }
}
