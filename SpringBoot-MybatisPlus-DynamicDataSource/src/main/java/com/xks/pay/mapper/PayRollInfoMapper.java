package com.xks.pay.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.xks.pay.entity.PayRollInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Entity com.zxhz.maintain.pay.entity.PayRollInfo
 */
@DS("pay")
public interface PayRollInfoMapper extends BaseMapper<PayRollInfo> {

    String deleteSalaryByBatch(@Param("batch") String batch);
}




