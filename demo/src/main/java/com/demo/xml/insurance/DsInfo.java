package com.demo.xml.insurance;

import java.io.Serializable;

/**
 * @author xks
 * @date 2020-02-11
 */
public class DsInfo implements Serializable {

    private static final long serialVersionUID = -1381332104295850440L;

    /**
     * 身份证号码
     */
    private String sfhzhm;
    /**
     * 姓名
     */
    private String xm;
    /**
     * 成功标志
     */
     private String shzt;
    /**
     * 结果说明
     */
    private String shsm;

    public String getSfhzhm() {
        return sfhzhm;
    }

    public void setSfhzhm(String sfhzhm) {
        this.sfhzhm = sfhzhm;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getShzt() {
        return shzt;
    }

    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

    public String getShsm() {
        return shsm;
    }

    public void setShsm(String shsm) {
        this.shsm = shsm;
    }
}
