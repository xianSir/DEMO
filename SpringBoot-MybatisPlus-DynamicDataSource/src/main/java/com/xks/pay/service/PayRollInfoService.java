package com.xks.pay.service;

import com.xks.pay.entity.PayRollInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface PayRollInfoService extends IService<PayRollInfo> {

    String  deleteSalaryByBatch(String batch);

}
