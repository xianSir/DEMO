package com.xks.oracle.service;

import com.xks.oracle.entity.TSalaryList;
import com.xks.dto.BaseQuery;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface TSalaryListService extends IService<TSalaryList> {

    List<TSalaryList> getSalaryByBatch(BaseQuery query);
}
