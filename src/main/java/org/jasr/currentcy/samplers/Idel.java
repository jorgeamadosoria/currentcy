package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("Idel")
public class Idel extends SamplerBase {

    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            buyValue = Double
                    .parseDouble(doc.select("a.linkHistorical").parents().get(2).select("tr:eq(2) td:eq(2)").text().replace(",", "."));
            sellValue = Double
                    .parseDouble(doc.select("a.linkHistorical").parents().get(2).select("tr:eq(2) td:eq(3)").text().replace(",", "."));
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double
                    .parseDouble(doc.select("a.linkHistorical").parents().get(2).select("tr:eq(5) td:eq(2)").text().replace(",", "."));
            sellValue = Double
                    .parseDouble(doc.select("a.linkHistorical").parents().get(2).select("tr:eq(5) td:eq(3)").text().replace(",", "."));
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }

    public Idel() {
        super("http://www.intercambio.com.uy","Intercambio Idel SA");
    }
}
