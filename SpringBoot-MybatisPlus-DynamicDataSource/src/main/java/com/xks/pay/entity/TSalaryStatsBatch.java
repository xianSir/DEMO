package com.xks.pay.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 项目工资统计表（按批次）
 * @TableName T_SALARY_STATS_BATCH
 */
@TableName(value ="T_SALARY_STATS_BATCH")
@Data
public class TSalaryStatsBatch implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 项目编号
     */
    private String projectId;

    /**
     * 工资表表id(多个id以逗号分隔）
     */
    private String salaryIds;

    /**
     * 年度
     */
    private String year;

    /**
     * 月份
     */
    private String month;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 批次说明
     */
    private String batchName;

    /**
     * 应发金额
     */
    private Long shouldPay;

    /**
     * 银行实发
     */
    private Long bankActPay;

    /**
     * 工资表状态
     */
    private String status;

    /**
     * 发放状态（YES：已发,NO：未发）
     */
    private String payStatus;

    /**
     * 是否是生成的工资表(0：否,1:是)
     */
    private String isCread;

    /**
     * 
     */
    private Long isDeleted;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private String delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}