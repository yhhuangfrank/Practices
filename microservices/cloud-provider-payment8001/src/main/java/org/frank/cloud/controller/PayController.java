package org.frank.cloud.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.frank.cloud.dto.PayDTO;
import org.frank.cloud.entity.Pay;
import org.frank.cloud.service.PayService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/pay")
@Tag(name = "支付微服務 module", description = "支付 CRUD")
public class PayController {

    private final PayService payService;

    private ModelMapper mapper = new ModelMapper();

    @PostMapping("/add")
    @Operation(summary = "新增", description = "新增支付流水紀錄方法")
    public String addPay(@RequestBody Pay pay) {
        log.info("request Object: {}", pay);
        payService.add(pay);
        return "add success!";
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "刪除", description = "刪除支付流水紀錄方法")
    public String delPay(@PathVariable("id") Integer id) {
        payService.delete(id);
        return "delete success!";
    }

    @PutMapping("/update")
    @Operation(summary = "修改", description = "修改支付流水紀錄方法")
    public String updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = mapper.map(payDTO, Pay.class);
        payService.update(pay);
        return "update success!";
    }

    @GetMapping("/{id}")
    @Operation(summary = "查詢", description = "查詢單一支付流水紀錄方法")
    public Pay findById(@PathVariable("id") Integer id) {
        return payService.getById(id);
    }

    @GetMapping
    @Operation(summary = "查詢", description = "查詢所有支付流水紀錄方法")
    public List<Pay> findAll() {
        return payService.getAll();
    }
}
