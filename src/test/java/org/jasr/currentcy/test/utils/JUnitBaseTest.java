package org.jasr.currentcy.test.utils;

import org.jasr.currentcy.config.ApplicationConfig;
import org.jasr.currentcy.config.PersistenceConfig;
import org.jasr.currentcy.config.WebMvcConfig;
import org.jasr.currentcy.controller.HomeController;
import org.jasr.currentcy.service.SamplerService;
import org.jasr.currentcy.test.config.DatasourceConfig;
import org.jasr.currentcy.test.config.EmailConfig;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebMvcConfig.class, ApplicationConfig.class, DatasourceConfig.class, PersistenceConfig.class,
        EmailConfig.class })
@WebAppConfiguration
public abstract class JUnitBaseTest {

    protected MockMvc               mockMvc;
        
    @Autowired
    protected WebApplicationContext webApplicationContext;

    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}
