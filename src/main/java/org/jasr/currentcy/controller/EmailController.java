package org.jasr.currentcy.controller;

import javax.annotation.Resource;

import org.jasr.currentcy.service.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mangofactory.swagger.annotations.ApiIgnore;

@Controller
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
	
}
