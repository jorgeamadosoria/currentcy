package org.jasr.currentcy.dao;

import java.util.List;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jasr.currentcy.domain.Trend;

public interface SampleDAO {

	public void saveSnapshot(List<Sample> samples,Currencies currency);
	/**
	 * samples from a source grouped by day. 10 latest grouped days. 
	 * @param source
	 * @return
	 */
	public Trend getLatestSamples(String source,Currencies currency);
	
	public List<Sample> getSnapshot(Currencies currency);
}
