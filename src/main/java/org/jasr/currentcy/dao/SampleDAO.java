package org.jasr.currentcy.dao;

import java.util.List;

import org.jasr.currentcy.domain.Sample;
import org.jasr.currentcy.domain.Trend;

public interface SampleDAO {

	public void saveSnapshot(List<Sample> samples);
	/**
	 * samples from a source grouped by day. 10 latest grouped days. 
	 * @param source
	 * @return
	 */
	public Trend getLatestSamples(String source);
	
	public List<Sample> getSnapshot();
}
