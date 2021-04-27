package com.xks.pay.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 工资单表
 * @TableName pay_roll_info
 */
@TableName(value ="pay_roll_info")
@Data
public class PayRollInfo implements Serializable {
    /**
     * 主键Id
     */
    @TableId
    private String id;

    /**
     * 工资单编号
     */
    private String payRollCode;

    /**
     * 工单名称
     */
    private String payRollName;

    /**
     * 
     */
    private String projectCode;

    /**
     * 
     */
    private Integer organizationCode;

    /**
     * 班组编号
     */
    private Integer teamSysno;

    /**
     * 发放工资的年月 ( YYYY-MM )
     */
    private Date payMonth;

    /**
     * 处理状态(0-待确认,1-已确认,2-银行处理中,3-银行已处理)
     */
    private String status;

    /**
     * 建筑活动工程编码（总包）
     */
    private String contractorProjectCode;

    /**
     * 建筑活动工程编码（分包）
     */
    private String subContractorProjectCode;

    /**
     * 附件 存放工资单附件路径(上传附件时有对应的附件上传接口)
     */
    private String attachFiles;

    /**
     * 项目ID
     */
    private String projectId;

    /**
     * 建筑活动工程编码（总包）ID (单位ID)
     */
    private String contractorProjectId;

    /**
     * 建筑活动工程编码（分包）ID(参建单位ID)
     */
    private String subContractorProjectId;

    /**
     * 班组ID
     */
    private String teamId;

    /**
     * 发放工资的时间( YYYY-MM-dd HH-mm-SS )
     */
    private Date payDate;

    /**
     * 发放开始
     */
    private Date payStartDate;

    /**
     * 发放结束
     */
    private Date payEndDate;

    /**
     * 
     */
    private String createUser;

    /**
     * 
     */
    private Date createDate;

    /**
     * 
     */
    private String updateUser;

    /**
     * 
     */
    private Date updateDate;

    /**
     * 参建单位id
     */
    private String organizationId;

    /**
     * 银行方处理状态(0-待确认,1-已确认,2-待银行下载,3-银行下载中,4-银行已下载,5-银行批量中,6-已撤回,7-作废,8-发放成功,9-发放失败,10-部分失败)
     */
    private String bankStatus;

    /**
     * 银行批次返回状态
     */
    private String statNo;

    /**
     * 银行批次状态信息
     */
    private String statMsg;

    /**
     * 发放方式（0-直接发放,1-线上代发）
     */
    private Integer payType;

    /**
     * 发放银行类型
     */
    private String bankType;

    /**
     * 发放银行名称
     */
    private String bankName;

    /**
     * 银行卡号
     */
    private String bankNumber;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 经办人
     */
    private String jbr;

    /**
     * 上报人员
     */
    private String upuser;

    /**
     * 
     */
    private String batchNumber;

    /**
     * 回执单
     */
    private String receipt;

    /**
     * 是否补发（0-否；1-是）
     */
    private Byte isBackPay;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}