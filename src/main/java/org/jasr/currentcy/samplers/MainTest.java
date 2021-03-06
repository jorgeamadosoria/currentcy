package org.jasr.currentcy.samplers;

import org.jasr.currentcy.config.SamplerConfig;
import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;

public class MainTest {

    public static void main(String[] args) {
        SamplerConfig var = new SamplerConfig();
        SamplerBase[] samplers = new SamplerBase[] { 
                var.AeroM()};

        for (SamplerBase sampler : samplers) {
            sampler.setTimeout(0);
            System.out.println(sampler.getName());
            for (Currencies currency : Currencies.values()) {
                System.out.println(currency.name());
                Sample sample = sampler.sample(currency);
                System.out.println(sample.getBuy());
                System.out.println(sample.getAvg());
                System.out.println(sample.getSell());
            }
        }
        
    }

}
