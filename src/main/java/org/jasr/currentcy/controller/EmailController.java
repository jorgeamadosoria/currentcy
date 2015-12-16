package org.jasr.currentcy.controller;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jasr.currentcy.service.EmailService;
import org.jasr.currentcy.service.SamplerService;
import org.jasr.currentcy.utils.EmailBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
	
    @Resource
    private EmailService emailService;
    
	@RequestMapping(value = "email/register", method = RequestMethod.GET)
    public String register(@RequestParam String token) {
        emailService.registerEmail(token);
        return "redirect:/";
    }
	
	@RequestMapping(value = "email/unregister", method = RequestMethod.GET)
    public String unregister(@RequestParam String token) {
        emailService.unregisterEmail(token);
        return "redirect:/";
    }
	
	@Resource
	private SamplerService samplerService;
	@Resource
    private EmailBuilder emailBodyUtils;
	
	@RequestMapping(value = "email/test", method = RequestMethod.GET)
    public String test() {
	    
	    List<List<Sample>> samplesByCurrency = new ArrayList<>();
	    for(Currencies cur:Currencies.values()){
            samplesByCurrency.add(cur.order, samplerService.getSnapshot(cur));
            
        }
	    return emailBodyUtils.getUpdateEmailBody(samplesByCurrency);
    }
	
	@RequestMapping(value = "email/test/register", method = RequestMethod.GET)
    public String testRegister() {
	    
        return emailBodyUtils.getRegisterEmailBody(new BigInteger(130, new SecureRandom()).toString(32));
    }
    
}
