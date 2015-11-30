package org.jasr.currentcy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.jasr.currentcy.dao.SampleDAO;
import org.jasr.currentcy.domain.Sample;
import org.jasr.currentcy.domain.Trend;
import org.jasr.currentcy.samplers.SamplerBase;
import org.jasr.currentcy.service.SamplerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SamplerServiceImpl implements SamplerService {

	@Resource
	private SampleDAO samplerDAO;

	@Autowired
	private List<SamplerBase> samplers;

	@Override
	public List<Sample> getSnapshot() {
		return samplerDAO.getSnapshot();
	}

	@Override
	public Trend getLatestSamples(String source) {
		return samplerDAO.getLatestSamples(source);
	}

	@Scheduled(fixedRate = 360000)
	private void takeSnapshot() {

	    List<Sample> samples = new ArrayList<Sample>();
		for (SamplerBase sampler : samplers){
			samples.add(sampler.sample());
			System.out.printf("%s sampled at %s\n",sampler.getCode(),new Date().toString());
		}
		samplerDAO.saveSnapshot(samples);
	}
}
