package com.xks.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;


/**
 * @author xks
 * @date 2021-04-25
 */
@Data
//@ExcelTarget("WorkerRole")
public class WorkerRole  implements Serializable {

    private static final long serialVersionUID = 490770267917756981L;
    @Excel(name="身份证号")
    String idCardNum;

    @Excel(name="工人角色")
    String workerRole;

}
