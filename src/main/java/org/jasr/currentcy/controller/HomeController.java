package org.jasr.currentcy.controller;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jasr.currentcy.domain.Trend;
import org.jasr.currentcy.service.EmailService;
import org.jasr.currentcy.service.SamplerService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
    @Resource
    private SamplerService samplerService;
    @Resource
    private EmailService emailService;
    
	@RequestMapping(value = "/snapshot", method = RequestMethod.GET)
	public List<Sample> snapshot(Principal principal,@RequestParam Currencies currency) {
	    
		return samplerService.getSnapshot(currency);
	}
	
	@RequestMapping(value = "/{source}/samples", method = RequestMethod.GET)
    public Trend samples(Principal principal,@PathVariable String source,@RequestParam Currencies currency) {
        
        return samplerService.getLatestSamples(source,currency);
    }
	
	@RequestMapping(value = "/email/subscribe", method = RequestMethod.POST)
    public void subscribe(@RequestParam String email) {
        emailService.subscribeEmail(email);
        
    }

}
