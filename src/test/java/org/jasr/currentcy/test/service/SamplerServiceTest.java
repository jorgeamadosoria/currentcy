package org.jasr.currentcy.test.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.test.utils.JUnitBaseTest;
import org.junit.Test;

public class SamplerServiceTest extends JUnitBaseTest {

    @Test
    public void getSnapshot(){
        assertThat(samplerService.getSnapshot(Currencies.USD),hasSize(4));
    }
    
    @Test
    public void getChangesSnapshot(){
        assertThat(samplerService.getChangesSnapshot(Currencies.USD),hasSize(4));
    }

    @Test
    public void getLatestSamples(){
        assertThat(samplerService.getLatestSamples("Abit", Currencies.USD).getSamples(),hasSize(4));
    }
}
