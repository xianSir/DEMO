package com.demo.xml.insurance;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.io.Serializable;

/**
 * @author xks
 * @date 2020-02-11
 */
@XStreamAlias("r")
public class DsEmp implements Serializable {

    private static final long serialVersionUID = 6717035399514114352L;
    /**
     * 姓名
     */
    @XStreamAlias("rsxtid")
    @XStreamAsAttribute
    private String xm;
    /**
     * 身份证号码
     */
    @XStreamAlias("rsxtid")
    @XStreamAsAttribute
    private String sfzhm;
    /**
     * 出生日期
     */
    @XStreamAlias("rsxtid")
    @XStreamAsAttribute
    private String csrq;
    /**
     * 性别 1男	2女
     */
    @XStreamAlias("rsxtid")
    @XStreamAsAttribute
    private String xb;
    /**
     * 项目起始日期
     */
    @XStreamAlias("rsxtid")
    @XStreamAsAttribute
    private String qsrq;
    /**
     * 项目终止日期
     */
    @XStreamAlias("rsxtid")
    @XStreamAsAttribute
    private String zzrq;
    /**
     * 家庭住址
     */
    @XStreamAlias("rsxtid")
    @XStreamAsAttribute
    private String jtzz;
    /**
     * 备注
     */
    @XStreamAlias("rsxtid")
    @XStreamAsAttribute
    private String bz;

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getSfzhm() {
        return sfzhm;
    }

    public void setSfzhm(String sfzhm) {
        this.sfzhm = sfzhm;
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getQsrq() {
        return qsrq;
    }

    public void setQsrq(String qsrq) {
        this.qsrq = qsrq;
    }

    public String getZzrq() {
        return zzrq;
    }

    public void setZzrq(String zzrq) {
        this.zzrq = zzrq;
    }

    public String getJtzz() {
        return jtzz;
    }

    public void setJtzz(String jtzz) {
        this.jtzz = jtzz;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
}
