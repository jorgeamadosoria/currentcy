package org.jasr.currentcy.controller;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;

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
	public List<Sample> snapshot(Principal principal) {
	    
		return samplerService.getSnapshot();
	}
	
	@RequestMapping(value = "/{source}/samples", method = RequestMethod.GET)
    public Trend samples(Principal principal,@PathVariable String source) {
        
        return samplerService.getLatestSamples(source);
    }
	
	@RequestMapping(value = "/email/register", method = RequestMethod.POST)
    public void register(@RequestParam String email) {
        
        emailService.registerEmail(email);
    }
	
	@RequestMapping(value = "/email/unregister", method = RequestMethod.GET)
    public void unregister(@RequestParam String token) {
        
        emailService.unregisterEmail(token);
    }
}
