package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("Monex")
public class Monex extends SimpleJSoupSampler {

    
    @Override
    public int timeout() {
        return 0;
    }

    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            buyValue = Double
                    .parseDouble(doc.select("div#main").get(0).select("div#column2").text());
            sellValue = Double
                    .parseDouble(doc.select("div#main").get(0).select("div#column3").text());
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double
                    .parseDouble(doc.select("div#main").get(3).select("div#column2").text());
            sellValue = Double
                    .parseDouble(doc.select("div#main").get(3).select("div#column3").text());
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }

    public String getCode() {
        return "Monex";
    }

    @Override
    public String getUrl() {
        return "http://www.monex.com.uy/";
    }

    @Override
    public String getName() {
        return "Monex S.F.";
    }
}
