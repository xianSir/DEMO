package com.xks.smz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xks.dto.WorkerRole;
import com.xks.smz.service.WorkerMasterService;
import com.xks.smz.entity.WorkerMaster;
import com.xks.smz.mapper.ProjectWorkerMapper;
import com.xks.smz.mapper.WorkerMasterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class WorkerMasterServiceImpl extends ServiceImpl<WorkerMasterMapper, WorkerMaster>
implements WorkerMasterService {
    @Autowired
    WorkerMasterMapper workerMasterMapper;
    @Autowired
    ProjectWorkerMapper projectWorkerMapper;

    @Override
    public List<WorkerRole> updateWorkerRole(List<WorkerRole> edit) {
        List<WorkerRole> editFailList=new ArrayList<>();
        edit.forEach(e->{
            WorkerMaster workerMaster = workerMasterMapper.selectByIdcardNumber(e.getIdCardNum());
            if(workerMaster!=null){
                String workerRole = e.getWorkerRole();
                workerMaster.setWorkerRole(Byte.valueOf(workerRole));
                workerMasterMapper.updateById(workerMaster);
                projectWorkerMapper.updateWorkerRoleByWorkerId(workerRole,workerMaster.getId());
            }else {
                editFailList.add(e);
            }
        });
        return editFailList;
    }
}




