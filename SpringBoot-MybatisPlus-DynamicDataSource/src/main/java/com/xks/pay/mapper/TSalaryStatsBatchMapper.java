package com.xks.pay.mapper;

import com.xks.pay.entity.TSalaryStatsBatch;

/**
 * @Entity com.zxhz.maintain.pay.entity.TSalaryStatsBatch
 */
public interface TSalaryStatsBatchMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TSalaryStatsBatch record);

    int insertSelective(TSalaryStatsBatch record);

    TSalaryStatsBatch selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TSalaryStatsBatch record);

    int updateByPrimaryKey(TSalaryStatsBatch record);

}




