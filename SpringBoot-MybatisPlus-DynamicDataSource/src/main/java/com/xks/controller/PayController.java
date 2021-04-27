package com.xks.controller;

import com.xks.dto.BaseQuery;
import com.xks.pay.service.PayRollInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xks
 * @date 2021-04-15
 */
@Api(tags = "工资系统运维模块")
@RestController("/pay")
public class PayController {

    @Autowired
    PayRollInfoService payRollInfoService;

    @ApiOperation(value = "删除工资单")
    @GetMapping("/deleteSalaryByBatch")
    public ResponseEntity<String> deleteSalaryByBatch(BaseQuery query){
       return ResponseEntity.ok(payRollInfoService.deleteSalaryByBatch(query.getBatch()));
    }
}
