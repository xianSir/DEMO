package com.xks.pay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xks.pay.entity.PayRollInfo;
import com.xks.pay.service.PayRollInfoService;
import com.xks.pay.mapper.PayRollInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class PayRollInfoServiceImpl extends ServiceImpl<PayRollInfoMapper, PayRollInfo> implements PayRollInfoService{


    @Autowired
    PayRollInfoMapper payRollInfoMapper;

    @Override
    public String deleteSalaryByBatch(String batch) {
        return  payRollInfoMapper.deleteSalaryByBatch(batch);
    }
}




