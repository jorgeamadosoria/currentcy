package org.jasr.currentcy.test.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.test.utils.JUnitBaseTest;
import org.jasr.currentcy.test.utils.TestUtil;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

public class AppControllerTest extends JUnitBaseTest {

   
    @Test
    public void snapshot() throws Exception {

        mockMvc.perform(get("/config/snapshot").param("currency", Currencies.USD.code)).andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8)).andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].code", equalTo("Abit")));
        verify(samplerDaoMock).getChangesSnapshot(Currencies.USD);
        verifyNoMoreInteractions(samplerDaoMock);
    }

    @Test
    public void samples() throws Exception {

        mockMvc.perform(get("/config/Abit/samples").param("currency", Currencies.USD.code))
                .andExpect(status().isOk()).andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.samples", hasSize(4))).andExpect(jsonPath("$['samples'][0].code", equalTo("Abit")))
                ;
        verify(samplerDaoMock).getLatestSamples("Abit",Currencies.USD);
        verifyNoMoreInteractions(samplerDaoMock);
        // System.out.println(result.getResponse().getContentAsString());
    }
}
