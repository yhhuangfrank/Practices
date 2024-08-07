package com.frank.redis_distributed_lock2.controller;

import com.frank.redis_distributed_lock2.service.InventoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
@Api(tags = "庫存 controller")
public class InventoryController {

    private final InventoryService inventoryService;

    @ApiOperation("每觸發一次少一個庫存")
    @GetMapping("/sale")
    public String sale() {
        return inventoryService.sale();
    }
}
