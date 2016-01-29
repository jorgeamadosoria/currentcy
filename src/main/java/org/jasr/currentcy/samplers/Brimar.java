package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("Brimar")
public class Brimar extends SamplerBase {

    public Brimar() {
        super("http://www.brimar.com.uy/", "Brimar S. F.");
    }

    @Override
    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            buyValue = Double.parseDouble(doc.select("div.tm-article-content p:eq(1)").text().split(" | ")[0]);
            sellValue = Double.parseDouble(doc.select("div.tm-article-content p:eq(1)").text().split(" | ")[2]);
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double.parseDouble(doc.select("div.tm-article-content p:eq(7)").text().split(" | ")[0]);
            sellValue = Double.parseDouble(doc.select("div.tm-article-content p:eq(7)").text().split(" | ")[2]);
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }

}
