package com.xks.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.xks.dto.BaseQuery;
import com.xks.dto.WorkerRole;
import com.xks.smz.service.ProjectWorkerService;
import com.xks.smz.service.WorkerMasterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xks
 * @date 2021-04-15
 */
@Api(tags = "实名制系统运维模块")
@RestController("/smz")
@Slf4j
public class SmzController {

    @Autowired
    WorkerMasterService workerMasterService;
    @Autowired
    ProjectWorkerService projectWorkerService;

    @ApiOperation(value = "修改工人角色")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "file", value = "文件流对象", required = true,dataType = "MultipartFile",allowMultiple = true)}
    )
    @PostMapping(value = "/updateWorkerRole")
    public ResponseEntity< List<WorkerRole>> updateWorkerRole(MultipartFile file, BaseQuery query) throws Exception {
            InputStream inputStream = file.getInputStream();
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        List<WorkerRole> list = ExcelImportUtil.importExcel(inputStream,WorkerRole.class, params);
        log.info("修改工人记录"+ JSON.toJSONString(list));
        List<WorkerRole> edit = new ArrayList<>();
        List<WorkerRole> fail = new ArrayList<>();
        list.forEach(e->{
            if(StringUtils.isNotBlank(e.getIdCardNum())){
                //            建筑工人20 管理人员10
                switch (e.getWorkerRole()){
                    case "建筑工人":
                        e.setWorkerRole("20");
                        edit.add(e);
                        break;
                    case "管理人员":
                        e.setWorkerRole("10");
                        edit.add(e);
                        break;
                    default:
                        fail.add(e);
                        break;
                }
            }
        });
        List<WorkerRole> editFailList=workerMasterService.updateWorkerRole(edit);
        fail.addAll(editFailList);
        return ResponseEntity.ok(fail);
    }
}
