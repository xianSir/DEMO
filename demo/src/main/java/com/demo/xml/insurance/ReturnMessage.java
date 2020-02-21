package com.demo.xml.insurance;

import java.io.Serializable;
import java.util.List;

/**
 * @author xks
 * @date 2020-02-11
 */
public class ReturnMessage implements Serializable {

    private static final long serialVersionUID = -5455528187687684353L;
    /**
     * 成功返回标注	1	0为成功
     */
    private String errflag;
    /**
     * 200	Errflag为1时返回错误信息
     */
    private String errtext;
    /**
     * 反馈人员增员结果
     */
    private List<DsInfo> ds_info;

    public String getErrflag() {
        return errflag;
    }

    public void setErrflag(String errflag) {
        this.errflag = errflag;
    }

    public String getErrtext() {
        return errtext;
    }

    public void setErrtext(String errtext) {
        this.errtext = errtext;
    }

    public List<DsInfo> getDs_info() {
        return ds_info;
    }

    public void setDs_info(List<DsInfo> ds_info) {
        this.ds_info = ds_info;
    }
}
