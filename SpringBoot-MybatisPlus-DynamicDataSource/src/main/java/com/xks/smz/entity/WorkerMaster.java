package com.xks.smz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 工人实名基础信息
 * @TableName smz_worker_master
 */
@TableName(value ="smz_worker_master")
@Data
public class WorkerMaster implements Serializable {
    /**
     * 主键Id
     */
    @TableId
    private String id;

    /**
     * 工人姓名
     */
    private String workerName;

    /**
     * 证件类型
     */
    private String idcardType;

    /**
     * 证件编号
     */
    private String idcardNumber;

    /**
     *  工人性别 0=男,1=女
            
     */
    private String gender;

    /**
     * 民族 身份证上获取的民族信息，如：汉，回，藏
     */
    private String nation;

    /**
     * 出生日期 身份证上获取的出生日期，格式：1990-04-08
     */
    private Date birthday;

    /**
     * 籍贯
     */
    private String birthPlaceCode;

    /**
     * 身份证上获取的籍贯地址
     */
    private String address;

    /**
     * 政治面貌:
            0=中共党员,
            1=中共预备党员,
            2=共青团员,
            3=群众
            
     */
    private String politicsType;

    /**
     * 所属参建单位
     */
    private String organizationCode;

    /**
     * 工人角色1=Employe=职员2=Worker=工人
            1=Employe=职员
            2=Worker=工人
            
     */
    private Byte workerRole;

    /**
     * 考勤类型1=正式工,2=临时工(指的短期进入工地工作的卡，默认这类工人不会记入工资结算)
            
     */
    private Byte cardCode;

    /**
     * 考勤卡号
     */
    private String cardNumber;

    /**
     * 是否加入工会 (  0=未加入,1=已加入 )
     */
    private String isJoined;

    /**
     * 加入工会时间
     */
    private Date joinedTime;

    /**
     * 手机号码
     */
    private String cellPhone;

    /**
     * 文化程度 (  0=小学,1=初中,2=高中,3=中专,4=大专,5=本科,6=硕士,7=博士,8=文盲) 
     */
    private String cultureLevelType;

    /**
     * 是否有重大病史 (  0=无,1=有 ) 
     */
    private String hasBadMedicalHistory;

    /**
     * 紧急联系人姓名
     */
    private String urgentContractName;

    /**
     * 紧急联系人联系电话
     */
    private String urgentContractCellphone;

    /**
     * 当前工种
     */
    private String workTypeCode;

    /**
     * 开始工作日期
     */
    private Date workDate;

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
     * 审核状态(0 草稿，1待审核，2审核通过，3 审核未通过)
     */
    private String approvalStatus;

    /**
     * 0-未注册，1-人脸，2-虹膜，3-指纹，4-人脸 虹膜，5-人脸 指纹，6虹膜 指纹，7 人脸 虹膜 指纹
     */
    private Integer registerType;

    /**
     * 工号
     */
    private String numbr;

    /**
     * 韦根号
     */
    private String wiegand;

    /**
     * 顶级单位（客户）
     */
    private String orgId;

    /**
     * 所属单位
     */
    private String deptId;

    /**
     * 人员进场(0 未进场 1 进场)
     */
    private Integer entryStatus;

    /**
     * 职务
     */
    private String post;

    /**
     * 工人住宿类型
     */
    private Integer workerAccommodationType;

    /**
     * 有无购买工伤或意外伤害保险
     */
    private Integer hasBuyinsurance;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 登录用户名
     */
    private String username;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 发证机关
     */
    private String grantOrg;

    /**
     * 身份证反面照
     */
    private String negativeIdcardimageUrl;

    /**
     * 证件有效期开始日期
     */
    private Date startDate;

    /**
     * 证件有效期结束日期
     */
    private Date expiryDate;

    /**
     * 婚姻状况(01=未婚,02=已婚,03=离异,04=丧偶)
     */
    private String maritalStatus;

    /**
     * 学历(1=博士后,2=博士,3=研究生,4=硕士,5=本科,6=大专,7=中专,8=高中,9=专科以下学历,99=其他)
     */
    private Integer eduLevel;

    /**
     * 学位(1=博士后学位,2=博士学位,3=硕士学位,4=学士学位,5=无学位,99=其他)
     */
    private Integer degree;

    /**
     * 特长
     */
    private String specialty;

    /**
     * 头像
     */
    private byte[] headImage;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}