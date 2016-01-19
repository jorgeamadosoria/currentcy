package org.jasr.currentcy.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jasr.currentcy.domain.AppConfig;
import org.jasr.currentcy.domain.BaseSample;
import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Trend;
import org.jasr.currentcy.samplers.SamplerBase;
import org.jasr.currentcy.service.SamplerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * This is the app server side controller. It handles all REST requests from the mobile app, pertaining samples, snapshots and
 * exchange notifications. This controller is a Rest Controller and is Swagger-annotated.
 * 
 */
@RestController
@Api(value = "config", description = "Operations to show in the mobile app")
@RequestMapping(value = "/config")
public class AppController {

    @Resource
    private SamplerService           samplerService;
    @Autowired
    private Map<String, SamplerBase> samplersMap;
    @Autowired
    private List<SamplerBase> exchanges;
    
    private SamplerBase getExchange(String key) {
        return samplersMap.get(key);
    }
    
    /**
     * Returns configuration information for the app.
     * 
     * @param code
     *            the code of the exchange
     * @return the SamplerBase corresponding to the exchange identified by the code parameter
     */
    @ApiOperation(value = "App configuration", notes = "Configuration for the first execution of the mobile app")
    @RequestMapping(value = "/app", method = RequestMethod.GET)
    public AppConfig config(HttpServletRequest request) {
        String baseUrl = String.format("%s://%s:%d/tasks/",request.getScheme(),  request.getServerName(), request.getServerPort());
        return new AppConfig(exchanges,baseUrl);
    }

    /**
     * Returns information for an exchange.
     * 
     * @param code
     *            the code of the exchange
     * @return the SamplerBase corresponding to the exchange identified by the code parameter
     */
    @ApiOperation(value = "exchangeInfo", notes = "return exchange information for the code sent")
    @RequestMapping(value = "/exchange", method = RequestMethod.GET)
    public SamplerBase exchange(
            @ApiParam(name = "code", value = "The code of the exchange to return information from", required = true) @RequestParam String code) {
        return samplersMap.get(code);
    }

    /**
     * Returns the latest samples taken for each exchange from which changes are detected since the last sampling.
     * 
     * @param currency
     *            the currency on which the snapshot is taken
     * @return list of latest samples for each of the exchanges with differences
     */
    @ApiOperation(value = "snapshot", notes = "Returns the latest sample taken for each exchange with differences.")
    @RequestMapping(value = "/snapshot", method = RequestMethod.GET)
    public List<BaseSample> snapshot(
            @ApiParam(name = "currency", value = "The currency on which the snapshot is taken", required = true) @RequestParam Currencies currency) {
        return samplerService.getChangesSnapshot(currency);
    }

    /**
     * Returns a series of the latest samples taken for the given exchange.
     * 
     * @param source
     *            the exchange from which the sample is taken
     * @param currency
     *            The currency on which the sample is expressed
     * @return A trend object containing the latest samples for the selected exchange
     */
    @ApiOperation(value = "samples", notes = "Returns a series of the latest samples taken for the given exchange.")
    @RequestMapping(value = "/{source}/samples", method = RequestMethod.GET)
    public Trend samples(
            @ApiParam(name = "source", value = "the exchange from which the sample is taken", required = true) @PathVariable String source,
            @ApiParam(name = "currency", value = "The currency on which the sample is expressed", required = true) @RequestParam Currencies currency) {

        return samplerService.getLatestSamples(source, currency);
    }

}
