package org.jasr.currentcy.service;

import java.util.List;

import org.jasr.currentcy.domain.Sample;
import org.jasr.currentcy.domain.Trend;

public interface SamplerService {

    public List<Sample> getSnapshot();
    
    public Trend getLatestSamples(String source);
}
