package org.jasr.currentcy.test.samplers;


import javax.annotation.Resource;

import org.jasr.currentcy.samplers.SamplerBase;
import org.junit.Before;

public class OpennTest extends BaseSampler {

    @Resource(name="Openn")
    private SamplerBase sampler;
    
    @Before
    public void setUp() {
        super.setUp(sampler);
    }
    
}
