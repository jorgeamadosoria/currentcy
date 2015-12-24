package org.jasr.currentcy.service;

import java.util.List;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jasr.currentcy.domain.Trend;

public interface SamplerService {

    public List<Sample> getSnapshot(Currencies currency);
    
    public Trend getLatestSamples(String source,Currencies currency);
}
