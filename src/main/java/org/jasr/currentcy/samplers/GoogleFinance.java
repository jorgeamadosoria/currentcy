package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

//@Component("GOOG")
public class GoogleFinance extends SamplerBase {

    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            buyValue = Double.parseDouble(doc.select("div#currency_value span.pr span.bld").text().replace(" UYU", "").trim());
            sellValue = Double.parseDouble(doc.select("div#currency_value span.pr span.bld").text().replace(" UYU", "").trim());
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double.parseDouble(doc.select("div#currency_value span.pr span.bld").text().replace(" UYU", "").trim());
            sellValue = Double.parseDouble(doc.select("div#currency_value span.pr span.bld").text().replace(" UYU", "").trim());
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }

    public GoogleFinance() {
        super("https://www.google.com/finance","Google Finance");
    }
    
    @Override
    public String getUrlByCurrency(Currencies currency) {
        return "https://www.google.com/finance?q=" + currency.code.toUpperCase() + "UYU";
    }

}
