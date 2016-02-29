package org.jasr.currentcy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.jasr.currentcy.dao.SampleDAO;
import org.jasr.currentcy.domain.BaseSample;
import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.CurrencySnapshot;
import org.jasr.currentcy.domain.Sample;
import org.jasr.currentcy.domain.Trend;
import org.jasr.currentcy.samplers.SamplerBase;
import org.jasr.currentcy.service.EmailService;
import org.jasr.currentcy.service.SamplerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SamplerServiceImpl implements SamplerService {

    @Resource
    private SampleDAO         samplerDAO;

    @Resource
    private EmailService      emailService;

    @Autowired
    private List<SamplerBase> samplers;

    @Override
    public List<Sample> getSnapshot(Currencies currency) {
        return samplerDAO.getSnapshot(currency);
    }
    
    @Override
    public List<CurrencySnapshot> getCurrencySnapshot() {
        return samplerDAO.getCurrencySnapshot();
    }

    @Override
    public List<BaseSample> getChangesSnapshot(Currencies currency) {
        return samplerDAO.getChangesSnapshot(currency);
    }

    @Override
    public Trend getLatestSamples(String source, Currencies currency) {
        return samplerDAO.getLatestSamples(source, currency);
    }

    /**
     * Scheduled method to take samples from all registered exchanges. It executes once every hour. each sampler is executed once
     * for each currency. It also checks changes on the samples, send emails and save the recently taken snapshot.
     */
    @Scheduled(fixedRate = 600000)
    private void takeSnapshot() {
        List<Sample> samples = new ArrayList<>();
        List<Sample> changes = new ArrayList<>();
        for (Currencies currency : Currencies.values()) {
            for (SamplerBase sampler : samplers) {
                Sample sample = sampler.sample(currency);
                if (sample != null) {
                    samples.add(sample);
                    System.out.printf("%s - %s sampled at %s\n", currency.code, sampler.getCode(), new Date().toString());
                }
                else
                    System.out.printf("%s - %s can't be sampled at this time: %s\n", currency.code, sampler.getCode(),
                            new Date().toString());
            }

            changes(changes, samples, currency);
            samplerDAO.saveSnapshot(samples, currency);
            samples.clear();
            emailService.sendUpdateEmails(currency, changes);
        }
    }

    /**
     * This method determines which samples hold changes from the latest snapshot in the database, and stores then in the out
     * parameter changes.
     * 
     * @param changes
     *            the out parameter for this method. It holds the samples that are proved to have modifications from the samples
     *            in the latest snapshot of all exchanges
     * @param samples
     *            the recently taken samples, not yet committed to database
     * @param currency
     *            the currency on which to test changes on these samples.
     */
    private void changes(List<Sample> changes, List<Sample> samples, Currencies currency) {
        List<Sample> snapshots = samplerDAO.getSnapshot(currency);

        for (Sample sample1 : samples) {
            boolean result = false;
            for (Sample sample2 : snapshots) {
                // if at least one sampler is equal to a sampler on the other
                // list, result = true
                result = result || sample1.equals(sample2);
            }
            // if all samplers are different, then there is change.
            if (!result){
                changes.add(sample1);
            }
        }
    }

}
