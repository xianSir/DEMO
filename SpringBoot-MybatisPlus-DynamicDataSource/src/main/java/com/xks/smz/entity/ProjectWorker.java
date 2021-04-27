package com.xks.smz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 项目中工人信息
 * @TableName smz_project_worker
 */
@TableName(value ="smz_project_worker")
@Data
public class ProjectWorker implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 项目编号
     */
    private String projectCode;

    /**
     * 工人Id
     */
    private String workerId;

    /**
     * 所属企业组织机构代码
     */
    private String organizationCode;

    /**
     * 班组编号
     */
    private Integer teamSysno;

    /**
     * 证件类型
     */
    private String idcardType;

    /**
     * 证件编号
     */
    private String idcardNumber;

    /**
     * 当前工种
     */
    private String workTypeCode;

    /**
     * 手机号码
     */
    private String cellPhone;

    /**
     * 发卡时间
     */
    private Date issueCardtime;

    /**
     * 进场时间
     */
    private Date entryTime;

    /**
     * 退场时间
     */
    private Date exitTime;

    /**
     * 销卡时间
     */
    private Date completeCardtime;

    /**
     * 门禁卡号
     */
    private String cardNumber;

    /**
     * 卡的类型
            1=正式卡,
            3=临工卡(指的短期进入工地工作的卡，默认这类工人不会记入工资结算)
            
     */
    private String cardType;

    /**
     * 是否有劳动合同
     */
    private String hasContract;

    /**
     * 工人劳动合同编号
     */
    private String contractCode;

    /**
     * 工人住宿类型 1=Employe=职员 , 2=Worker=工人
            
     */
    private String workerAccommodationType;

    /**
     * 工人角色
     */
    private String workerRole;

    /**
     * 工资银行卡号
     */
    private String payRollbankCardNumber;

    /**
     * 工资银行名称
     */
    private String payRollbankName;

    /**
     * 有无购买工伤或意外伤害保险 (  0=No=无,1=Yes=有 )
     */
    private String hasBuyinsurance;

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
     * 项目Id
     */
    private String projectId;

    /**
     * 参见单位Id
     */
    private String organizationId;

    /**
     * 班组Id
     */
    private String teamId;

    /**
     * 删除标记
     */
    private Integer delFlag;

    /**
     * 合同id
     */
    private String contractId;

    /**
     * 职务
     */
    private String workerDuty;

    /**
     * 工号
     */
    private String numbr;

    /**
     * 韦根号
     */
    private String wiegand;

    /**
     * 人员进场(0 未进场 1 进场)
     */
    private Integer entryStatus;

    /**
     * 移除时间
     */
    private Date delDate;

    /**
     * 是否设置日工资 0 否 1 是
     */
    private Byte hasSetSettlement;

    /**
     * 是否同步合同 0 否 1 是
     */
    private Byte hasSyschContract;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}