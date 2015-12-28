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

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@RestController
@Api(value="currentcy", description="Operations pertaining to samples and snapshots from currency exchanges")
public class HomeController {
	
    @Resource
    private SamplerService samplerService;
    @Resource
    private EmailService emailService;
    
    @ApiOperation(value = "snapshot")
	@RequestMapping(value = "/snapshot", method = RequestMethod.GET)
	public List<Sample> snapshot(Principal principal,@ApiParam(name="currency", value="The currency on which the snapshot is taken", required=true)@RequestParam Currencies currency) {
		return samplerService.getSnapshot(currency);
	}
	
    @ApiOperation(value = "samples")
	@RequestMapping(value = "/{source}/samples", method = RequestMethod.GET)
    public Trend samples(Principal principal,@ApiParam(name="source", value="the exchange from which the sample is taken", required=true)@PathVariable String source,@ApiParam(name="currency", value="The currency on which the sample is expressed", required=true)@RequestParam Currencies currency) {
        
        return samplerService.getLatestSamples(source,currency);
    }
	
    @ApiOperation(value = "subscribe")
	@RequestMapping(value = "/email/subscribe", method = RequestMethod.POST)
    public void subscribe(@ApiParam(name="email", value="The email address to subscribe to the application", required=true)@RequestParam String email) {
        emailService.subscribeEmail(email);
        
    }

}
