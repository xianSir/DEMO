package com.xks.activiti;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.FormService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.form.DateFormType;
import org.activiti.engine.impl.form.StringFormType;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xks
 * @date 2019-11-07
 */
@Configuration
@Slf4j
public class autoctiviti {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private FormService formService;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                log.info("启动我们的程序");
                //启动运行流程
                ProcessInstance processInstance = startProcessInstance("user");
                //处理流程任务
                processTask(processInstance);
                log.info("结束我们的程序");
            }
        };
    }

    private void processTask(ProcessInstance processInstance) {
        Scanner scanner = new Scanner(System.in);
        while (processInstance != null && !processInstance.isEnded()) {
            List<Task> list = taskService.createTaskQuery().list();
            log.info("待处理任务数量 [{}]", list.size());
            for (Task task : list) {
                log.info("待处理任务 [{}]", task.getName());
                Map<String, Object> variables = buildVariablesByScanner(scanner, task);
                taskService.complete(task.getId(), variables);
                processInstance = runtimeService
                        .createProcessInstanceQuery()
                        .processInstanceId(processInstance.getId())
                        .singleResult();
            }
        }
        scanner.close();
    }

    private Map<String, Object> buildVariablesByScanner(Scanner scanner, Task task) {
        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
        List<FormProperty> formProperties = taskFormData.getFormProperties();
        return buildVariablesByScanner(scanner, formProperties);
    }

    public static Map<String, Object> buildVariablesByScanner(Scanner scanner, List<FormProperty> formProperties) {
        Map<String, Object> variables = new HashMap();
        for (FormProperty property : formProperties) {
            String line = null;
            if(StringFormType.class.isInstance(property.getType())) {
                log.info("请输入 {} ？", property.getName());
                line = scanner.nextLine();
                variables.put(property.getId(), line);
            } else if(DateFormType.class.isInstance(property.getType())) {
                log.info("请输入 {} ？ 格式 （yyyy-MM-dd）", property.getName());
                line = scanner.nextLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = dateFormat.parse(line);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                variables.put(property.getId(), date);
            } else {
                log.info("类型暂不支持 {}", property.getType());
            }
            log.info("您输入的内容是 [{}]", line);

        }
        return variables;
    }

    private ProcessInstance startProcessInstance(String processDefinitionId) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionId);
        log.info("启动流程 [{}]", processInstance.getProcessDefinitionKey());
        return processInstance;
    }

}

