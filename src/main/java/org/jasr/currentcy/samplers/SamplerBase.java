package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * Base class for all samplers. A sampler is the main unit for information collecting. Each sampler recover information from one
 * exchange in all available currencies as described in {@link org.jasr.currentcy.domain.Currencies} It usually relies on Jsoup
 * parsing of code with css-like selectors, but on occasion other methods of parsing can be used, depending on the source.
 * 
 */
public abstract class SamplerBase implements BeanNameAware{

    @Override
    public void setBeanName(String name) {
        this.code = name;
    }

    public SamplerBase(String url, String name) {
        this.url = url;
        this.name = name;
        this.urlByCurrency = null;
    }
    
    public SamplerBase(String url, String name,String urlByCurrency) {
        this(url,name);
        this.urlByCurrency = urlByCurrency;
    }

    /**
     * The url used for sampling. The getter has special logic to default this value to the url of the exchange if not present
     */
    private String urlByCurrency = null;

    /**
     * Name of the exchange as it is known by humans (fantasy name as registered on the BCU). Used for labeling purposes on the
     * UI.
     * 
     */
    private String name;
    /**
     * Source main page url. This is not necessarily the same page used for data sampling and is used for linking purposes only.
     */
    private String url;
    /**
     * Unique readable code to identify this sample and its source. Usually in a 4-6 letter format that references the name of the
     * exchange
     * 
     */
    private String code;

    /**
     * User agent string to mock Firefox detection in order to bypass DDoS detection through false positives
     */
    private String userAgent     = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0";

    private int    id;

    public String getUrl() {
        return url;
    };

    public String getName() {
        return name;
    };

    /**
     * Specifies timeout for sampling from sources. -1 for default timeout. Other values are expressed in milliseconds
     * 
     * @return milliseconds for jsoup sampling timeout
     */
    private int timeout=300000;
    
    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * The url used for sampling. Usually but not always, is the same url for all currencies and can even be the same as the url
     * for the main page for the source, which is the default behaviour
     * 
     * @param currency
     *            the currency to sample from the source
     * @return The html code of the page, usually containing the data sampled for the specified currency. It defaults to the
     *         exchange url if the specific url for sampling does not exist
     */
    public String getUrlByCurrency(Currencies currency) {
        if (urlByCurrency == null)
            return getUrl();
        return urlByCurrency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    /**
     * Method to do the actual sampling of the source using the specified currency. This is the core method of the sampler and
     * uses a template-callback pattern to include the other methods as customizable points of extension.
     * 
     * @param currency
     *            the currency to sample from the source url
     * @return the Sample object representing the data from the exchange at this moment in time
     */
    public Sample sample(Currencies currency) {
        Sample sample = null;
        try {
            int timeout = getTimeout();
            Document doc = null;
            if (timeout != -1)
                doc = Jsoup.connect(getUrlByCurrency(currency)).userAgent(userAgent).timeout(timeout).get();
            else
                doc = Jsoup.connect(getUrlByCurrency(currency)).userAgent(userAgent).get();
            sample = new Sample();
            sample.setCode(getCode());
            sample = doSample(doc, sample, currency);
        }
        catch (Exception e) {
            e.printStackTrace();
            sample = null;
        }
        return sample;
    }

    /**
     * Actual sampling. This method is intended to be defined on child class, as each source is different. Usually this method
     * contains the jsoup parsing with css selectors
     * 
     * @param doc
     *            the Jsoup document sampled from the exchange source, containing the full html or text content from which to
     *            sample data
     * @param sample
     *            The previously created Sample object to be populated with currency data
     * @param currency
     *            the currency to sample
     * @return A fully populated Sample object with currency data for this exchange
     * @throws IOException
     *             This exception is handled on the {@link org.jasr.currentcy.samplers.SamplerBase#sample(Currencies)}
     */
    public abstract Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException;
}
