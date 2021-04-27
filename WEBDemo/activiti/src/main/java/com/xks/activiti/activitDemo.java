package com.xks.activiti;

import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

import java.text.ParseException;

/**
 * @author xks
 * @date 2019-11-04
 */
public class activitDemo {
    public static void main(String[] args) throws ParseException {
        ProcessEngine processEngine = EngineConfigurtion();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        //创建 添加流程定义 并部署
        //Deployment deployment = CreateProcess(repositoryService);
        //查询流程资源
//        ProcessDefinition processDefinition = findProcess(repositoryService, deployment);
       // startProcess(processEngine, processDefinition);
        complateTask(processEngine,"10002");

    }

    private static ProcessEngine EngineConfigurtion() {
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://127.0.0.1:3306/activiti?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=GMT")
                .setJdbcUsername("root")
                .setJdbcPassword("123456")
                .setJdbcDriver("com.mysql.jdbc.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine processEngine = cfg.buildProcessEngine();
        String pName = processEngine.getName();
        String ver = ProcessEngine.VERSION;
        System.out.println("ProcessEngine [" + pName + "] Version: [" + ver + "]");
        return processEngine;
    }

    /**
     * 创建任务模板
     * @param repositoryService
     * @return
     */
    private static Deployment CreateProcess(RepositoryService repositoryService) {
        return repositoryService
                    .createDeployment()
                    .addClasspathResource("processes/user.bpmn")
                    .name("请假流程")
                    .deploy();
    }

    /**
     * 查询任务
     * @param repositoryService
     * @param deployment
     * @return
     */
    private static ProcessDefinition findProcess(RepositoryService repositoryService, Deployment deployment) {
        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        System.out.println(
                "Found process definition ["
                        + processDefinition.getName() + "] with id ["
                        + processDefinition.getId() + "]");
        return processDefinition;
    }

    /**
     * 发布任务
     * @param processEngine
     * @param processDefinition
     */
    private static void startProcess(ProcessEngine processEngine, ProcessDefinition processDefinition) {
        //发布流程
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
    }

    /**
     * 完成任务
     * @param processEngine
     */
    private static void complateTask(ProcessEngine processEngine,String TaskId) {
        //发布流程
        TaskService taskService = processEngine.getTaskService();
        taskService.setAssignee("10002","user123");
        taskService.complete(TaskId);
    }
}
