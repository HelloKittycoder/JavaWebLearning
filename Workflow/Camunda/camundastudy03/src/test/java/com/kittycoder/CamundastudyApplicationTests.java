package com.kittycoder;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 基础测试类，其他测试类都从这里继承
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class CamundastudyApplicationTests {

    @Autowired
    protected RuntimeService runtimeService;
    @Autowired
    protected TaskService taskService;
    @Autowired
    protected HistoryService historyService;
    @Autowired
    protected RepositoryService repositoryService;

    @Autowired
    private WebApplicationContext context;
    protected MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

}
