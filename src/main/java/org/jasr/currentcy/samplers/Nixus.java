package org.jasr.currentcy.samplers;

import java.io.IOException;

import org.jasr.currentcy.domain.Currencies;
import org.jasr.currentcy.domain.Sample;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("Nix")
public class Nixus extends SamplerBase {

    public Sample doSample(Document doc, Sample sample, Currencies currency) throws IOException {
        double buyValue = 0;
        double sellValue = 0;
        if (currency.equals(Currencies.USD)) {
            buyValue = Double
                    .parseDouble(doc.select("table#AutoNumber21 > tbody > tr").get(1).select("tr").get(3).select("td").get(1).text());
            sellValue = Double
                    .parseDouble(doc.select("table#AutoNumber21 > tbody > tr").get(1).select("tr").get(3).select("td").get(2).text());
        }
        if (currency.equals(Currencies.EUR)) {
            buyValue = Double
                    .parseDouble(doc.select("table#AutoNumber21 > tbody > tr").get(1).select("tr").get(6).select("td").get(1).text());
            sellValue = Double
                    .parseDouble(doc.select("table#AutoNumber21 > tbody > tr").get(1).select("tr").get(6).select("td").get(1).text());
        }
        sample.setBuyValue(buyValue);
        sample.setSellValue(sellValue);
        return sample;
    }

    public String getCode() {
        return "Nix";
    }

    @Override
    public String getUrl() {
        return "http://www.nixus.com.uy";
    }

    @Override
    public String getName() {
        return "Nixus Servicios Financieros";
    }
}
