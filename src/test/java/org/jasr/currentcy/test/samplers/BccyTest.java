package org.jasr.currentcy.test.samplers;


import javax.annotation.Resource;

import org.jasr.currentcy.samplers.SamplerBase;
import org.junit.Before;

public class BccyTest extends BaseSampler {

    @Resource(name="Bccy")
    private SamplerBase sampler;
    
    @Before
    public void setUp() {
        super.setUp(sampler);
    }
    
}
