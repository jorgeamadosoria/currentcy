package org.jasr.currentcy.test.samplers;


import javax.annotation.Resource;

import org.jasr.currentcy.samplers.SamplerBase;
import org.junit.Before;

public class BCUTest extends BaseSampler {

    @Resource(name="BCU")
    private SamplerBase sampler;
    
    @Before
    public void setUp() {
        super.setUp(sampler);
    }
    
}
