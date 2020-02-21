package com.demo.xml.insurance;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.List;

/**
 * @author xks
 * @date 2020-02-11
 */
@XStreamAlias("p")
public class DataModel implements Serializable {

    private static final long serialVersionUID = -1973818764018150572L;
    /**
     * 项目编号
     */
    @XStreamAlias("xmbh")
    //@XStreamAsAttribute
    private String xmbh;
    /**
     * 人社系统id
     */
    @XStreamAlias("rsxtid")
   // @XStreamAsAttribute
    private String rsxtid;
    /**
     * 人员信息集合
     */
    @XStreamAlias("r")
    //@XStreamOmitField
    //@XStreamImplicit
    private List<DsEmp> ds_emp;



    public String getXmbh() {
        return xmbh;
    }

    public void setXmbh(String xmbh) {
        this.xmbh = xmbh;
    }

    public String getRsxtid() {
        return rsxtid;
    }

    public void setRsxtid(String rsxtid) {
        this.rsxtid = rsxtid;
    }

    public List<DsEmp> getDs_emp() {
        return ds_emp;
    }

    public void setDs_emp(List<DsEmp> ds_emp) {
        this.ds_emp = ds_emp;
    }
}
