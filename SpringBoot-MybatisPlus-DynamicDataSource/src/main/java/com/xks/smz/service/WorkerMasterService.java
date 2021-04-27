package com.xks.smz.service;

import com.xks.dto.WorkerRole;
import com.xks.smz.entity.WorkerMaster;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface WorkerMasterService extends IService<WorkerMaster> {

    List<WorkerRole> updateWorkerRole(List<WorkerRole> edit);
}
