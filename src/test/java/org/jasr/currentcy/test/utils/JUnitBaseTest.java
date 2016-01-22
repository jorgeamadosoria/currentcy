package org.jasr.currentcy.test.utils;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jasr.currentcy.config.ApplicationConfig;
import org.jasr.currentcy.config.PersistenceConfig;
import org.jasr.currentcy.config.WebMvcConfig;
import org.jasr.currentcy.controller.HomeController;
import org.jasr.currentcy.dao.SampleDAO;
import org.jasr.currentcy.domain.BaseSample;
import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jasr.currentcy.domain.Trend;
import org.jasr.currentcy.service.SamplerService;
import org.jasr.currentcy.test.config.DatasourceConfig;
import org.jasr.currentcy.test.config.EmailConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

    @Mock
    protected SampleDAO             samplerDaoMock;

    @InjectMocks
    @Autowired
    protected SamplerService        samplerService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        // Mockito.reset(samplerServiceMock);
        List<Sample> snapshot = new ArrayList<>();
        Sample sample1 = new Sample();
        sample1.setCode("Abit");
        sample1.setDate(new Date().toString());
        snapshot.add(sample1);
        Sample sample2 = new Sample();
        sample2.setDate(new Date().toString());
        sample2.setCode("ASPN");
        snapshot.add(sample2);
        Sample sample3 = new Sample();
        sample3.setCode("BBVa");
        sample3.setDate(new Date().toString());
        snapshot.add(sample3);
        Sample sample4 = new Sample();
        sample4.setCode("BROU");
        sample4.setDate(new Date().toString());
        snapshot.add(sample4);

        List<BaseSample> baseSnapshot = new ArrayList<>();
        BaseSample baseSample1 = new BaseSample();
        baseSample1.setCode("Abit");
        baseSample1.setDate(new Date().toString());
        baseSnapshot.add(baseSample1);
        BaseSample baseSample2 = new BaseSample();
        baseSample2.setDate(new Date().toString());
        baseSample2.setCode("ASPN");
        baseSnapshot.add(baseSample2);
        BaseSample baseSample3 = new BaseSample();
        baseSample3.setCode("BBVa");
        baseSample3.setDate(new Date().toString());
        baseSnapshot.add(baseSample3);
        BaseSample baseSample4 = new BaseSample();
        baseSample4.setCode("BROU");
        baseSample4.setDate(new Date().toString());
        baseSnapshot.add(baseSample4);

        Trend trend = new Trend();
        trend.setSamples(snapshot);

        doNothing().when(samplerDaoMock).saveSnapshot(anyList(), eq(Currencies.USD));
        doNothing().when(samplerDaoMock).saveSnapshot(anyList(), eq(Currencies.EUR));
        when(samplerDaoMock.getChangesSnapshot(Currencies.USD)).thenReturn(baseSnapshot);
        when(samplerDaoMock.getSnapshot(Currencies.USD)).thenReturn(snapshot);
        when(samplerDaoMock.getLatestSamples("Abit", Currencies.USD)).thenReturn(trend);

        
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}
