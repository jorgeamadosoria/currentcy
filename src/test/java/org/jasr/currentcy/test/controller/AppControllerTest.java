package org.jasr.currentcy.test.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jasr.currentcy.dao.SampleDAO;
import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jasr.currentcy.domain.Trend;
import org.jasr.currentcy.service.SamplerService;
import org.jasr.currentcy.test.utils.JUnitBaseTest;
import org.jasr.currentcy.test.utils.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

public class AppControllerTest extends JUnitBaseTest {

    @Mock
    protected SampleDAO      samplerDaoMock;

    @InjectMocks
    @Autowired
    protected SamplerService samplerService;

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

        Trend trend = new Trend();
        trend.setSamples(snapshot);

        doNothing().when(samplerDaoMock).saveSnapshot(anyList(), eq(Currencies.USD));
        doNothing().when(samplerDaoMock).saveSnapshot(anyList(), eq(Currencies.EUR));
        when(samplerDaoMock.getSnapshot(Currencies.USD)).thenReturn(snapshot);
        when(samplerDaoMock.getLatestSamples("Abit", Currencies.USD)).thenReturn(trend);

        super.setUp();
    }

    @Test
    public void snapshot() throws Exception {

        mockMvc.perform(get("/web/snapshot").param("currency", Currencies.USD.code)).andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8)).andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].code", equalTo("Abit")));
        verify(samplerDaoMock).getSnapshot(Currencies.USD);
        verifyNoMoreInteractions(samplerDaoMock);
    }

    @Test
    public void samples() throws Exception {

        MvcResult result = mockMvc.perform(get("/web/Abit/samples").param("currency", Currencies.USD.code))
                .andExpect(status().isOk()).andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.samples", hasSize(4))).andExpect(jsonPath("$['samples'][0].code", equalTo("Abit")))
                .andReturn();
        verify(samplerDaoMock).getLatestSamples("Abit",Currencies.USD);
        verifyNoMoreInteractions(samplerDaoMock);
        // System.out.println(result.getResponse().getContentAsString());
    }
}
