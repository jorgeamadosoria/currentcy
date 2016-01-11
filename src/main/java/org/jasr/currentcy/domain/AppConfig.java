package org.jasr.currentcy.domain;

import java.util.List;

import org.jasr.currentcy.samplers.SamplerBase;

import com.wordnik.swagger.annotations.ApiModel;

/**
 * Read only configuration settings for the mobile app. This instance is meant to help the android app to self configure upon the
 * first execution. It can also help to configure the app if the application changes URL or adds more parameters later in time.
 */
@ApiModel(value="Config",description="Read only configuration settings for the mobile app")
public class AppConfig {

    /**
     * Constant to set the lowest bound possible for the refresh interval. It corresponds to the set interval for sampler refresh
     * in the SamplerService {@link org.jasr.currentcy.service.impl.SamplerServiceImpl#takeSnapshot}
     */
    private static final int  REFRESH_INTERVAL = 60 * 10 * 1000;

    private List<SamplerBase> exchanges;
    private String            url;

    public AppConfig(List<SamplerBase> exchanges, String url) {
        this.exchanges = exchanges;
        this.url = url;
    }

    public List<SamplerBase> getExchanges() {
        return exchanges;
    }

    public Currencies[] getCurrencies() {
        return Currencies.values();
    }

    public String getUrl() {
        return url;
    }

    public static int getRefreshInterval() {
        return REFRESH_INTERVAL;
    }

}
