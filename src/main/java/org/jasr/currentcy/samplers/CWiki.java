package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("CWiki")
public class CWiki extends SamplerBase {

    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            buyValue = Double.parseDouble(doc.select("span:contains(UYU)").get(0).text().replace(" UYU", "").trim());
            sellValue = Double.parseDouble(doc.select("span:contains(UYU)").get(0).text().replace(" UYU", "").trim());
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double.parseDouble(doc.select("span:contains(UYU)").get(0).text().replace(" UYU", "").trim());
            sellValue = Double.parseDouble(doc.select("span:contains(UYU)").get(0).text().replace(" UYU", "").trim());
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }

    public String getCode() {
        return "CWiki";
    }

    @Override
    public String getUrl() {
        return "http://currency.wiki";
    }

    @Override
    public String getUrlByCurrency(Currencies currency) {
        return "http://currency.wiki/currencytools?sum=1&from=" + currency.code.toUpperCase() + "&to=UYU";
    }

    @Override
    public String getName() {
        return "Currency.wiki";
    }
}
