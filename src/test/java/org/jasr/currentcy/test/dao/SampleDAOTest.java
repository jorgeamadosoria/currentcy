package org.jasr.currentcy.test.dao;

import org.jasr.currentcy.dao.SampleDAO;
import org.jasr.currentcy.test.utils.JUnitBaseTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

public class SampleDAOTest extends JUnitBaseTest {

    @Autowired
    private SampleDAO sampleDAOMock;

    @Before
    public void setUp() {
        Mockito.reset(sampleDAOMock);
        super.setUp();
    }

    @Test
    public void samples() throws Exception {

        // mockMvc.perform(get("/api/todo"))
        // .andExpect(status().isOk())
        // .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        // .andExpect(jsonPath("$", hasSize(2)))
        // .andExpect(jsonPath("$[0].id", is(1)))
        // .andExpect(jsonPath("$[0].description", is("Lorem ipsum")))
        // .andExpect(jsonPath("$[0].title", is("Foo")))
        // .andExpect(jsonPath("$[1].id", is(2)))
        // .andExpect(jsonPath("$[1].description", is("Lorem ipsum")))
        // .andExpect(jsonPath("$[1].title", is("Bar")));
        //
        // verifyNoMoreInteractions(homeControllerMock);
    }
}
