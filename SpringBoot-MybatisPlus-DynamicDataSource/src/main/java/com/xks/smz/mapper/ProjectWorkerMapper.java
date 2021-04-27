package com.xks.smz.mapper;
import com.xks.smz.entity.ProjectWorker;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity com.zxhz.maintain.smz.entity.ProjectWorker
 */
public interface ProjectWorkerMapper extends BaseMapper<ProjectWorker> {
    int updateWorkerRoleByWorkerId(@Param("workerRole") String workerRole, @Param("workerId") String workerId);

}




