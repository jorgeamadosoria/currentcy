package org.jasr.currentcy.service;

import java.util.List;

import org.jasr.currentcy.domain.BaseSample;
import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jasr.currentcy.domain.Trend;

/**
 * The Service for samples. It also the component that handles sample taking and email sending, thus being the center for all
 * sampler centered logic. All public methods are thin wrappers for methods of SamplerDAO
 *
 */
public interface SamplerService {

    public List<Sample> getSnapshot(Currencies currency);

    public List<BaseSample> getChangesSnapshot(Currencies currency);

    public Trend getLatestSamples(String source, Currencies currency);
}
