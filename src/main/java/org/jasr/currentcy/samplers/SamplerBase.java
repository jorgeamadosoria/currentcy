package org.jasr.currentcy.samplers;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class SamplerBase {
    private int id;

    public abstract String getUrl();

    /**
     * -1 for default timeout. Other values are expressed in milliseconds
     * 
     * @return milliseconds for jsoup sampling timeout
     */
    public int timeout() {
        return -1;
    }

    public String getUrlByCurrency(Currencies currency) {
        return getUrl();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract String getCode();

    public abstract String getName();

    public Sample sample(Currencies currency) {
        Sample sample = null;
        try {
            int timeout = timeout();
            Document doc = null;
            if (timeout != -1)
                doc = Jsoup.connect(getUrlByCurrency(currency)).timeout(timeout()).get();
            else
                doc = Jsoup.connect(getUrlByCurrency(currency)).get();
            sample = new Sample();
            sample.setCode(getCode());
            sample = doSample(doc, sample, currency);
        }
        catch (SocketTimeoutException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return sample;
    }

    public abstract Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException;
}
