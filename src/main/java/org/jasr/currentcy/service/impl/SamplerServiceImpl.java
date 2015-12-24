package org.jasr.currentcy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.jasr.currentcy.dao.SampleDAO;
import org.jasr.currentcy.domain.Currencies;
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
    public Trend getLatestSamples(String source, Currencies currency) {
        return samplerDAO.getLatestSamples(source, currency);
    }

    @Scheduled(fixedRate = 3600000)
    private void takeSnapshot() {
        List<Sample> samples = new ArrayList<Sample>();
        boolean isChanged = false;
        for (Currencies currency : Currencies.values()) {
            for (SamplerBase sampler : samplers) {
                samples.add(sampler.sample(currency));
                System.out.printf("%s - %s sampled at %s\n", currency.code,sampler.getCode(), new Date().toString());
            }

            isChanged |= isChange(samples, currency);
            samplerDAO.saveSnapshot(samples, currency);
            samples.clear();
        }
        if (isChanged)
            emailService.sendUpdateEmails();
    }

    private boolean isChange(List<Sample> samples, Currencies currency) {
        List<Sample> snapshots = samplerDAO.getSnapshot(currency);

        for (Sample sample1 : snapshots) {
            boolean result = false;
            for (Sample sample2 : samples) {
                // if at least one sampler is equal to a sampler on the other
                // list, result = true
                result = result || sample1.equals(sample2);
            }
            // if all samplers are different, then there is change.
            if (!result)
                return true;
        }
        // result never is true, there is no change.
        return false;
    }

}
