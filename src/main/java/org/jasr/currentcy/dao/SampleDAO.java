package org.jasr.currentcy.dao;

import java.util.List;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jasr.currentcy.domain.Trend;
/**
 * DAO to handle all persistence for Sample objects.
 */
public interface SampleDAO {

	public void saveSnapshot(List<Sample> samples,Currencies currency);
	/**
	 * samples from a source grouped by day. 10 latest grouped days. 
	 * @param source the code for the exchange that is to be sampled
	 * @param currency currency to be sampled from the exchange
	 * @return A Trend object containing some samples from the specified source and currency
	 */
	public Trend getLatestSamples(String source,Currencies currency);
	
	/**
	 * A list of the latest samples for each exchange registered on the system for the specified currency.
	 * @param currency currency to get samples for.
	 * @return the latest sample for each exchange and the specified currency
	 */
	public List<Sample> getSnapshot(Currencies currency);
	/**
     * A list of the latest samples for the specified currency and each exchange with changes on their values since the last sampling.
     * @param currency currency to get samples for.
     * @return the latest sample for each exchange with differences
     */
    public List<Sample> getChangesSnapshot(Currencies currency);
}
