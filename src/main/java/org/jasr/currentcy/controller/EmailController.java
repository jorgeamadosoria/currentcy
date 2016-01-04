package org.jasr.currentcy.controller;

import javax.annotation.Resource;

import org.jasr.currentcy.service.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mangofactory.swagger.annotations.ApiIgnore;

/**
 * This controller handles registering and unregistering of users to notifications of exchange rates modifications. Notice that
 * actual subscription is handled on the HomeController, as it is a POST request. The requests handled here are intended to
 * originate from clicking links on emails sent by the system and are thus GET requests.
 * All requests redirect to the main page, as this controller do not present any content to the user.
 * 
 */
@Controller
@ApiIgnore
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
