package org.jasr.currentcy.test.samplers;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jasr.currentcy.samplers.SamplerBase;
import org.jasr.currentcy.test.utils.JUnitBaseTest;
import org.junit.Test;

public class BaseSampler extends JUnitBaseTest {

    protected SamplerBase sampler;

    public void setUp(SamplerBase sampler) {
        super.setUp();
        this.sampler = sampler;
    }

    @Test
    public void sampleUSD() throws Exception {

        Sample sample = sampler.sample(Currencies.USD);
        doTest(sample);
    }

    protected void doTest(Sample sample) {
        String avg = sample.getAvg();
        String buy = sample.getBuy();
        String sell = sample.getSell();
        double avgValue = sample.getAvgValue();
        double buyValue = sample.getBuyValue();
        double sellValue = sample.getSellValue();

        
        
        
        assertThat(new Double(sellValue), is(not(equalTo(0D))));
        assertThat(new Double(avgValue), is(not(equalTo(0D))));
        assertThat(new Double(buyValue), is(not(equalTo(0D))));
        assertThat(new Double(avgValue), is(both(greaterThanOrEqualTo(buyValue)).and(lessThanOrEqualTo(sellValue))));
        assertThat(sellValue, is(greaterThanOrEqualTo(avgValue)));
        assertThat(sell, is(startsWith("$")));
        assertThat(sell.length(), is(both(greaterThanOrEqualTo(3)).and(lessThanOrEqualTo(6))));
        assertThat(avg, is(startsWith("$")));
        assertThat(avg.length(), is(both(greaterThanOrEqualTo(4)).and(lessThanOrEqualTo(7))));
        assertThat(buy, is(startsWith("$")));
        assertThat(buy.length(), is(both(greaterThanOrEqualTo(4)).and(lessThanOrEqualTo(7))));
    }

    @Test
    public void sampleEUR() throws Exception {

        Sample sample = sampler.sample(Currencies.EUR);
        doTest(sample);
    }
}
