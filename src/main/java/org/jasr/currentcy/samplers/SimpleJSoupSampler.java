package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;

/**
 * Base class for JSOUP samplers that can express each value request in a single JSoup selector. It is expected that most samplers
 * can inherit from this one, as JSoup selectors are quite powerful
 * 
 */
public class SimpleJSoupSampler extends SamplerBase {

    private String buyValueUSD;
    private String sellValueUSD;
    private String buyValueEUR;
    private String sellValueEUR;

    public SimpleJSoupSampler(String url, String name, String buyValueUSD, String sellValueUSD, String buyValueEUR,
            String sellValueEUR) {
        super(url, name);
        this.buyValueUSD = buyValueUSD;
        this.sellValueUSD = sellValueUSD;
        this.buyValueEUR = buyValueEUR;
        this.sellValueEUR = sellValueEUR;
    }

    public SimpleJSoupSampler(String url, String name, String buyValueUSD, String sellValueUSD, String buyValueEUR,
            String sellValueEUR, String urlByCurrency) {
        super(url, name, urlByCurrency);
        this.buyValueUSD = buyValueUSD;
        this.sellValueUSD = sellValueUSD;
        this.buyValueEUR = buyValueEUR;
        this.sellValueEUR = sellValueEUR;
    }

    @Override
    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        // ASCII code 160 corresponds to &nbsp; that we need to extract if it exists on the text to convert to numbers
        if (currency.equals(Currencies.USD)) {
            buyValue = Double.parseDouble(doc.select(buyValueUSD).text().replace(",", ".").trim()
                    .replaceAll(new String(new char[] { (char) 160 }), ""));
            sellValue = Double.parseDouble(doc.select(sellValueUSD).text().replace(",", ".").trim()
                    .replaceAll(new String(new char[] { (char) 160 }), ""));
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double.parseDouble(doc.select(buyValueEUR).text().replace(",", ".").trim()
                    .replaceAll(new String(new char[] { (char) 160 }), ""));
            sellValue = Double.parseDouble(doc.select(sellValueEUR).text().replace(",", ".").trim()
                    .replaceAll(new String(new char[] { (char) 160 }), ""));
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }
}
