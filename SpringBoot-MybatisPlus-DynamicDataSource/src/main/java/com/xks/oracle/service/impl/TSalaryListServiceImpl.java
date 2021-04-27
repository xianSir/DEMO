package com.xks.oracle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xks.oracle.entity.TSalaryList;
import com.xks.oracle.mapper.TSalaryListMapper;
import com.xks.oracle.service.TSalaryListService;
import com.xks.dto.BaseQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class TSalaryListServiceImpl extends ServiceImpl<TSalaryListMapper, TSalaryList>
implements TSalaryListService {
    @Autowired
    TSalaryListMapper tSalaryListMapper;
    @Override
    public List<TSalaryList> getSalaryByBatch(BaseQuery query) {
        return tSalaryListMapper.selectByRemark4(query.getBatch());
    }
}




