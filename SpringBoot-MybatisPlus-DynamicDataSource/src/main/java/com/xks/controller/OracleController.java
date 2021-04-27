package com.xks.controller;

import com.xks.dto.BaseQuery;
import com.xks.oracle.entity.TSalaryList;
import com.xks.oracle.service.TSalaryListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xks
 * @date 2021-04-15
 */
@Api(tags = "oracle模块")
@RestController("/oracle模块")
public class OracleController {

    @Autowired
    TSalaryListService tSalaryListService;

    @ApiOperation(value = "查询工资单")
    @GetMapping("/getSalaryByBatch")
    public ResponseEntity<List<TSalaryList>> getSalaryByBatch(BaseQuery query){
       return ResponseEntity.ok(tSalaryListService.getSalaryByBatch(query));
    }
}
