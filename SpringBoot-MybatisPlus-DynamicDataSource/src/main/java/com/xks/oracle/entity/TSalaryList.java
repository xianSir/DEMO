package com.xks.oracle.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 工资表
 * @TableName T_SALARY_LIST
 */
@TableName(value ="T_SALARY_LIST")
@Data
public class TSalaryList implements Serializable {
    /**
     * 
     */
    @TableId
    private String salaryid;

    /**
     * 单位编号
     */
    private String companyid;

    /**
     * 录入时间
     */
    private Date inputdate;

    /**
     * 经办人
     */
    private String jbr;

    /**
     * 备注4(工资表生成批次号)
     */
    private String remark4;

    /**
     * startmonth
     */
    private String startmonth;

    /**
     * startyear
     */
    private String startyear;

    /**
     * 状态
     */
    private String state;

    /**
     * 上报人员
     */
    private String upuserid;

    /**
     * 录入人员
     */
    private String userid;

    /**
     * 
     */
    private String statusCode;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 银行是否发放
     */
    private String bankStatus;

    /**
     * 银行发放总额
     */
    private BigDecimal bankactpay;

    /**
     * 应发工资总额
     */
    private BigDecimal shouldpay;

    /**
     * 
     */
    private String projectid;

    /**
     * 
     */
    private String lbcompanyId;

    /**
     * 审核时间
     */
    private Date createTime;

    /**
     * 
     */
    private String delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}