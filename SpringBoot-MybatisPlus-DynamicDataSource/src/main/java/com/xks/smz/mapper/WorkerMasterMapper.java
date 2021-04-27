package com.xks.smz.mapper;

import com.xks.smz.entity.WorkerMaster;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity com.zxhz.maintain.smz.entity.WorkerMaster
 */
public interface WorkerMasterMapper extends BaseMapper<WorkerMaster> {
    WorkerMaster selectByIdcardNumber(@Param("idcardNumber") String idcardNumber);

}




