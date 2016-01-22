package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

/**
 * Base class for JSOUP samplers that can express each value request in a single JSoup selector. It is expected that most samplers
 * can inherit from this one, as JSoup selectors are quite powerful
 * 
 */
public abstract class SimpleJSoupSampler extends SamplerBase {

    private String buyValueUSD;
    private String sellValueUSD;
    private String buyValueEUR;
    private String sellValueEUR;

    public SimpleJSoupSampler(String url, String name, String buyValueUSD, String sellValueUSD, String buyValueEUR,
            String sellValueEUR) {
        super(url, name);
        this.buyValueUSD=buyValueUSD;
        this.sellValueUSD=sellValueUSD;
        this.buyValueEUR=buyValueEUR;
        this.sellValueEUR = sellValueEUR;
    }

    @Override
    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            buyValue = Double.parseDouble(doc.select(buyValueUSD).text().trim());
            sellValue = Double.parseDouble(doc.select(sellValueUSD).text().trim());
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double.parseDouble(doc.select(buyValueEUR).text().trim());
            sellValue = Double.parseDouble(doc.select(sellValueEUR).text().trim());
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }
}
