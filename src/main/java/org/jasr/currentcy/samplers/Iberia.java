package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("Iberia")
public class Iberia extends SamplerBase {

    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            buyValue = Double
                    .parseDouble(doc.select("div.entry-content table tr").get(1).select("td").get(2).text());
            sellValue = Double
                    .parseDouble(doc.select("div.entry-content table tr").get(1).select("td").get(3).text());
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double
                    .parseDouble(doc.select("div.entry-content table tr").get(4).select("td").get(2).text());
            sellValue = Double
                    .parseDouble(doc.select("div.entry-content table tr").get(4).select("td").get(3).text());
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }

    public String getCode() {
        return "Iberia";
    }

    @Override
    public String getUrl() {
        return "http://www.cambioiberia.com/";
    }

    @Override
    public String getName() {
        return "Cambio Iberia";
    }
}
