package org.jasr.currentcy.controller;

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

/**
 * This is the main web controller. It handles all REST requests from the main page, pertainin samples, snapshots and email
 * subscriptions.
 * This controller is a Rest Controller and is Swagger-annotated.
 * 
 */
@RestController
@Api(value = "currentcy", description = "Operations pertaining to samples and snapshots from currency exchanges")
public class HomeController {

    @Resource
    private SamplerService samplerService;
    @Resource
    private EmailService   emailService;

    /**
     * Returns the latest sample taken for each registered exchange.
     * @param currency the currency on which the snapshot is taken
     * @return list of latest samples for each of the registered exchanges
     */
    @ApiOperation(value = "snapshot", notes = "Returns the latest sample taken for each registered exchange.")
    @RequestMapping(value = "/snapshot", method = RequestMethod.GET)
    public List<Sample> snapshot(@ApiParam(name = "currency", value = "The currency on which the snapshot is taken", required = true) @RequestParam Currencies currency) {
        return samplerService.getSnapshot(currency);
    }

    /**
     * Returns a series of the latest samples taken for the given exchange.
     * @param source the exchange from which the sample is taken
     * @param currency The currency on which the sample is expressed
     * @return A trend object containing the latest samples for the selected exchange
     */
    @ApiOperation(value = "samples", notes = "Returns a series of the latest samples taken for the given exchange.")
    @RequestMapping(value = "/{source}/samples", method = RequestMethod.GET)
    public Trend samples(@ApiParam(name = "source", value = "the exchange from which the sample is taken", required = true) @PathVariable String source,
            @ApiParam(name = "currency", value = "The currency on which the sample is expressed", required = true) @RequestParam Currencies currency) {

        return samplerService.getLatestSamples(source, currency);
    }

    /**
     * Subscribe an email address to notifications from the system
     * @param email The email address to subscribe to the application
     */
    @ApiOperation(value = "subscribe", notes = "Subscribe an email address to notifications from the system")
    @RequestMapping(value = "/email/subscribe", method = RequestMethod.POST)
    public void subscribe(@ApiParam(name = "code", value = "The code of the exchange from which to receive notifications", required = true) @RequestParam String code,
            @ApiParam(name = "email", value = "The email address to subscribe to the application", required = true) @RequestParam String email) {
        emailService.subscribeEmail(code,email);

    }

}
