package com.demo.xml.insurance;


/**
 * @author xks
 * @date 2020-02-13
 */
public class InsuranceMQModel {

    private Methed methed;

    private String insuranceNum;

    private String oldInsuranceNum;

    private String projectId;

    private String workerId;

    private String projectWorkerId;

    public enum Methed {
        // 初始化(绑定),修改编号,添加任务
        INIT, CHANGE, ADD;
    }

    public InsuranceMQModel() {
    }

    public InsuranceMQModel(Methed methed, String projectId, String workerId) {
        this.methed = methed;
        this.projectId = projectId;
        this.workerId = workerId;
    }

    public InsuranceMQModel(Methed methed, String projectWorkerId) {
        this.methed = methed;
        this.projectWorkerId = projectWorkerId;
    }

    public Methed getMethed() {
        return methed;
    }

    public void setMethed(Methed methed) {
        this.methed = methed;
    }

    public String getInsuranceNum() {
        return insuranceNum;
    }

    public void setInsuranceNum(String insuranceNum) {
        this.insuranceNum = insuranceNum;
    }

    public String getOldInsuranceNum() {
        return oldInsuranceNum;
    }

    public void setOldInsuranceNum(String oldInsuranceNum) {
        this.oldInsuranceNum = oldInsuranceNum;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getProjectWorkerId() {
        return projectWorkerId;
    }

    public void setProjectWorkerId(String projectWorkerId) {
        this.projectWorkerId = projectWorkerId;
    }
}
